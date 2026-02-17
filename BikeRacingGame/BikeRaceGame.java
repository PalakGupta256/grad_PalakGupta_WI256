import java.util.*;
import java.util.concurrent.*;
import java.time.Duration;
import java.time.LocalDateTime;

class Biker implements Callable<BikerResult> {

    private String name;
    private int totalDistance;

    public Biker(String name, int totalDistance) {
        this.name = name;
        this.totalDistance = totalDistance;
    }

    @Override
    public BikerResult call() throws Exception {

        Random random = new Random();
        int coveredDistance = 0;

        LocalDateTime startTime = LocalDateTime.now();

        while (coveredDistance < totalDistance) {

            int speedTime = 100 + random.nextInt(400);
            Thread.sleep(speedTime);

            coveredDistance += 100;

            if (coveredDistance > totalDistance) {
                coveredDistance = totalDistance;
            }

            System.out.println(name + " covered " + coveredDistance + " meters");
        }

        LocalDateTime endTime = LocalDateTime.now();
        Duration timeTaken = Duration.between(startTime, endTime);

        return new BikerResult(name, startTime, endTime, timeTaken);
    }
}

class BikerResult {
    String name;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Duration timeTaken;

    public BikerResult(String name, LocalDateTime startTime, LocalDateTime endTime, Duration timeTaken) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeTaken = timeTaken;
    }
}

public class BikeRaceGame {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        List<String> bikerNames = new ArrayList<>();

        System.out.println("Enter 10 Biker Names:");

        for (int i = 1; i <= 10; i++) {
            System.out.print("Biker " + i + ": ");
            bikerNames.add(scanner.nextLine());
        }

        System.out.print("Enter Race Distance (in KM): ");
        int distanceKM = scanner.nextInt();

        int totalDistanceMeters = distanceKM * 1000;

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<BikerResult>> futures = new ArrayList<>();

        for (String name : bikerNames) {
            futures.add(executor.submit(new Biker(name, totalDistanceMeters)));
        }

        List<BikerResult> results = new ArrayList<>();

        for (Future<BikerResult> future : futures) {
            results.add(future.get());
        }

        executor.shutdown();

        results.sort(Comparator.comparing(result -> result.timeTaken));

        System.out.println("\n========== FINAL DASHBOARD ==========");
        int rank = 1;

        for (BikerResult result : results) {
            System.out.println("Rank " + rank++);
            System.out.println("Name: " + result.name);
            System.out.println("Start Time: " + result.startTime);
            System.out.println("End Time: " + result.endTime);
            System.out.println("Time Taken: " + result.timeTaken.toSeconds() + " seconds");
            System.out.println("----------------------------------");
        }

        scanner.close();
    }
}
