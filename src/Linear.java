import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Linear {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the specific number to search
        System.out.print("Enter the number to search: ");
        int targetNumber = scanner.nextInt();

        // The filename is assumed to be "output.txt"
        String filename = "output.txt";

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Perform linear search in the file
        boolean found = performLinearSearch(filename, targetNumber);

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

    private static boolean performLinearSearch(String filename, int targetNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line.trim());
                if (number == targetNumber) {
                    return true; // Number found
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return false; // Number not found
    }
}
