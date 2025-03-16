import java.io.*;
import java.util.*;

class ReadFile {

    public List<Process> readFile(String fileName){
        List<Process> processList = new ArrayList<>();

        if(fileName.endsWith(".txt")){
            System.out.println("\nReading " + fileName + " file: ");
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

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, select the file number and press Enter: ");
        System.out.println("1. processes.txt");
        System.out.println("2. process.ppt");
        System.out.println("3. sampleData2.txt");
        int input = sc.nextInt();
        System.out.println("You chose: " + input);
        sc.close();
        List<Process> processes = new ArrayList<>();
        switch(input){
            case 1:
                processes = read1.readFile("processes.txt");
                break;
            case 2: 
                processes = read1.readFile("process.ppt");
                break;
            case 3: 
                processes = read1.readFile("sampleData2.txt");
                break;
            default:
                System.err.println("Not a valid option. Try again.");
        }

        //List<Process> processes = read1.readFile(input);

        for(Process p: processes){
            System.out.println(p);
       }
    }
}
