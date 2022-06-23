import java.util.Arrays;

public class Trajet {
    int[][] plan = new int[10][10];
    Posi deb = new Posi(0, 0, 'N');


    public Trajet() {
    }

    protected void makeTrajet(Bordereau bdr) {
        int x0, y0, x1, y1, nosalle;
        nosalle = 0;
        x0 = deb.lin;
        y0 = deb.col;
        while (nosalle < bdr.indice){
        x1 = bdr.list[nosalle][1];
        y1 = bdr.list[nosalle][0];
        if (x0 >= x1) {
            for (int x = x0; x >= x1; x--) {
                plan[x][y0] = 1;
            }
        } else {
            for (int x = x0; x <= x1; x++) {
                plan[x][y0] = 1;
            }
        }
        x0 = x1;
        if (y0 >= y1) {
            for (int y = y0; y >= y1; y--) {
                plan[x0][y] = 1;
            }
        } else {
            for (int y = y0; y <= y1; y++) {
                plan[x0][y] = 1;
            }
        }
        y0 = y1;
        nosalle++;
        }
    }

    private void remplir(int[][] tableau){
        for (int[] ligne  : tableau) {
            Arrays.fill(ligne, 0);
        }
    }

    protected void afficher(int[][] tableau){
        for(int ligne = tableau.length-1; ligne >= 0; ligne--){
            for(int colonne =0; colonne<tableau.length ; colonne++) {
                System.out.print(tableau[ligne][colonne] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("  ");
    }
       

    protected void affichtrajet(){
        afficher(plan);
        System.out.println("les coordonnés du debut du trajet sont: " + deb.lin + " "+ deb.col+ " et l'orientation est : " + deb.ori );
    }
   
   
   
    public static void main(String[] args) {

        Trajet route = new Trajet();
        route.remplir(route.plan);
        Bordereau bdr = new Bordereau();
        route.makeTrajet(bdr);
        route.affichtrajet();
    }
}
