package sec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class uzd3 {
    private static char[] num;
    private static boolean bb = false;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Input number: ");
            String[] temp = br.readLine().split("\\s");
            if (temp.length > 128) throw new IOException();
            num = new char[temp.length];

            for (int i = 0; i < num.length; i++) {
                num[i] = temp[i].charAt(0);
                if (!temp[i].matches("[^a-z]|[^0-9]"))
                    throw new IOException();
            }

            while (!bb) {
                int i = pos(num);
                if (bb) sort(i);
                else break;
            }

            for (char aNum : num) {
                System.out.print(aNum + "\t");
            }
        } catch (IOException e) {
            System.out.println("input error");
        }
    }

    private static void sort(int i) {
        char temp;
        int n;
        int j;
        for (n = i; n < num.length; n++) {
            temp = num[n];
            j = n - 1;
            while (j >= (i - 1) && num[j] > temp) {
                num[j + 1] = num[j];
                j--;
            }
            num[j + 1] = temp;
        }
    }

    private static int pos(char[] num) {
        int i = num.length;
        char temp;
        while (i - 2 >= 0) {
            if (num[i - 2] < num[i - 1]) {
                temp = num[i - 2];
                num[i - 2] = num[i - 1];
                num[i - 1] = temp;
                bb = true;
                break;
            }
            i--;
        }
        return i;
    }

}
