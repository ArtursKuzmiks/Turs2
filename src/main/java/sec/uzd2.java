package sec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Node {

    private String data;
    private Node left;
    private Node right;

    Node(String data) {
        this.data = data;
        this.left = null;
        this.right = null;

    }

    String getData() {
        return data;
    }

    Node getLeft() {
        return left;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    Node getRight() {
        return right;
    }

    void setRight(Node right) {
        this.right = right;
    }

    void setData(String data) {
        this.data = data;
    }
}

class BinaryTree {

    private Node tempRoot;
    private Node root;
    private ArrayList<Node> nodeArrayList = new ArrayList<>();
    private String[] operator = {"+", "-", "*"};


    BinaryTree() {
        this.root = null;
    }

    private Node insert(Node node, String data) {
        if (node == null) {
            if (data.equals("(")) {
                return null;
            }
            return new Node(data);
        } else {
            if (isDigit(data)) {
                rekursInsertDigit(node, data);
            } else {
                if (isOperator(data)) {
                    if (!operatorTest(node)) {
                        Node newNode = new Node(data);
                        nodeArrayList.add(newNode);
                        newNode.setLeft(root);
                        return newNode;
                    }
                } else {
                    if (!isBracket(data)) {
                        Node newNode = new Node(data);
                        newNode.setLeft(root);
                        tempRoot = newNode;
                        return null;
                    } else {
                        if (data.equals(")"))
                            return node;
                        else
                            rekusInsertOperator(node, " ");
                    }
                }
            }
        }
        return node;
    }

    void add(String data) {
        root = insert(root, data);
    }

    private boolean isDigit(String data) {
        try {
            Integer.parseInt(data);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean isOperator(String data) {
        return data.equals(" ");
    }

    private boolean isBracket(String data) {
        return data.equals("(") || data.equals(")");
    }

    private int generator(int k, ArrayList<Node> node) {
        int n = 2;
        int j;
        if (k == node.size()) {
            n = elavue();
            if (n == 0) return n;
        } else {
            for (j = 0; j < operator.length; j++) {
                if (n == 0) return n;
                nodeArrayList.get(k).setData(operator[j]);
                if (k + 1 <= node.size()) {
                    n = generator(k + 1, nodeArrayList);
                } else return -1;

            }
        }
        return n;
    }

    private int evalue(Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return toDigit(node.getData());
        } else {
            int rez;

            if (node.getData().equals("+")) {
                rez = evalue(node.getLeft()) + evalue(node.getRight());
            } else {
                if (node.getData().equals("-")) {
                    rez = evalue(node.getLeft()) - evalue(node.getRight());
                } else {
                    if (node.getData().equals("*")) {
                        rez = evalue(node.getLeft()) * evalue(node.getRight());
                    } else {
                        if (node.getData().equals("=")) {
                            rez = evalue(node.getLeft()) - evalue(node.getRight());
                        } else rez = -1;
                    }
                }
            }
            return rez;
        }
    }

    int solve() {
        for (Node aNodeArrayList : nodeArrayList) {
            aNodeArrayList.setData(operator[0]);
        }

        return generator(0, nodeArrayList);
    }

    private int elavue() {
        return evalue(tempRoot);
    }

    private int toDigit(String data) {
        return Integer.parseInt(data);
    }

    void create() {
        tempRoot.setRight(root);
    }

    String rekombine(String[] str) {

        StringBuilder bulder = new StringBuilder();

        for (int n = 0, i = 0; i < str.length; i++) {
            if (str[i].charAt(0) == 32) {
                bulder.append(nodeArrayList.get(n++).getData().charAt(0));
            } else {
                bulder.append(str[i]);
            }
        }

        return bulder.toString();

    }

    private boolean rekursInsertDigit(Node node, String data) {

        boolean found = false;

        if (node.getData().equals(" ")) {
            if (node.getLeft() != null) {
                if (node.getLeft().getData().equals(" ")) {
                    found = rekursInsertDigit(node.getLeft(), data);
                }
            }
            if (node.getRight() != null) {
                if (node.getRight().getData().equals(" ")) {
                    found = rekursInsertDigit(node.getRight(), data);
                }
            }
            if (!found) {
                if (node.getLeft() == null) {
                    node.setLeft(rekusInsertOperator(node.getLeft(), data));
                    return true;
                } else {
                    if (node.getRight() == null) {
                        node.setRight(rekusInsertOperator(node.getRight(), data));
                        return true;
                    }
                }
            }

        }


        return found;
    }

    private Node rekusInsertOperator(Node node, String data) {
        if (node == null) {
            Node node1 = new Node(data);
            if (isOperator(node1.getData()))
                nodeArrayList.add(node1);
            return node1;
        }
        if (node.getLeft() != null) {
            node.setRight(rekusInsertOperator(node.getRight(), data));
        } else {
            node.setLeft(rekusInsertOperator(node.getLeft(), data));
        }
        return node;
    }

    private boolean operatorTest(Node node) {
        boolean test = false;
        if (node.getLeft() != null && node.getRight() == null)
            return true;
        if (node.getLeft() == null && node.getRight() != null)
            return true;
        if (node.getLeft() == null && node.getRight() == null)
            return false;

        Node temp = node.getLeft();
        if (temp.getData().equals(" ")) {
            test = operatorTest(temp);
        }

        if (!test) {
            temp = node.getRight();
            test = operatorTest(temp);
        }
        return test;
    }

}

public class uzd2 {

    private static int position;

    public static void main(String[] args) throws IOException {

        boolean solve = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Input line: ");
            String string = br.readLine();
            String[] stringArr = string.split("\\s");
            String[] temp = new String[stringArr.length];
            copy(temp, stringArr);

            //test 3 2 4 2 ( 6 3 ) ( 6 3 )
            //test 2 ( 2 5 ) 18 2 2
            //test 18 7 ( ( 5 2 ) 2 )
            //test 8 4 2 3 2 ( 2 ( 2 7 ) 8 ( 2 3 ) )
            //test 5 6 9 78 14 2 ( 3 ( 2 ( 5 6 ) 2 ) 9 )

            for (position = 0; position < operatorCount(string); position++) {
                BinaryTree bt = new BinaryTree();
                copy(stringArr, temp);
                String[] pp = insert(string, stringArr, operatorCount(string));
                int i = 0;
                do {
                    bt.add(pp[i++]);
                } while (i < pp.length);
                bt.create();
                if (bt.solve() == 0) {
                    System.out.println(solveOut(pp, bt));
                    solve = true;
                }
                if (solve) break;
            }
            if (!solve) System.out.println(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("input error");
        } catch (IllegalStateException es) {
            System.out.println("-1");
        }

    }

    private static void copy(String[] str, String[] temp) {
        System.arraycopy(temp, 0, str, 0, str.length);
    }

    private static int operatorCount(String string) {
        String temp = string.replaceAll("\\(\\s", "(").
                replaceAll("\\s\\)", ")");
        String[] tempArr = temp.split("\\s");

        return tempArr.length - 1;

    }

    private static String[] insert(String str, String[] arr, int a) {

        String tempstr = str.replaceAll("\\(\\s", "(").replaceAll("\\s\\)", ")");
        String[] temp = new String[arr.length + a];
        boolean insert = false;
        int count = 0;
        int bracret1Count = 0;
        int bracret2Count = 0;

        for (int n = 0, i = 0; i < temp.length; i++) {
            if (tempstr.charAt(0) == 40) bracret1Count++;
            if (tempstr.charAt(0) == 41) bracret2Count++;

            if (tempstr.charAt(0) != 32) {
                temp[i] = arr[n++];
                tempstr = tempstr.substring(arr[n - 1].length());
            } else {
                if (count++ == position) {
                    if ((bracret1Count - bracret2Count) != 0) {
                        temp[i] = " ";
                        tempstr = tempstr.substring(1);
                        position++;
                    } else {
                        temp[i] = "=";
                        insert = true;
                        tempstr = tempstr.substring(1);
                    }
                } else {
                    temp[i] = " ";
                    tempstr = tempstr.substring(1);
                }
            }

        }

        if (!insert)
            throw new IllegalStateException();

        return temp;
    }

    private static String solveOut(String[] str, BinaryTree bt) {
        return bt.rekombine(str);
    }


}
