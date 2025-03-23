/* First -Come, First-Served Algorithm also known as First-In, First-Out Algorithm.
 * This algorithm prioritizes arrival time. The earlier the arrival, the higher the priority.
 * If multiple processes have the same arrival time, the arbitration rule picks a process at random.
 * All requests are processed sequentially, non-preemptive.
 * 
 * This file will sort the processes based on the algorithm.
 * Calculate the Waiting time and Turnaround Time for each process.
 * Calculate the average WT and TAT.
 */

import java.util.*;

public class FCFS {

    public List<Process> sortByArrivalTime(List<Process> processesList){
        processesList.sort(Comparator.comparingInt(p -> p.arrival_time));
        return processesList;
    }

    public void FCFSAlgorithm(List<Process> processes){
        int wt = 0;
        int tat = 0;
        int currentTime = 0;
        
        sortByArrivalTime(processes);
        
        for(Process p : processes){
            if(currentTime < p.arrival_time){
                currentTime = p.arrival_time;
            }
            p.waitingTime = currentTime - p.arrival_time;
            p.completionTime = currentTime + p.burst_time;
            p.turnAroundTime = p.completionTime - p.arrival_time;
            currentTime += p.burst_time;

            wt += p.waitingTime;
            tat += p.turnAroundTime;
        }
        System.out.println("\n------------------- FCFS/FIFO Scheduling Algorithm -------------------");
        System.out.printf("%-5s %-10s %-10s %-10s %-15s %-15s\n", 
            "PID", "Arrival", "Burst", "Start", "Waiting Time", "Turnaround Time");
        for (Process p : processes) {
            System.out.printf("%-5d %-10d %-10d %-10d %-15d %-15d\n", 
                p.pid, p.arrival_time, p.burst_time, p.arrival_time + p.waitingTime, p.waitingTime, p.turnAroundTime);
        }

        double averageWT = (double) wt/ processes.size();
        double averageTAT = (double) tat/ processes.size();
        System.out.println("\n--------------- AVERAGES ----------------");
        System.out.printf("Average Waiting Time: %.2f\n", averageWT);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTAT);
        System.out.println("----------------------------------------------------------------------");

        printGanttChart(processes);

        int[] memoryBlocks1 = {100, 500, 200, 300, 600};
        int[] memoryBlocks2 = {100, 500, 200, 300, 600};
        int[] memoryBlocks3 = {100, 500, 200, 300, 600};

        MemoryAllocation.firstFit(memoryBlocks1, processes);
        MemoryAllocation.bestFit(memoryBlocks2, processes);
        MemoryAllocation.worstFit(memoryBlocks3, processes);

        int[] pageReferenceString = {7, 0, 1, 2, 0, 3, 4, 2, 3, 0, 3, 2};
        int frameCount = 3;
        PagingSimulation.fifoPageReplacement(pageReferenceString, frameCount);
        PagingSimulation.lruPageReplacement(pageReferenceString, frameCount);
    }

    public void printGanttChart(List<Process> processes) {
        System.out.println("\nGantt Chart:");
    
        System.out.print(" ");
        for (Process p : processes) {
            for (int i = 0; i < p.burst_time; i++) {
                System.out.print("--");
            }
            System.out.print(" ");
        }
        System.out.println();
    
        System.out.print("|");
        for (Process p : processes) {
            for (int i = 0; i < p.burst_time - 1; i++) {
                System.out.print(" ");
            }
            System.out.print("P" + p.pid);
            for (int i = 0; i < p.burst_time - 1; i++) {
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println();
    
        System.out.print(" ");
        for (Process p : processes) {
            for (int i = 0; i < p.burst_time; i++) {
                System.out.print("--");
            }
            System.out.print(" ");
        }
        System.out.println();
    
        System.out.print("0");
        for (Process p : processes) {
            for (int i = 0; i < p.burst_time; i++) {
                System.out.print("  ");
            }
            System.out.printf("%2d", p.completionTime);
        }
        System.out.println("\n");
    }    

    public static void main(String[] args){
        ReadFile readFile1 = new ReadFile();
        String fileName = readFile1.getSelectedFile();
        List<Process> processes = readFile1.readFile(fileName);

        FCFS fifo = new FCFS();
        List<Process> sortedProcesses = fifo.sortByArrivalTime(processes);

        System.out.println("\n------------- Sorted by ARRIVAL TIME --------------");
        for (Process p : sortedProcesses){
            System.out.println(p);
        }

        fifo.FCFSAlgorithm(processes);
    }
}
