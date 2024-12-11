package com.cpuScheculing.scheduler;

import com.cpuScheculing.model.Process;
import java.util.ArrayList;
import java.util.List;

public class SJFPreemptive {
    public double[] schedule(List<Process> processes) {
        List<Process> queue = new ArrayList<>(processes);
        int currentTime = 0;
        int totalWaitingTime = 0, totalTurnaroundTime = 0;

        System.out.println("Process\tStart Time\tFinish Time\tWaiting Time\tTurnaround Time");

        int[] remainingBurstTime = new int[processes.size()];
        for (int i = 0; i < processes.size(); i++) {
            remainingBurstTime[i] = processes.get(i).getBurstTime();
        }

        while (!queue.isEmpty()) {
            // Find the shortest job that has arrived
            int finalCurrentTime = currentTime;
            Process next = queue.stream()
                    .filter(p -> p.getArrivalTime() <= finalCurrentTime)
                    .min((p1, p2) -> remainingBurstTime[p1.getId() - 1] - remainingBurstTime[p2.getId() - 1])
                    .orElse(null);

            // If no process has arrived yet, move the current time forward
            if (next == null) {
                currentTime++;
                continue;
            }

            int index = next.getId() - 1; // Index in remainingBurstTime
            currentTime++; // Simulate 1 unit of CPU time
            remainingBurstTime[index]--; // Reduce burst time by 1

            // If process is completed, calculate its metrics and remove it from the queue
            if (remainingBurstTime[index] == 0) {
                int finishTime = currentTime;
                int turnaroundTime = finishTime - next.getArrivalTime();
                int waitingTime = turnaroundTime - next.getBurstTime();

                totalWaitingTime += waitingTime;
                totalTurnaroundTime += turnaroundTime;

                queue.remove(next); // Remove process from the queue

                // Print process details
                System.out.printf("P%d\t\t%d\t\t\t\t%d\t\t\t%d\t\t\t\t%d\n",
                        next.getId(), currentTime - next.getBurstTime(), finishTime, waitingTime, turnaroundTime);
            }
        }

        // Calculate and print averages
        int n = processes.size();
        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.printf("Average Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);

        return new double[]{averageWaitingTime, averageTurnaroundTime};
    }
}
