package threads1;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class CallableExample {

    public static void main(String[] args) {

        List<CallableTask> allCallables = asList(
                new CallableTask("Freddy"),
                new CallableTask("Farkle"),
                new CallableTask("Scooby"),
                new CallableTask("Daphne"),
                new CallableTask("Velma"),
                new CallableTask("Shaggy"),
                new CallableTask("Hot Dog Water"),
                new CallableTask("Scrappy")
        );

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> futures = allCallables.stream()
                .map(callableTask -> executorService.submit(callableTask))
                .collect(Collectors.toList());

        futures.forEach(future -> {
            try {
                System.out.println(">> " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });


    }

}
