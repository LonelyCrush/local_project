package com.lzf.local.d2411.d241115.myeventbus;

import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author leizefeng
 */
public class MyObserverRegistry {

  private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<MyObserverAction>> registry =
      new ConcurrentHashMap<>();

  public void register(Object observer) {
    Map<Class<?>, Collection<MyObserverAction>> observerActions = findAllObserverActions(observer);
    for (Map.Entry<Class<?>, Collection<MyObserverAction>> entry : observerActions.entrySet()) {
      Class<?> eventType = entry.getKey();
      Collection<MyObserverAction> eventActions = entry.getValue();
      CopyOnWriteArraySet<MyObserverAction> registeredEventActions = registry.get(eventType);
      if (registeredEventActions == null) {
        registry.putIfAbsent(eventType, new CopyOnWriteArraySet<>());
        registeredEventActions = registry.get(eventType);
      }
      registeredEventActions.addAll(eventActions);
    }
  }

  private Map<Class<?>, Collection<MyObserverAction>> findAllObserverActions(Object observer) {
    Map<Class<?>, Collection<MyObserverAction>> observerActions = new HashMap<>();
    Class<?> clazz = observer.getClass();
    for (Method method : getAnnotatedMethods(clazz)) {
      Class<?>[] parameterTypes = method.getParameterTypes();
      Class<?> eventType = parameterTypes[0];
      if (!observerActions.containsKey(eventType)) {
        observerActions.put(eventType, new ArrayList<>());
      }
      observerActions.get(eventType).add(new MyObserverAction(observer, method));
    }
    return observerActions;
  }

  // 找到类中的Subscribe注解标记的方法
  private List<Method> getAnnotatedMethods(Class<?> clazz) {
    List<Method> annotatedMethods = new ArrayList<>();
    for (Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Subscribe.class)) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length!= 1) {
          throw new IllegalArgumentException(
              String.format("Method %s has @Subscribe annotation but has %s parameters. "
                      + "Subscriber methods must have exactly 1 parameter.",
                  method, parameterTypes.length));
        }
        annotatedMethods.add(method);
      }
    }
    return annotatedMethods;
  }

  public List<MyObserverAction> getMatchedObserverActions(Object event) {
    List<MyObserverAction> matchedObservers = new ArrayList<>();
    Class<?> postedEventType = event.getClass();
    for (Map.Entry<Class<?>, CopyOnWriteArraySet<MyObserverAction>> entry : registry.entrySet()) {
      Class<?> eventType = entry.getKey();
      Collection<MyObserverAction> eventActions = entry.getValue();
      if (postedEventType.isAssignableFrom(eventType)) {
        matchedObservers.addAll(eventActions);
      }
    }
    return matchedObservers;
  }
}
