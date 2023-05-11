import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CountItUp is a program that deals with using 64-bit integers to display the
 * value of n choose k (nCk) for given n and k without the use of multiple
 * precision features such as BigInteger.
 * 
 * The program works by using the Greatest Common Denominator (GCD) method
 * 
 * 
 * @author Hamzah Alansi, Denzel Lozano
 */
public class CountItUp {

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (args.length > 0) { // Check if a file is provided as a command-line argument
            try {
                sc = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[0]);
                System.exit(1);
            }
        } else { // Otherwise, use standard input
            sc = new Scanner(System.in);
        }

        while (sc.hasNextLine()) { // Read input lines until there are no more
            String[] line = sc.nextLine().split(" ");
            Long n = Long.parseLong(line[0]);
            Long k = Long.parseLong(line[1]);
            long result = nChooseK(n, k); // Calculate the number of combinations (n choose k)
            System.out.println(result); // Output the result
        }

        sc.close();
    }

    // Method to calculate the number of combinations (n choose k)
    public static long nChooseK(long n, long k) {
        if (k == 0 || k == n) { // Base case: if k is 0 or equal to n, return 1 (there is only one combination)
            return 1L;
        } else if (k == 1 || k == n - 1) { // Base case: if k is 1 or equal to n-1, return n (n choose 1 = n and n
                                           // choose n-1 = n)
            return n;
        }

        long numerator = 1L; // Initialize the numerator
        long denominator = 1L; // Initialize the denominator

        for (long i = 1L; i <= k; i++) {
            long gcd = gcd(numerator, i); // Find the greatest common divisor between numerator and i
            numerator /= gcd; // Divide numerator by the greatest common divisor
            long j = (n - i + 1) / (i / gcd); // Calculate j value for the next iteration
            long gcd2 = gcd(denominator, j); // Find the greatest common divisor between denominator and j
            denominator /= gcd2; // Divide denominator by the greatest common divisor
            numerator *= j / gcd2; // Update numerator by multiplying with j divided by the greatest common divisor
        }

        return numerator; // Return the final calculated value
    }

    // Method to calculate the greatest common divisor (GCD) using Euclid's
    // algorithm
    public static long gcd(long a, long b) {
        while (b != 0) { // Continue until b becomes 0
            long t = b;
            b = a % b;
            a = t;
        }
        return a; // Return the calculated GCD
    }

}