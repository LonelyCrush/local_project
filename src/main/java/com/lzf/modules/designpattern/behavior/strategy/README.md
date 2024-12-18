1. 一般来讲，如果策略类是无状态的，不包含成员变量，只是纯粹的算法实现，这样的策略对象是可以被共享使用的，不需要在每次调用getStrategy()的时候，都创建一个新的策略对象。针对这种情况，我们可以使用静态生成的实现方式
   1. 这种情况相当于使用map作为表代替了原先的if-else分支逻辑
2. 如果策略类是有状态的，根据业务场景的需要，我们希望每次从工厂方法中，获得的都是新创建的策略对象，而不是缓存好可共享的策略对象，则需要每次创建新的策略类
   1. 这种情况相当于将原先的if-else分支逻辑移到了工厂类当中