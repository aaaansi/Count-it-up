import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountItUp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
            String[] line = sc.nextLine().split(" ");
            Long n = Long.parseLong(line[0]);
            Long k = Long.parseLong(line[1]);
            long result = nChooseK(n, k);
            System.out.println(result);
        }
    }

    public static long nChooseK(long n, long k) {
        if (k == 0 || k == n) {
            return 1L;
        } else if (k == 1 || k == n - 1) {
            return n;
        }

        long numerator = 1L;
        long denominator = 1L;

        for (long i = 1L; i <= k; i++) {
            long gcd = gcd(numerator, i);
            numerator /= gcd;
            long j = (n - i + 1) / (i / gcd);
            long gcd2 = gcd(denominator, j);
            denominator /= gcd2;
            numerator *= j / gcd2;
        }

        return numerator;
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

}