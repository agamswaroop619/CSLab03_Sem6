import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Interpolation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the specific number to search
        System.out.print("Enter the number to search: ");
        int targetNumber = scanner.nextInt();

        // The filename is assumed to be "output.txt"
        String filename = "output.txt";

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Perform Interpolation search in the sorted file
        boolean found = performInterpolationSearch(filename, targetNumber);

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

    private static boolean performInterpolationSearch(String filename, int targetNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] numbers = Arrays.stream(line.trim().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                // Interpolation search requires a sorted array
                Arrays.sort(numbers);

                int foundIndex = interpolationSearch(numbers, targetNumber);

                if (foundIndex != -1) {
                    return true; // Number found
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return false; // Number not found
    }

    private static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {
            int denominator = arr[high] - arr[low];

            // Check for division by zero
            if (denominator == 0) {
                return (arr[low] == target) ? low : -1;
            }

            int pos = low + ((target - arr[low]) * (high - low) / denominator);

            if (arr[pos] == target) {
                return pos; // Element found
            }

            if (arr[pos] < target) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }

        return -1; // Element not found
    }
}
