package sec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uzd4 {
    private static int count;

    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Input options: ");
            String[] options = br.readLine().split("\\s");

            if (options.length != 4) throw new IllegalArgumentException();

            int M = Integer.parseInt(options[0]);
            if (M < 0 || M > 100) throw new IllegalArgumentException();

            double N = Double.parseDouble(options[1]);
            if (N < 0 || N > 1) throw new IllegalArgumentException();

            double K = Double.parseDouble(options[2]);
            if (K < 1) throw new IllegalArgumentException();

            int B = Integer.parseInt(options[3]);
            int hol = B;

            double start;
            int i = 0;
            int m = 1;
            boolean test = false;

            do {
                start = M;
                count = 0;

                if (test) {
                    hol = B - 1;
                    test = false;
                }

                for (int n = 0; n < i; n++) {
                    double prog = M * N;
                    for (int j = 1; j <= m; j++) {
                        double temp = M * N;
                        for (int k = 0; k < j; k++) {
                            temp = temp / K;
                        }
                        prog += temp;
                    }
                    start += prog;
                    count += 2 + m;

                }
                if (start >= 100)
                    break;
                if (hol == -1) {
                    i = 0;
                    test = true;
                    m++;
                }
                i++;
            } while (rekurs(start, M * N, hol--) < 100);

            System.out.println(count - 1);


        } catch (IllegalArgumentException e) {
            System.out.println("Input error");
        }

    }

    private static double rekurs(double M, double progress, int B) {
        if (B >= 0) {
            if (M < 100) {
                count += 2;
                M = rekurs(M + progress, progress, B - 1);
            }
        }
        if (M > 100)
            return M;

        return M;
    }

}
