package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uzd7 {
    public static void main(String[] args) throws IOException{

        String rind;
        String n;
        int tempInt;
        char tempChar;
        int[] num;
        char [] sim;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Ievadiet rindu: ");
            rind = br.readLine();
            System.out.print("Ievadit skaitlu virkni: ");
            n=br.readLine();
            Integer.parseInt(n);
        }catch (IllegalArgumentException e){
            System.out.println("input error");
            return;
        }
        if(rind.length()!=n.length())
            System.out.println("-1");
        else{
            num = new int[n.length()];
            sim = new char[rind.length()];
            for(int i=0;i<n.length();i++){
                num[i] = Integer.parseInt(String.valueOf(n.charAt(i)));
                sim[i]= rind.charAt(i);
            }
            for(int j=0;j<num.length;j++)
                for(int i=0;i<num.length-1;i++){
                    if(num[i]>num[i+1]){
                        tempInt =num[i];
                        tempChar=sim[i];
                        num[i]=num[i+1];
                        sim[i]=sim[i+1];
                        num[i+1]=tempInt;
                        sim[i+1]=tempChar;
                    }
                }
            for (char i:sim)
                System.out.print(i);


        }
    }

}
