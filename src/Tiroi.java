

public class Tiroi {
    int nosalle, code;

    public Tiroi(int nosalle, int code) {
        this.nosalle = nosalle;
        this.code = code;
    }

    public Tiroi() {
    }

    public boolean verifiCode(Agent ag) {
        boolean result = false;
        if (ag.saisiCode() == code & ag.nosalle == nosalle) {
            result = true;
        }
        return result;
    }

    public static void main(String[] args){
        //Bordereau bdr = new Bordereau();
        //int i =0;
        //while( i < 5 ){
            Tiroi tir = new Tiroi(2, 3);
            Agent ag = new Agent(2);
            tir.verifiCode(ag);
            //System.out.println(t);
        //}

    }
}