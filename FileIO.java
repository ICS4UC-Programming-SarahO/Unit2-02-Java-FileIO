import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reads integers from input.txt line by line, sums each line,
 * and writes the result (or an error message) to output.txt.
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
            // Locate the input file in the program's working directory.
            File inputFile = new File("input.txt");

            if (!inputFile.exists()) {
                // Create the input file with sample values when it is missing.
                FileWriter starter = new FileWriter(inputFile);
                starter.write("1\n2\n3\n4\n-3\n2\n0\n");
                starter.close();
            }

            // Open the input file and prepare the output file for new results.
            Scanner input = new Scanner(inputFile);
            FileWriter output = new FileWriter("output.txt");

            while (input.hasNextLine()) {
                // Read one line and remove whitespace from both ends.
                String line = input.nextLine().trim();

                if (line.isEmpty()) {
                    // Report empty lines without attempting to parse them.
                    output.write("This line is empty :c \n");
                    continue;
                }

                // Separate the line into individual values for validation.
                String[] pieces = line.split(" ");
                int sum = 0;
                String invalidValue = null;

                for (String piece : pieces) {
                    try {
                        // Convert each value to an integer before adding it.
                        sum += Integer.parseInt(piece);
                    } catch (NumberFormatException error) {
                        // Remember the first value that cannot be parsed.
                        invalidValue = piece;
                        break;
                    }
                }

                if (invalidValue == null) {
                    // Write the sum when every value on the line is valid.
                    output.write(sum + "\n");
                } else {
                    // Explain which value caused the line to be rejected.
                    output.write("Error: \"" + invalidValue
                        + "\" is not a valid integer\n");
                }
            }

            // Close both streams after all input lines have been processed.
            input.close();
            output.close();
        } catch (IOException error) {
            // Report file operation errors to the console.
            System.out.println("File error: " + error.getMessage());
        }
    }
}
