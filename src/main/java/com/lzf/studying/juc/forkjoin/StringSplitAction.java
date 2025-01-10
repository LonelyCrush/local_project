package com.lzf.studying.juc.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author leizefeng
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringSplitAction extends RecursiveAction {

  private String workload = "";

  private static final int THRESHOLD = 7;

  @Override
  protected void compute() {
    if (workload.length() > THRESHOLD) {
      invokeAll(splitTask(workload));
    } else {
      doTask(workload);
    }
  }

  @NotNull
  private List<StringSplitAction> splitTask(@NotNull String workload) {
    List<StringSplitAction> actionList = new ArrayList<>();
    String substring1 = workload.substring(0, workload.length() / 2);
    String substring2 = workload.substring(workload.length() / 2);
    actionList.add(new StringSplitAction(substring1));
    actionList.add(new StringSplitAction(substring2));
    return actionList;
  }

  private void doTask(String workload) {
    log.info("Original String: {}, UpperCase String: {}", workload, workload.toUpperCase());
  }
}
