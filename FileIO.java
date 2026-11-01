import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reads integers from input.txt line by line, reports invalid lines,
 * and writes the total sum of all valid integers to output.txt.
 *
 * @author Sarah OUAMOU
 * @version 1.0
 * @since 2026-11-01
 */
public final class FileIO {

    /**
     * Prevents instantiation because this class only contains the entry point.
     */
    private FileIO() {
    }

    /**
     * Main entry point for file IO processing.
     *
     * @param args Command-line arguments.
     */
    public static void main(final String[] args) {
        try {
            // Locate the input file and open the output writer.
            File inputFile = new File("input.txt");
            FileWriter output = new FileWriter("output.txt");

            // Open the input file for reading.
            Scanner input = new Scanner(inputFile);

            // Track total sum across all lines in the file.
            int totalSum = 0;

            while (input.hasNextLine()) {
                // Read one line and remove whitespace from both ends.
                String line = input.nextLine().trim();

                if (line.isEmpty()) {
                    // Skip blank lines because they contain no numbers to add.
                    continue;
                }

                // Separate the line into individual values for validation.
                String[] pieces = line.split(" ");

                for (String piece : pieces) {
                    try {
                        // Add valid integers to the running total.
                        totalSum += Integer.parseInt(piece);
                    } catch (NumberFormatException error) {
                        // Immediately report invalid inputs to output.txt.
                        output.write(piece + " is not a valid int\n");
                    }
                }
            }

            // Write the overall sum at the end of the file.
            output.write(totalSum + "\n");

            // Close both streams after all input lines have been processed.
            input.close();
            output.close();
        } catch (IOException error) {
            // Report file operation errors to the console.
            System.out.println("File error: " + error.getMessage());
        }
    }
}
