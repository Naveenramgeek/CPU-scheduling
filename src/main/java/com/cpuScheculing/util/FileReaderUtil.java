package com.cpuScheculing.util;
import com.cpuScheculing.model.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {
    public static List<Process> readProcessesFromFile(String filePath, int maxProcesses) {
        List<Process> processes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int processId = 1;
            int arrivalTime = 0; // Simulate processes arriving sequentially

            while ((line = br.readLine()) != null && processes.size() < maxProcesses) {
                // Simulate burst time as the length of the line
                int burstTime = line.length();
                // Simulate priority based on line length (smaller = higher priority)
                int priority = Math.max(1, 10 - burstTime / 10);

                // Create a process
                Process process = new Process(processId++, burstTime, arrivalTime++, priority);
                processes.add(process);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return processes;
    }
}
