package threads1;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    final private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10_000; i++) {
            try {
                queue.put(i);
//              Could also be written as:
//              Integer value = i;
//              queue.put(value);value=null; It's a good idea to set any object you 'put' to null
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
