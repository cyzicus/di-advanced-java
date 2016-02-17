package threads1;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class CallableTask implements Callable<String> {

    final private String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println(name + "...says hello");
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 4000));
        System.out.println(name + "...goodbye");
        return name;
    }
}
