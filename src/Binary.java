import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Binary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the specific number to search
        System.out.print("Enter the number to search: ");
        int targetNumber = scanner.nextInt();

        // The filename is assumed to be "output.txt"
        String filename = "output.txt";

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Perform binary search in the sorted file
        boolean found = performBinarySearch(filename, targetNumber);

        // Record the end time
        long endTime = System.currentTimeMillis();

        if (found) {
            System.out.println("The number " + targetNumber + " was found in the file.");
        } else {
            System.out.println("The number " + targetNumber + " was not found in the file.");
        }

        // Calculate and print the time taken
        long timeTaken = endTime - startTime;
        System.out.println("Time taken: " + timeTaken + " milliseconds");
    }

    private static boolean performBinarySearch(String filename, int targetNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] numbers = Arrays.stream(line.trim().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                // Binary search requires a sorted array
                Arrays.sort(numbers);

                int foundIndex = Arrays.binarySearch(numbers, targetNumber);

                if (foundIndex >= 0) {
                    return true; // Number found
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return false; // Number not found
    }
}
