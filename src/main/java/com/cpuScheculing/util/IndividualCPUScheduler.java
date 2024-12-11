package com.cpuScheculing.util;

import com.cpuScheculing.model.Process;
import com.cpuScheculing.scheduler.*;

import java.util.List;
import java.util.Scanner;

public class IndividualCPUScheduler {

    public IndividualCPUScheduler(List<Process> processes){
        Scanner scanner = new Scanner(System.in);

        System.out.println("1.FCFS.\n2.SJF.\n3.Priority Scheduling.\n4.SJF Preemptive.\n.5.Round Robin.");
        System.out.print("Choose one of the algorithms above:");
        int chooseScheduler = scanner.nextInt();

        switch (chooseScheduler) {
            case 1:
                System.out.println("FCFS Scheduling:");
                new FCFS().schedule(processes);
                break;
            case 2:
                System.out.println("\nSJF Scheduling:");
                new SJF().schedule(processes);
                break;
            case 3:
                System.out.println("\nPriority Scheduling:");
                new PriorityScheduling().schedule(processes);
                break;
            case 4:
                System.out.println("\nSJF Preemptive Scheduling:");
                new SJFPreemptive().schedule(processes);
                break;
            case 5:
                System.out.println("\nRound Robin Scheduling:");
                new RoundRobin().schedule(processes, 2); // Example time quantum
                break;
            default:
                System.out.println("Invalid selection");
        }
    }
}
