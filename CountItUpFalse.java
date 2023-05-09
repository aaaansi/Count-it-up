import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountItUpFalse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long result = 0;
        if (args.length > 0) {
            try {
                sc = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[0]);
                System.exit(1);
            }

        } else { // otherwise, use standard input
            sc = new Scanner(System.in);
        }
        while (sc.hasNextLine()) {
            String[] input = sc.nextLine().split(" ");
            long n = Long.parseLong(input[0]);
            long k = Long.parseLong(input[1]);

            result = binomial(n, k);
            System.out.println(result);
        }

        sc.close();
    }

    private static long binomial(long n, long k) {
        if (k > n - k) {
            k = n - k;
        }

        long res = 1;

        for (long i = 0; i < k; i++) {
            res *= (n - i);
            res /= (i + 1);
        }

        return res;
    }
}