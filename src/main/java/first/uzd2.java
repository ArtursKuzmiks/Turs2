package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uzd2 {
    public static void main(String[] args) {
        String [] num=new String[0];
        int [] val;
        int tempint;
        String tempstr = null;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

            System.out.print("Ievadiet skaitlus: ");
            num = br.readLine().split(",");

        }catch (IOException e){
            System.out.println("Input error");
        }

        val = new int [num.length];

        for(int i=0;i<num.length;i++) {
            int s = 0;
            for (int j = 0; j < num[i].length(); j++) {
                s += Integer.valueOf(String.valueOf(num[i].charAt(j)));
            }
            val[i] = s;
        }

        for(int i=0;i<val.length;i++){
            for(int j=0;j<val.length-1;j++)
                if(val[j]<val[j+1]){
                    tempint=val[j];
                    tempstr=num[j];
                    val[j]=val[j+1];
                    num[j]=num[j+1];
                    val[j+1]=tempint;
                    num[j+1]=tempstr;
                }
        }

        for(int i=0;i<val.length-1;i++){
            if(val[i]==val[i+1]){
                    if(num[i].length()<num[i+1].length()){
                        tempstr=num[i];
                        num[i]=num[i+1];
                        num[i+1]=tempstr;
                    }
            }
        }

        for (String i:num) {
            System.out.print(i+" ");
        }
    }
}
