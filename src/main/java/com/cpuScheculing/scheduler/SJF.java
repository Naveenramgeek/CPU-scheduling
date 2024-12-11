package com.cpuScheculing.scheduler;
import com.cpuScheculing.model.Process;

import java.util.ArrayList;
import java.util.List;

public class SJF {
    public double[] schedule(List<Process> processes) {
        List<Process> queue = new ArrayList<>(processes);
        int currentTime = 0;
        int totalWaitingTime = 0, totalTurnaroundTime = 0;

        System.out.println("Process\tStart Time\tFinish Time\tWaiting Time\tTurnaround Time");
        while (!queue.isEmpty()) {
            int CurrentTime = currentTime;
            Process next = queue.stream()
                    .filter(p -> p.getArrivalTime() <= CurrentTime)
                    .min((p1, p2) -> p1.getBurstTime() - p2.getBurstTime())
                    .orElse(queue.get(0));

            queue.remove(next);

            int startTime = Math.max(currentTime, next.getArrivalTime());
            int waitingTime = startTime - next.getArrivalTime();
            int finishTime = startTime + next.getBurstTime();
            int turnaroundTime = finishTime - next.getArrivalTime();

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;

            currentTime = finishTime;

            System.out.printf("P%d\t\t%d\t\t\t\t%d\t\t\t%d\t\t\t\t%d\n",
                    next.getId(), startTime, finishTime, waitingTime, turnaroundTime);
        }

        int n = processes.size();
        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.printf("Average Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);

        return new double[]{averageWaitingTime, averageTurnaroundTime};
    }
}
