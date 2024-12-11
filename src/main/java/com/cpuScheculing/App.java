package com.cpuScheculing;
import com.cpuScheculing.model.Process;

import com.cpuScheculing.scheduler.*;
import com.cpuScheculing.util.FileReaderUtil;
import com.cpuScheculing.util.IndividualCPUScheduler;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String filePath = "src/main/resources/input/Amazon.com - Order 111-1617229-6539431.pdf";
        int maxProcesses = 5;

        // Read processes from file
        List<Process> processes = FileReaderUtil.readProcessesFromFile(filePath, maxProcesses);

        if (processes != null) {

            System.out.println("FCFS Scheduling:");
            double[] fcfsResults = new FCFS().schedule(processes);

            System.out.println("\nSJF Non-Preemptive Scheduling:");
            double[] sjfNonPreemptiveResults = new SJF().schedule(processes);

            System.out.println("\nPriority Scheduling:");
            double[] priorityResults = new PriorityScheduling().schedule(processes);

            System.out.println("\nSJF Preemptive Scheduling:");
            double[] sjfPreemptiveResults = new SJFPreemptive().schedule(processes);

            System.out.println("\nRound Robin Scheduling:");
            double[] roundRobinResults = new RoundRobin().schedule(processes, 2); // Example time quantum

            // Compare and find the best algorithm
            System.out.println("\nComparison of Algorithms:");
            System.out.printf("Algorithm\t\t\tAverage Waiting Time\tAverage Turnaround Time\n");
            System.out.printf("FCFS\t\t\t\t\t%.2f\t\t\t\t\t%.2f\n", fcfsResults[0], fcfsResults[1]);
            System.out.printf("Non-Preemptive SJF\t\t%.2f\t\t\t\t\t%.2f\n", sjfNonPreemptiveResults[0], sjfNonPreemptiveResults[1]);
            System.out.printf("Preemptive SJF\t\t\t%.2f\t\t\t\t\t%.2f\n", sjfPreemptiveResults[0], sjfPreemptiveResults[1]);
            System.out.printf("Priority Scheduling\t\t%.2f\t\t\t\t\t%.2f\n", priorityResults[0], priorityResults[1]);
            System.out.printf("Round Robin\t\t\t\t%.2f\t\t\t\t\t%.2f\n", roundRobinResults[0], roundRobinResults[1]);

            // Determine the best algorithm based on Average Waiting Time
            String bestAlgorithm = "FCFS";
            double minWaitingTime = fcfsResults[0];

            if (sjfNonPreemptiveResults[0] < minWaitingTime) {
                bestAlgorithm = "Non-Preemptive SJF";
                minWaitingTime = sjfNonPreemptiveResults[0];
            }
            if (sjfPreemptiveResults[0] < minWaitingTime) {
                bestAlgorithm = "Preemptive SJF";
                minWaitingTime = sjfPreemptiveResults[0];
            }
            if (priorityResults[0] < minWaitingTime) {
                bestAlgorithm = "Priority Scheduling";
                minWaitingTime = priorityResults[0];
            }
            if (roundRobinResults[0] < minWaitingTime) {
                bestAlgorithm = "Round Robin";
            }

            System.out.println("\nBest Algorithm: " + bestAlgorithm + " (Lower Average Waiting Time)\n\n");
        }

        //IndividualCPUScheduler individualCPUScheduler = new IndividualCPUScheduler(processes);
    }
}
