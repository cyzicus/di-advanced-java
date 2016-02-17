package threads1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueExample {

    public static void main(String[] args) {

        BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        System.out.println("Starting threads....");
        new Thread(new Producer(arrayBlockingQueue)).start();
        new Thread(new Consumer(arrayBlockingQueue)).start();
        System.out.println("Exiting main");

    }

}
