package sec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uzd5 {

    private static int[][] field;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Input size: ");
            String temp = br.readLine();
            String[] size = temp.split("\\s");
            if (size.length == 2)
                field = new int[Integer.parseInt(size[1])][Integer.parseInt(size[0])];
            else
                throw new IllegalArgumentException();

            if (field.length < 3 || field[0].length < 4)
                throw new IllegalArgumentException();

            System.out.print("Input line: ");
            temp = br.readLine();
            String[] line = temp.split("\\s");


            figure(line);

        } catch (IllegalArgumentException e) {
            System.out.println("Input error");
        }

    }

    private static int[][] rote(int[][] figure) {
        int x = figure[0].length;
        int y = figure.length;
        int[][] temp = new int[x][y];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                temp[i][j] = figure[temp[i].length - 1 - j][i];
            }
        }
        return temp;
    }

    private static void figure(String[] figure) {
        try {
            boolean first = true;
            loop:
            for (String aFigure : figure) {
                int data = Integer.parseInt(aFigure);

                switch (data) {
                    case 1:
                        int[][] figure1 = {{1, 1, 1, 1}};
                        place(figure1, 0, 1);
                        break;
                    case 2:
                        int[][] figure2 = {{0, 2, 0}, {2, 2, 2}};
                        if (first) place(figure2, 0, 1);
                        else place(rote(rote(figure2)), 0, 1);
                        break;
                    case 3:
                        int[][] figure3 = {{0, 0, 3}, {3, 3, 3}};
                        place(figure3, 0, 1);
                        break;
                    case 4:
                        int[][] figure4 = {{4, 0, 0}, {4, 4, 4}};
                        place(figure4, 0, 1);
                        break;
                    case 5:
                        int[][] figure5 = {{5, 5}, {5, 5}};
                        place(figure5, 0, 1);
                        break;
                    case 6:
                        int[][] figure6 = {{0, 6, 6}, {6, 6, 0}};
                        if (first)place(figure6, 0, 1);
                        else place(rote(figure6), 0, 1);
                        break;
                    case 7:
                        int[][] figure7 = {{7, 7, 0}, {0, 7, 7}};
                        place(figure7, 0, 1);
                        break;
                    default:
                        break loop;

                }
                first = false;
            }
            fieldPrint(field);
        } catch (IllegalArgumentException e) {
            System.out.println("Input error");
        }
    }

    private static void fieldPrint(int[][] field) {
        for (int[] aField : field) {
            System.out.print("|");
            for (int j = 0; j < aField.length - 1; j++) {
                if (aField[j] == 0)
                    System.out.print(" ");
                else
                    System.out.print(aField[j]);
            }
            if ((aField[aField.length - 1] == 0))
                System.out.println(" |");
            else
                System.out.println(aField[aField.length - 1] + "|");
        }
        System.out.print("+");
        for (int i = 0; i < field[0].length; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println();
    }

    private static void place(int[][] figure, int count, int kk) {
        if (count < 4) {
            int countFig = 0;
            int countField = 0;
            int temp = 0;
            boolean found = false;
            int cc = 0;

            for (int i = 0; i < figure[0].length; i++) {
                if (figure[figure.length - 1][i] != 0)
                    countFig++;
            }

            loop:
            for (int n = temp; n < field[0].length; n++) {
                for (int i = n; i <= field[0].length; i++) {
                    if (countField == countFig) {
                        temp = i - countFig;
                        if (figure[figure.length - 1][0] == 0) {
                            for (int m = 0; m < figure[0].length; m++) {
                                if (figure[figure.length - 1][m] == 0) {
                                    cc++;
                                } else
                                    break;
                            }
                            found = testFigure(kk - 1, temp - cc, figure);
                        } else
                            found = testFigure(kk - 1, temp, figure);

                        if (found) break loop;
                        else {
                            n = temp++;
                            countField=0;
                            break;
                        }

                    }

                    if (i < field[0].length) {
                        if (field[field.length - kk][i] == 0)
                            countField++;
                        else
                            countField = 0;
                    }

                }
            }
            cc = 0;
            for (int i = 0; i < figure[0].length; i++) {
                if (figure[figure.length - 1][i] == 0) {
                    cc++;
                } else
                    break;
            }
            if (found) {
                if (cc != 0) {
                    for (int i = 0; i < figure.length; i++) {
                        for (int j = 0; j < figure[i].length; j++) {
                            if (figure[figure.length - 1 - i][j] != 0)
                                field[field.length - kk - i][j + temp - cc] = figure[figure.length - 1 - i][j];
                        }
                    }
                } else {
                    for (int i = 0; i < figure.length; i++) {
                        for (int j = 0; j < figure[i].length; j++) {
                            if (figure[figure.length - 1 - i][j] != 0)
                                field[field.length - kk - i][j + temp] = figure[figure.length - 1 - i][j];
                        }
                    }
                }
                clearLine();
            } else {
                place(rote(figure), count + 1, kk);
            }
        } else {
            if (kk + 1 < field.length)
                place(figure, 0, kk + 1);
        }
    }

    private static void clearLine() {
        int m = 0;
        while (m < field.length) {
            for (int i = field.length - 1; i >= 0; i--) {
                int count = 0;
                for (int j = 0; j < field[i].length; j++) {
                    if (field[i][j] != 0)
                        count++;
                }
                if (count == field[i].length) {
                    for (int i1 = i; i1 > 0; i1--) {
                        System.arraycopy(field[i1 - 1], 0, field[i1],
                                0, field[i].length);
                    }
                }
            }
            m++;
        }
    }

    private static boolean testFigure(int kk, int x, int[][] figure) {
        boolean found = false;
        int y = field.length - figure.length - kk;
        try {
            for (int i = field.length - kk - 2; i >= 0; i--) {
                if (field[i][x] != 0)
                    return false;
            }
            loop:
            for (int n = 0; n < figure.length; n++) {
                for (int m = 0; m < figure[n].length; m++) {
                    if (figure[n][m] != 0) {
                        if (field[y + n][x + m] == 0) {
                            found = true;
                        } else {
                            found = false;
                            break loop;
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        return found;
    }

}
