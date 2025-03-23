import java.util.*;

public class MemoryAllocation {

    // First-Fit Allocation
    public static void firstFit(int[] memoryBlocks, List<Process> processes) {
        int[] allocation = new int[processes.size()];
        Arrays.fill(allocation, -1);
        int[] blocksCopy = Arrays.copyOf(memoryBlocks, memoryBlocks.length);

        for (int i = 0; i < processes.size(); i++) {
            int memReq = processes.get(i).memory_requirement;
            for (int j = 0; j < blocksCopy.length; j++) {
                if (blocksCopy[j] >= memReq) {
                    allocation[i] = j;
                    blocksCopy[j] -= memReq; 
                    break;
                }
            }
        }
        System.out.println("\n--- First-Fit Allocation ---");
        printAllocation(processes, allocation);
    }

    // Best-Fit Allocation
    public static void bestFit(int[] memoryBlocks, List<Process> processes) {
        int[] allocation = new int[processes.size()];
        Arrays.fill(allocation, -1);
        int[] blocksCopy = Arrays.copyOf(memoryBlocks, memoryBlocks.length);

        for (int i = 0; i < processes.size(); i++) {
            int memReq = processes.get(i).memory_requirement;
            int bestIdx = -1;
            for (int j = 0; j < blocksCopy.length; j++) {
                if (blocksCopy[j] >= memReq) {
                    if (bestIdx == -1 || blocksCopy[j] < blocksCopy[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }
            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blocksCopy[bestIdx] -= memReq;
            }
        }
        System.out.println("\n--- Best-Fit Allocation ---");
        printAllocation(processes, allocation);
    }

    // Worst-Fit Allocation
    public static void worstFit(int[] memoryBlocks, List<Process> processes) {
        int[] allocation = new int[processes.size()];
        Arrays.fill(allocation, -1);
        int[] blocksCopy = Arrays.copyOf(memoryBlocks, memoryBlocks.length);

        for (int i = 0; i < processes.size(); i++) {
            int memReq = processes.get(i).memory_requirement;
            int worstIdx = -1;
            for (int j = 0; j < blocksCopy.length; j++) {
                if (blocksCopy[j] >= memReq) {
                    if (worstIdx == -1 || blocksCopy[j] > blocksCopy[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blocksCopy[worstIdx] -= memReq;
            }
        }
        System.out.println("\n--- Worst-Fit Allocation ---");
        printAllocation(processes, allocation);
    }

    // Print allocation results
    private static void printAllocation(List<Process> processes, int[] allocation) {
        System.out.println("Process\tMemReq\tBlock");
        for (int i = 0; i < processes.size(); i++) {
            System.out.printf("P%d\t%d\t%s\n", processes.get(i).pid,
                    processes.get(i).memory_requirement,
                    allocation[i] != -1 ? String.valueOf(allocation[i] + 1) : "Not Allocated");
        }
    }
}
