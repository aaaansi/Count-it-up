import java.util.Scanner;



public class CountItUp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();
        long deduction = n - k;
        System.out.println(factorial(n) / (factorial(deduction) * factorial(k)));
        //System.out.println((int) factorial(52));
        //System.out.println(factorial(5));
        //System.out.println(factorial(52) / factorial(5));

        double value = 8.0658175e+67;
        long[] arrayValue = storeValue(value);
        long divisor = 120;
        //long[] quotient = divide(arrayValue, divisor);
        // long[] arrayValue = { 80658175000000000000000000000000000000000000000000000000000000000000000000L };
        long[] quotientArray = divide(arrayValue, 120);
        long quotient = convertToLong(quotientArray);
        //System.out.println(quotient);
        sc.close();
    }

    public static long factorial(long n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

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

    public static long convertToLong(long[] array) {
        long result = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            result = result * (long) Math.pow(10, 9) + array[i];
        }
        return result;
    }

}
