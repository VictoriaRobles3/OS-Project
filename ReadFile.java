import java.io.*;

class ReadFile {

    public void readFile(String fileName){
        if(fileName.contains(".txt")){
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
                String line;
                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            System.err.println("Cannot read file. File must be in txt format.");
        }
    }
    public static void main (String[] args){
        ReadFile read1 = new ReadFile();
        read1.readFile("processes.txt");
    }
}
