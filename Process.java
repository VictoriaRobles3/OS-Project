public class Process {
    int pid;
    int arrival_time;
    int burst_time;
    int priority;

    public Process(int pid, int arrival_time, int burst_time, int priority){
        this.pid = pid;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
        this.priority = priority;
    }

    public String toString(){
        return "PID: " + pid + ", Arrival Time: " + arrival_time + ", Burst Time: " + burst_time + ", Priority: " + priority;
    }
}
