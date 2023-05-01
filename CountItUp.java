import java.util.Arrays;
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
    public static final int SIZE = 100000;

    public static void main(String[] args) {
        Scanner scanner = null;
        int n;
        int k;
        int[] arrN = new int[SIZE];
        int[] arrK = new int[SIZE];
        Arrays.fill(arrN, 0);
        Arrays.fill(arrK, 0);
        arrN[0] = 1;
        arrK[0] = 1;
        // System.out.println(Long.MAX_VALUE);
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
            n = Integer.parseInt(parts[0]);
            k = Integer.parseInt(parts[1]);

            // First for loop cycle to calculate factorial of n
            for (int i = 1; i <= n; i++) {
                factorial(arrN, i); // arr = arr * i. Initially arr was 1.
            }
            print(arrN);

            // Second for loop cycle to calculate factorial of k
            for (int i = 1; i <= k; i++) {
                factorial(arrK, i); // arr = arr * i. Initially arr was 1.
            }
            print(arrK);

        }
        scanner.close();
    }

    public static void factorial(int[] arr, int num) {
        int carry = 0;
        for (int i = 0; i < SIZE; i++) {
            int product = arr[i] * num + carry;
            arr[i] = product % 10;
            carry = product / 10;
        }

        // If there is any carry left after multiplying with the number,
        // keep multiplying with the carry until it becomes zero.
        while (carry != 0) {
            int product = arr[SIZE - 1] * carry;
            arr[SIZE - 1] = product % 10;
            carry = product / 10;

            // Shift the digits to the right to make space for the new digits.
            for (int i = SIZE - 2; i >= 0; i--) {
                product = arr[i] * carry;
                arr[i + 1] += product % 10;
                carry = product / 10;
                arr[i] = arr[i + 1] / 10;
                arr[i + 1] %= 10;
            }
        }
    }

    // This module prints the number stored in arr. Maximum size can be SIZE.
    public static void print(int[] arr) {
        int flag = 0;

        for (int i = SIZE - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                flag = 1;
            }

            if (flag == 1) {
                System.out.print(arr[i]);
            }
        }

        System.out.println();
    }

    // factorial method takes a long parameter n, and returns the factorial of that
    // number as a long type using recursion to calculate the factorial.
    public static long basicFactorial(long n) {
        long rs = 1;
        if (n < 2)
            return 1;
        for (int i = 2; i <= n; i++) {
            rs *= i;
            System.out.println(rs);
        }
        return rs;
    }

}
