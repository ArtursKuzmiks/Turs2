package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uzd1 {
    static char [] ch = new char []{'a','e','i','o','u'};
    public static void main(String[] args) {
        String str = null;
        int [] num;
        int n=0;
        int m=0;
        char temp;
        char [] sim;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Ievadiet rindu: ");
            str=br.readLine();

        }catch (IOException e){
            System.out.println("input error");
        }


        for(int i = 0; i< (str != null ? str.length() : 0); i++)
            if(integer(str.charAt(i)))
                n++;

        num=new int[n];
        sim=new char[(str != null ? str.length() : 0) -n];
        n=0;

        for(int i = 0; i< (str != null ? str.length() : 0); i++){
            if(integer(str.charAt(i))){
                num[n]=Integer.parseInt(String.valueOf(str.charAt(i)));
                n++;
            }else{
               sim[m]=str.charAt(i);
               m++;
            }
        }


        for(int i=0;i<num.length-1;i=i+2) {
            if (num[i] % 2 != 0 && num[i + 1] % 2 != 0) {
                for (int j = i; j < num.length; j++) {
                    if (num[j] % 2 == 0) {
                        n = num[i + 1];
                        num[i + 1] = num[j];
                        num[j] = n;
                    }
                }
            } else {
                if (num[i] % 2 == 0 && num[i + 1] % 2 == 0) {
                    for (int j = i; j < num.length; j++) {
                        if (num[j] % 2 != 0) {
                            n = num[i + 1];
                            num[i + 1] = num[j];
                            num[j] = n;
                        }
                    }
                }
            }
        }

        n=0;
        for(int i=0;i<num.length-1;i++){
            if (num[i] % 2 != 0 && num[i + 1] % 2 != 0) n++;
            if (num[i] % 2 == 0 && num[i + 1] % 2 == 0) n++;
        }

        if(n>sim.length || num.length/2 > sim.length){
            System.out.println(-1);
            return;
        }

        int x=0;
        while (x<sim.length/2){
        for(int i=1;i<sim.length;i++){
            if (find(sim[i]) && find(sim[i-1])){
                for(int j=i;j<sim.length-2;j++){
                    if(!find(sim[j+1]) && !find(sim[j+2])){
                        temp=sim[j+1];
                        sim[j+1]=sim[j];
                        sim[j]=temp;
                    }

                }
            }

        }
        x++;
        }

        m=0;
        for(int i=0;i<sim.length-1;i++){
            if(i%2==0 && i!=0)
                m++;
            if((find(sim[i]) && find(sim[i+1]))&& i%2!=0)
                m++;
        }

        if(m>num.length || sim.length/2 > num.length ){
            System.out.println("-1");
            return;
        }

        int a=0;
        if(num.length>sim.length){
            if(num.length-sim.length<4)
                a=2;
            m=0;
            for(int i=0;i<num.length;i+=2){
                System.out.print(num[i]);
                if(i+1<num.length) System.out.print(num[i+1]);
                if(i+1<=num.length && m<sim.length)System.out.print(sim[m]);

                if(i-a<sim.length/2){
                    System.out.print(sim[m+1]);
                    m+=2;
                }else
                    m++;
            }
        }else{
            if(sim.length-num.length<4)
                a=2;
            n=0;
            for(int j=0;j<sim.length;j+=2){
                System.out.print(sim[j]);
                if(j+1<sim.length) System.out.print(sim[j+1]);
                if(j+1<=sim.length && n<sim.length) System.out.print(num[n]);

                if(j-a<num.length/2){
                    System.out.print(num[n+1]);
                    n+=2;
                }else
                    n++;
            }
        }

    }

    private static boolean integer(char a){
        try{
            Integer.parseInt(String.valueOf(a));
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    private static boolean find(char a){
        for (char aCh : ch) {
            if (a == aCh)
                return true;
        }
        return false;
    }
}
