package threads1;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    final private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10_000; i++) {
            try {
                queue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
