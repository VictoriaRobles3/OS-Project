import java.io.*;
import java.util.*;

class ReadFile {

    public List<Process> readFile(String fileName){
        List<Process> processList = new ArrayList<>();

        if(fileName.endsWith(".txt")){
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
                String line;
                br.readLine();
                while((line = br.readLine()) != null){
                    String[] fields = line.trim().split("\\s+");
                    if(fields.length == 4){
                        int pid = Integer.parseInt(fields[0]);
                        int arrival_time = Integer.parseInt(fields[1]);
                        int burst_time = Integer.parseInt(fields[2]);
                        int priority = Integer.parseInt(fields[3]);

                        Process process = new Process(pid, arrival_time, burst_time, priority);
                        processList.add(process);
                    } 
                    else{
                        System.err.println("Invalid format: " + line);
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            System.err.println("Cannot read file. File must be in txt format.");
        }
        return processList;
    }
    public static void main (String[] args){
        ReadFile read1 = new ReadFile();
        List<Process> processes = read1.readFile("processes.txt");

        for(Process p: processes){
            System.out.println(p);
        }
    }
}
