import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("Enter the number of random integers to generate: ");
        int n = new Scanner(System.in).nextInt();  // Using scanner directly

        String filename = "output.txt";

        generateAndSaveRandomIntegers(n, filename);
    }

    private static void generateAndSaveRandomIntegers(int n, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Random random = new Random();

            for (int i = 0; i < n; i++) {
                int randomNumber = random.nextInt();
                writer.write(Integer.toString(randomNumber));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
