package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uzd4 {
    public static void main(String[] args) throws IOException{
        int x;
        double time;
        double s=0;
        double kof=1;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Ievadiet laiku: ");
            x=Integer.parseInt(br.readLine());

        }catch (IllegalArgumentException e){
            System.out.println("input error");
            return;
        }
        time =x/10.;
        if(time<1){
            System.out.println(0);
            return;
        }

        for(int i=0;i<Math.round(time);i++){
            if(i<=10){
                kof=kof*(10-i)/10.;
            }else kof=0;
            s+=kof;
        }
        if(time<Math.round(time)){
            s-=(Math.round(time)-time)*kof;
        }else{
            kof=kof*(10-Math.round(time))/10.;
            s+=(time-Math.round(time))*kof;
        }

        double a = (x-((time-1)*7))/10.;
        if(s<a)
            System.out.println(1);
        else
            System.out.println(0);
    }

}
