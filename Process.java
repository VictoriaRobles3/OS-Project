public class Process {
    int pid;
    int arrival_time;
    int burst_time;
    int priority;
    int waitingTime;
    int turnAroundTime;
    int completionTime;
    int memory_requirement;

    public Process(int pid, int arrival_time, int burst_time, int priority, int memory_requirement){
        this.pid = pid;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
        this.priority = priority;
        this.memory_requirement = memory_requirement;
    }

    public String toString(){
        return "PID: " + pid + ", Arrival Time: " + arrival_time + ", Burst Time: " + burst_time + ", Priority: " + priority + ", Memory Requirement: " + memory_requirement;
    }
}
