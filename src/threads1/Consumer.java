package threads1;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    final private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10_000; i++) {
            try {
                int currentlyTaken = (int) queue.take();
                if(currentlyTaken == i) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                else {
                    System.out.println("Something went horribly wrong!!!!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
