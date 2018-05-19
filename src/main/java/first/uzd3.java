package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uzd3 {
    public static void main(String[] args) throws IOException{
        String str;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Ievadiet virkni: ");
            str = br.readLine();
        }catch (IllegalArgumentException e){
            return;
        }
        for(int i=str.length()-1;i>=0;i--)
            if(!integer(String.valueOf(str.charAt(i))))
                System.out.print(str.charAt(i));

    }
    private static boolean integer(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
