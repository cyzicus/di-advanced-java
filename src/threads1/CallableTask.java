package threads1;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

    final private String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println(name);
        Thread.sleep(200);
        System.out.println(name + "...goodbye");
        return name;
    }
}
