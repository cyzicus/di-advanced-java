package threads1;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    final private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10_000; i++) {
            try {
                int currentlyTaken = queue.take();
                if(currentlyTaken != i) {
                    System.out.println("Something went horribly wrong!!!! - " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
