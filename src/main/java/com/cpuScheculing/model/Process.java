package com.cpuScheculing.model;

public class Process {
    private int id;
    private int burstTime;
    private int arrivalTime;
    private int priority;

    public Process(int id, int burstTime, int arrivalTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
    }

    public int getId() { return id; }
    public int getBurstTime() { return burstTime; }
    public int getArrivalTime() { return arrivalTime; }
    public int getPriority() { return priority; }
}
