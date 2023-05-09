import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountItUpFalse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        long n = Long.parseLong(input[0]);
        long k = Long.parseLong(input[1]);
        sc.close();

        long result = binomial(n, k);

        System.out.println(result);
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