package com.cpuScheculing.scheduler;
import com.cpuScheculing.model.Process;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin {
    public double[] schedule(List<Process> processes, int timeQuantum) {
        Queue<Process> queue = new LinkedList<>(processes);
        int currentTime = 0;
        int totalWaitingTime = 0, totalTurnaroundTime = 0;
        int[] remainingBurstTime = new int[processes.size()];

        for (int i = 0; i < processes.size(); i++) {
            remainingBurstTime[i] = processes.get(i).getBurstTime();
        }

        System.out.println("Process\tStart Time\tFinish Time\tWaiting Time\tTurnaround Time");
        while (!queue.isEmpty()) {
            Process process = queue.poll();
            int index = process.getId() - 1;
            int startTime = currentTime;
            int executionTime = Math.min(timeQuantum, remainingBurstTime[index]);

            currentTime += executionTime;
            remainingBurstTime[index] -= executionTime;

            if (remainingBurstTime[index] > 0) {
                queue.add(process);
            } else {
                int finishTime = currentTime;
                int turnaroundTime = finishTime - process.getArrivalTime();
                int waitingTime = turnaroundTime - process.getBurstTime();

                totalWaitingTime += waitingTime;
                totalTurnaroundTime += turnaroundTime;

                System.out.printf("P%d\t\t%d\t\t\t\t%d\t\t\t%d\t\t\t\t%d\n",
                        process.getId(), startTime, finishTime, waitingTime, turnaroundTime);
            }
        }

        int n = processes.size();
        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.printf("Average Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);

        return new double[]{averageWaitingTime, averageTurnaroundTime};
    }
}
