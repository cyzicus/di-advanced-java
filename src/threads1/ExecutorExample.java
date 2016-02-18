package executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutorExample {
    static class MyCallable implements Callable<String> {
        private static int nextId = 0;
        private int myId = nextId++;

        @Override
        public String call() throws Exception {
            System.out.println("MyCallable " + myId + " starting...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 4000));
            System.out.println("MyCallable " + myId + " finishing...");
            return "MyCallable " + myId + " completed";
        }
    }
    
    private static final int COUNT = 10;
    
    public static void main(String [] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        List<Future<String>> handles = new ArrayList<Future<String>>();
        boolean[] seen = new boolean[COUNT];
        for (int i = 0; i < COUNT; i ++) {
            handles.add(es.submit(new MyCallable()));
        }
        System.out.println("All jobs submitted.");
        while (!allDone(seen)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                // don't care!
            }
            for (int i = 0; i < COUNT; i++) {
                if (!seen[i]) {
                    Future<String> handle = handles.get(i);
                    if (handle.isDone()) {
                        seen[i] = true;
                        try {
                            String result = handle.get();
                            System.out.println("Job returned: " + result);
                        } catch (InterruptedException ex) {
                            System.out.println("Interrupted...");
                        } catch (ExecutionException ex) {
                            System.out.println("Execution threw " + ex.getCause().getMessage());
                        }                           
                    }
                }
            }
        }
        System.out.println("All done, quitting.");
        System.exit(0);
    }
    
    private static boolean allDone(boolean[] seen) {
        for (boolean b : seen) {
            if (!b) return false;
        }
        return true;
    }
}
