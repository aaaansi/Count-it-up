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

            Arrays.fill(arrN, 0);
            arrN[0] = 1;
            // First for loop cycle to calculate factorial of n
            for (int i = 1; i <= n; i++) {
                factorial(arrN, i); // arr = arr * i. Initially arr was 1.
            }
            System.out.println(Long.MAX_VALUE);
            System.out.println("-------");
            // print(arrN);

            Arrays.fill(arrK, 0);
            arrK[0] = 1;
            // Second for loop cycle to calculate factorial of k
            for (int i = 1; i <= k; i++) {
                factorial(arrK, i); // arr = arr * i. Initially arr was 1.
            }
            System.out.println(toStringValue(arrN));
            System.out.println(toStringValue(arrK));

            System.out.println("-----------");

            System.out.println(multiply(toStringValue(arrK), toStringValue(arrN)));
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

    public static String longDivision (String numeratior, String denominator){

        // Convert the input strings to character arrays
        char[] numArr = numerator.toCharArray();
        char[] denomArr = denominator.toCharArray();

        // Initialize variables for the long division algorithm
        int numIndex = 0;
        StringBuilder quotient = new StringBuilder();
        long remainder = 0;

        // Loop through the digits of the numerator
        while (numIndex < numArr.length){
            // Append the next digit to the remainder
            remainder = remainder * 10 + Character.getNumericValue(numArr[numIndex]);
            numIndex++;

            // Perform long division if there is enough room in the remainder
            if (remainder >= Long.parseLong(denominator)){
                
                // Divide the remainder by the denominator
                long div = 0;
                while (remainder >= Long.parseLong(denominator)){
                    remainder -= Long.parseLong(denominator);
                    div++;
                }
                quotient.append(div);
            } else {
                quotient.append('0');
            }
        }

        // Remove leading zeroes from the quotient
        int i = 0;
        while (i < quotient.length() && quotient.charAt(i) == '0'){
            i++;
        }
        if (i == quotient.length()){
            return "0";
        } else {
            return quotient.substring(i);
        }
        

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
