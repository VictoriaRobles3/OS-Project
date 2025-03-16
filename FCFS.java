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

    public static void main(String[] args){
        ReadFile readFile1 = new ReadFile();
        String fileName = readFile1.getSelectedFile();
        List<Process> processes = readFile1.readFile(fileName);

        FCFS fifo = new FCFS();
        List<Process> sortedProcesses = fifo.sortByArrivalTime(processes);

        System.out.println("\n----- Sorted by ARRIVAL TIME -----");
        for (Process p : sortedProcesses){
            System.out.println(p);
        }
    }
}
