import java.util.Scanner;

public class Agent {
    int nosalle;
    
    public Agent(int nosalle){
        this.nosalle = nosalle;
    }

    public int saisiCode(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt() ;
    }

    public String accuser(){
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public static void  main(String[] args){
        Agent ag = new Agent(2);
        ag.saisiCode();

    }
}
