package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uzd6 {

    private static volatile int [] record=new int[100];
    private static volatile int count;

    public static void main(String[] args) throws IOException{
        String str;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Ievadiet skaitli: ");
            str=br.readLine();
            Long.parseLong(str);
        }catch (IllegalArgumentException e) {
            System.out.println("input error");
            return;
        }

        if(str.length()<17){
            System.out.println("input error");
            return;
        }
        record[count]=num3(str);
        count++;

        String [] pZ= str.split(String.valueOf(num3(str)));

        for (String aPZ : pZ) num2(aPZ);

        record=sakartot(record);
        count=6;

        for(int i=0;i<count;i++)
            str=str.replaceAll(String.valueOf(record[i]),"");

        for(int i=0;i<str.length();i++) {
            record[count] = Integer.parseInt(String.valueOf(str.charAt(i)));
            count++;
        }
        record=sakartot(record);
        for (int i=0;i<count;i++)
            System.out.print(record[i]+" ");


    }
    private static int num3(String str){
        int[] tempArray = new int[3];
        int temp=0;
        int [] num= new int[str.length()];

        for(int i=0;i<num.length;i++)
            num[i] = Integer.parseInt(String.valueOf(str.charAt(i)));

        for(int i=1;i<num.length-1;i++){
            StringBuilder stringBuilder = new StringBuilder();
            for(int j=0;j<3;j++){
                tempArray[j]=num[i+j-1];
                stringBuilder.append(tempArray[j]);
            }
            if(temp<Integer.parseInt(stringBuilder.toString()))
                temp=Integer.parseInt(stringBuilder.toString());
        }
        return temp;
    }

    private static void num2(String str){
        int[] num = new int[str.length()];
        int[] temp1 = new int[num.length-1];
        int [] temp2=new int[num.length-1];

        for(int i=0;i<num.length;i++)
            num[i] = Integer.parseInt(String.valueOf(str.charAt(i)));

        for(int i=0;i<num.length-1;i++){
            temp1[i]=num[i];
            temp2[i]=num[i+1];
        }

        for(int i=0;i<str.length()/2;i++){
            int a=max(temp1);
            for(int j=0;j<temp1.length;j++){
                if(temp1[j]==a && temp1[j]!=0){
                    String stringBuilder = String.valueOf(temp1[j]) +
                            temp2[j];
                    record[count]=Integer.parseInt(stringBuilder);
                    count++;
                    temp1[j]=temp2[j]=0;
                    if(j-1>=0)temp1[j-1]=temp2[j-1]=0;
                    if(j+1<temp1.length)temp1[j+1]=temp2[j+1]=0;
                }
            }
        }
    }
    private static int max(int[]a){
        int max=0;
        for (int anA : a) {
            if (anA >= max)
                max = anA;
        }
        return max;
    }
    private static int[] sakartot(int[]a){
        int temp;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length-1;j++)
                if (a[j] < a[j + 1]) {
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
        }
        return a;
    }
}
