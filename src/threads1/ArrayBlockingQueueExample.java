package threads1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by jason1.miller on 2/17/16.
 */
public class ArrayBlockingQueueExample {

    public static void main(String[] args) {

        BlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<Integer>(10);

        Producer producer = new Producer(arrayBlockingQueue);

        Consumer consumer = new Consumer(arrayBlockingQueue);

        Thread p = new Thread(producer);
        Thread c = new Thread(consumer);

        System.out.println("Starting threads....");
        p.start();
        c.start();
        System.out.println("Exiting main");

    }

}
