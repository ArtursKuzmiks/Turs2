package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Mikelis implements Runnable{
    private volatile double rez;
    @Override
    public void run() {
        rez=1;
        int z=uzd5.time;
        int x=uzd5.par;
        for (int i=0;i<z-1;i++)
            rez=rez+x;
    }
    double getRez() {
        return rez;
    }
}

public class uzd5 {
    static volatile int time;
    static volatile int par;
    public static void main(String[] args) throws IOException{

        Mikelis mikelis = new Mikelis();
        Thread myThread = new Thread(mikelis);

        int z;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Ievadiet laiku: ");
            par=Integer.parseInt(br.readLine());
            time=z=Integer.parseInt(br.readLine());
            myThread.start();

        }catch (IllegalArgumentException e){
            System.out.println("input error");
            return;
        }

        if(myThread.isAlive()){
            do{
                try{
                    myThread.join(150);
                }catch (InterruptedException ignored){}
            }while(myThread.isAlive());
        }

        if (mikelis.getRez() < eriks(z))
                System.out.println("Ēriks");
        else
            System.out.println("Miķelis");

    }
    private static double eriks(int z){
        double rez=1;
        for (int i=0;i<z-1;i++)
            rez=rez*2;
        return rez;
    }

}
