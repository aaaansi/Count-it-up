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
        int deduction;
        int[] arrN = new int[SIZE];
        int[] arrK = new int[SIZE];
        int[] arrDeduction = new int[SIZE];
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
            try {
                n = Integer.parseInt(parts[0]);
                k = Integer.parseInt(parts[1]);
                deduction = n - k;
                Arrays.fill(arrN, 0);
                arrN[0] = 1;
                // First for loop cycle to calculate factorial of n
                for (int i = 1; i <= n; i++) {
                    factorial(arrN, i); // arr = arr * i. Initially arr was 1.
                }
                // System.out.println(Long.MAX_VALUE);
                // System.out.println(Integer.MAX_VALUE);
                // System.out.println("-------");
                // print(arrN);

                Arrays.fill(arrK, 0);
                arrK[0] = 1;
                // Second for loop cycle to calculate factorial of k
                for (int i = 1; i <= k; i++) {
                    factorial(arrK, i); // arr = arr * i. Initially arr was 1.
                }
                Arrays.fill(arrDeduction, 0);
                arrDeduction[0] = 1;
                // First for loop cycle to calculate factorial of n
                for (int i = 1; i <= deduction; i++) {
                    factorial(arrDeduction, i); // arr = arr * i. Initially arr was 1.
                }
            } catch (NumberFormatException e) {
                System.err.println("Not a valid integer");
            }

            String multiple = multiply(toStringValue(arrN), toStringValue(arrK));
            // String minus = subtract(toStringValue(arrN), toStringValue(arrK));
            // String divison = longDivision(toStringValue(arrN), toStringValue(arrK));
            // String result = longDivision(toStringValue(arrN),
            // multiply(toStringValue(arrK), toStringValue(arrDeduction)));
            // System.out.println(toStringValue(arrN));
            // System.out.println(multiple);
            // System.out.println(minus);
            // System.out.println(divison);
            // System.out.println(result);
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

    public static String multiply(String num1, String num2) {
        String a = num1;
        String b = num2;
        if (a.equals("0") || b.equals("0")) {
            System.out.println("0");
            return "0";
        }
        int m = a.length() - 1, n = b.length() - 1,
                carry = 0;
        String product = "";
        for (int i = 0; i <= m + n || carry != 0; ++i) {
            for (int j = Math.max(0, i - n); j <= Math.min(i, m); ++j) {
                carry += (a.charAt(m - j) - '0')
                        * (b.charAt(n - i + j) - '0');
            }
            product += (char) (carry % 10 + '0');
            carry /= 10;
        }
        product = new StringBuilder(product)
                .reverse()
                .toString();
        // System.out.println("The Product is: " + product);
        return product;
    }

    // This module prints the number stored in arr. Maximum size can be SIZE.
    public static String toStringValue(int[] arr) {
        int flag = 0;
        String val = "";
        for (int i = SIZE - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                flag = 1;
            }

            if (flag == 1) {
                // System.out.print(arr[i]);
                val += arr[i];
            }
        }

        // System.out.println();
        return val;
    }
}