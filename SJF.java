/* Shortest Job First (SJF) Algorithm  
 * This is a non-preemptive scheduling algorithm that selects the process with the shortest burst time next.  
 * If multiple processes arrive at the same time, the one with the shortest burst time is chosen first.  
 * The algorithm sorts processes by arrival time first, then by burst time.  
 *  
 * This file will sort the processes based on the algorithm.
 * Calculate the Waiting time and Turnaround Time for each process.
 * Calculate the average WT and TAT.
 */


import java.util.*;


class SJF {


    public void SJFAlgorithm(List<Process> processes) {
   
        processes.sort(Comparator.comparingInt(p -> p.arrival_time));


        int currentTime = 0;
        int totalWT = 0, totalTAT = 0;
        List<Process> completedProcesses = new ArrayList<>();
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.burst_time));


        System.out.println("\n------------------- Shortest Job First (SJF) Scheduling -------------------");


        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
         
            while (!processes.isEmpty() && processes.get(0).arrival_time <= currentTime) {
                readyQueue.add(processes.remove(0));
            }


           
            if (readyQueue.isEmpty()) {
                currentTime = processes.get(0).arrival_time;
                continue;
            }


       
            Process currentProcess = readyQueue.poll();
            currentProcess.waitingTime = currentTime - currentProcess.arrival_time;
            currentProcess.completionTime = currentTime + currentProcess.burst_time;
            currentProcess.turnAroundTime = currentProcess.completionTime - currentProcess.arrival_time;
            currentTime += currentProcess.burst_time;


           
            totalWT += currentProcess.waitingTime;
            totalTAT += currentProcess.turnAroundTime;


     
            completedProcesses.add(currentProcess);
        }


   
        System.out.printf("%-5s %-10s %-10s %-10s %-15s %-15s\n",
            "PID", "Arrival", "Burst", "Start", "Waiting Time", "Turnaround Time");
        for (Process p : completedProcesses) {
            System.out.printf("%-5d %-10d %-10d %-10d %-15d %-15d\n",
                p.pid, p.arrival_time, p.burst_time, p.arrival_time + p.waitingTime, p.waitingTime, p.turnAroundTime);
        }


     
        double averageWT = (double) totalWT / completedProcesses.size();
        double averageTAT = (double) totalTAT / completedProcesses.size();
        System.out.println("\n--------------- AVERAGES ----------------");
        System.out.printf("Average Waiting Time: %.2f\n", averageWT);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTAT);
        System.out.println("----------------------------------------------------------------------");
    }


    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        String fileName = readFile.getSelectedFile();
        List<Process> processes = readFile.readFile(fileName);


        SJF sjf = new SJF();
        sjf.SJFAlgorithm(processes);
    }
}



