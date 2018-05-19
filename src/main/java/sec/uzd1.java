package sec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uzd1 {
    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Position: ");
            int pos = Integer.parseInt(br.readLine());

            long i = 1;
            long num = 0;
            int count = 0;

            do {
                if (test(i)) {
                    count++;
                    num = i;
                }
                i++;
            } while (count != pos);

            System.out.println(num);
        } catch (IllegalArgumentException e) {
            System.out.println("input error");
        }
    }

    private static boolean test(long num) {
        boolean jep = true;
        long n = num;
        while (n != 0) {
            num = n;
            n = n / 10;
            if (num % 10 < n % 10) {
                jep = false;
                break;
            }
        }
        return jep;
    }

}
