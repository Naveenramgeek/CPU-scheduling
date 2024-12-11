package com.cpuScheculing.scheduler;
import com.cpuScheculing.model.Process;

import java.util.List;

public class FCFS {
    public double[] schedule(List<Process> processes) {
        processes.sort((p1, p2) -> p1.getArrivalTime() - p2.getArrivalTime());
        int currentTime = 0;
        int totalWaitingTime = 0, totalTurnaroundTime = 0;

        System.out.println("Process\tStart Time\tFinish Time\tWaiting Time\tTurnaround Time");
        for (Process process : processes) {
            int startTime = Math.max(currentTime, process.getArrivalTime());
            int waitingTime = startTime - process.getArrivalTime();
            int finishTime = startTime + process.getBurstTime();
            int turnaroundTime = finishTime - process.getArrivalTime();

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;

            currentTime = finishTime;

            System.out.printf("P%d\t\t%d\t\t\t\t%d\t\t\t%d\t\t\t\t%d\n",
                    process.getId(), startTime, finishTime, waitingTime, turnaroundTime);
        }

        int n = processes.size();
        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.printf("Average Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);

        return new double[]{averageWaitingTime, averageTurnaroundTime};
    }
}
