import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * CountItUp is a program that deals with counting factorials of given numbers
 * without the use of a multiple precision feature such as BigInteger.
 * 
 * @author Hamzah Alansi, Denzel Lozano
 */
public class CountItUp {
    public static void main(String[] args) {
        Scanner scanner = null;
        if (args.length > 0) {
            try {
                scanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[0]);
                System.exit(1);
            }
        } else { // otherwise, use standard input
            scanner = new Scanner(System.in);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int n = Integer.parseInt(parts[0]);
            int k = Integer.parseInt(parts[1]);
            long deduction = n - k;
            // System.out
            // .println(n + " " + k + " = " + factorial(n) / (factorial(deduction) *
            // factorial(k)));
            System.out
                    .println(factorial(n) / (factorial(deduction) * factorial(k)));
        }
        scanner.close();
    }

    // factorial method takes a long parameter n, and returns the factorial of that
    // number as a long type.
    public static long factorial(long n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /*
     * storeValue method that takes a parameter value of type double and returns an
     * array of long.
     * The method is supposed to store values of the factorial calculation that are
     * greater than 64bits in an array of long
     * 
     */
    public static long[] storeValue(double value) {
        long[] result = new long[3];
        long base = 1000000000L; // 10^9, the base for each element in the array
        long longValue = (long) value;
        double remainder = value - longValue;

        // Store the integer portion of the value
        for (int i = 0; i < 2 && longValue > 0; i++) {
            result[i] = longValue % base;
            longValue /= base;
        }

        // Store the decimal portion of the value
        if (remainder > 0) {
            String decimalString = String.format("%.15f", remainder);
            decimalString = decimalString.substring(2); // Remove "0." prefix
            int numElements = (int) Math.ceil(decimalString.length() / 9.0);
            for (int i = 0; i < numElements; i++) {
                int endIndex = decimalString.length() - (i * 9);
                int startIndex = Math.max(0, endIndex - 9);
                String elementString = decimalString.substring(startIndex, endIndex);
                result[i + 2] = Long.parseLong(elementString);
            }
        }

        return result;
    }

    /*
     * divide method takes 2 parameters an array of long called value which contains
     * the stored value of the factorial result greater than 64 bits and divides it
     * the value of another factorial (the reason the divisior is not an array
     * because im not assuming the factorial of the divisior is greater than 64
     * bits)
     */
    public static long[] divide(long[] value, long divisor) {
        long[] quotient = new long[value.length];
        long remainder = 0;
        for (int i = value.length - 1; i >= 0; i--) {
            long numerator = (remainder * (long) Math.pow(10, 9)) + value[i];
            quotient[i] = numerator / divisor;
            remainder = numerator % divisor;
        }
        return quotient;
    }

    /*
     * convertToLong method takes an array of long type as parameter and returns a
     * long.
     * This method is supposed to convert the values of the factorial stored in the
     * long array into readable numbers after the division
     */
    public static long convertToLong(long[] array) {
        long result = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            result = result * (long) Math.pow(10, 9) + array[i];
        }
        return result;
    }

}
