package threads1;

/**
 * Created by jason1.miller on 2/17/16.
 */
public class ThreadsAndRunnables {

    public static void main(String[] args) {
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println(Thread.currentThread().getName() + " i is " + i);
//                }
//            }
//        };

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " i is " + i);
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        System.out.println("Main thread is called " + Thread.currentThread().getName());
        t1.start();
        t2.start();
        System.out.println("Main exiting...");
    }
}
