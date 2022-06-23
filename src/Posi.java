public class Posi {
    public int col;
    public int lin;
    public char ori;

    public Posi(int lin, int col, char ori){
        this.lin = lin;
        this.col = col;
        this.ori = ori;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setLin(int lin) {
        this.lin = lin;
    }

    public void setOri(char ori) {
        this.ori = ori;
    }

    public void printm(){
        System.out.println("les coordonnées actuelles sont: "+ this.lin+ " "+ this.col + " et est orienté vers "+ this.ori);
    }
}
