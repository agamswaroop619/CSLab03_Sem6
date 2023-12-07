import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the specific number to search
        System.out.print("Enter the number to search: ");
        int targetNumber = scanner.nextInt();

        // The filename is assumed to be "output.txt"
        String filename = "output.txt";

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Perform Fibonacci search in the sorted file
        boolean found = performFibonacciSearch(filename, targetNumber);

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

    private static boolean performFibonacciSearch(String filename, int targetNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] numbers = Arrays.stream(line.trim().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                // Fibonacci search requires a sorted array
                Arrays.sort(numbers);

                int foundIndex = fibonacciSearch(numbers, targetNumber);

                if (foundIndex != -1) {
                    return true; // Number found
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return false; // Number not found
    }

    private static int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;
        int fibMMinus2 = 0;
        int fibMMinus1 = 1;
        int fibM = fibMMinus1 + fibMMinus2;

        while (fibM < n) {
            fibMMinus2 = fibMMinus1;
            fibMMinus1 = fibM;
            fibM = fibMMinus1 + fibMMinus2;
        }

        int offset = -1;

        while (fibM > 1) {
            int i = Math.min(offset + fibMMinus2, n - 1);

            if (arr[i] < target) {
                fibM = fibMMinus1;
                fibMMinus1 = fibMMinus2;
                fibMMinus2 = fibM - fibMMinus1;
                offset = i;
            } else if (arr[i] > target) {
                fibM = fibMMinus2;
                fibMMinus1 = fibMMinus1 - fibMMinus2;
                fibMMinus2 = fibM - fibMMinus1;
            } else {
                return i; // Element found
            }
        }

        if (fibMMinus1 == 1 && arr[offset + 1] == target) {
            return offset + 1;
        }

        return -1; // Element not found
    }
}
