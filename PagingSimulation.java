import java.util.*;

public class PagingSimulation {

    // FIFO Page Replacement
    public static void fifoPageReplacement(int[] pages, int frameCount) {
        Queue<Integer> memory = new LinkedList<>();
        int pageFaults = 0;
        System.out.println("\n--- FIFO Page Replacement ---");
        for (int page : pages) {
            if (!memory.contains(page)) {
                if (memory.size() == frameCount) {
                    memory.poll();
                }
                memory.add(page);
                pageFaults++;
            }
            System.out.println("Memory Frames: " + memory);
        }
        System.out.println("Total Page Faults: " + pageFaults);
    }

    // LRU Page Replacement
    public static void lruPageReplacement(int[] pages, int frameCount) {
        List<Integer> memory = new ArrayList<>();
        int pageFaults = 0;
        System.out.println("\n--- LRU Page Replacement ---");
        for (int page : pages) {
            if (!memory.contains(page)) {
                if (memory.size() == frameCount) {
                    // Remove the least recently used page (first element)
                    memory.remove(0);
                }
                memory.add(page);
                pageFaults++;
            } else {
                // Move the used page to the end to mark it as recently used
                memory.remove(Integer.valueOf(page));
                memory.add(page);
            }
            System.out.println("Memory Frames: " + memory);
        }
        System.out.println("Total Page Faults: " + pageFaults);
    }
}
