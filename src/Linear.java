import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Linear {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number to search: ");
        int targetNumber = scanner.nextInt();

        String filename = "output.txt";

        long startTime = System.currentTimeMillis();

        boolean found = performLinearSearch(filename, targetNumber);
        long endTime = System.currentTimeMillis();

        if (found) {
            System.out.println("The number " + targetNumber + " was found in the file.");
        } else {
            System.out.println("The number " + targetNumber + " was not found in the file.");
        }

        long timeTaken = endTime - startTime;
        System.out.println("Time taken: " + timeTaken + " milliseconds");
    }

    private static boolean performLinearSearch(String filename, int targetNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line.trim());
                if (number == targetNumber) {
                    return true;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return false;
    }
}
