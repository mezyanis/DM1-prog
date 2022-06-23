import java.util.Arrays;
import java.util.Scanner;

public class Bordereau {
    
    int[][] list = new int [5][5];
    int indice ;
   
    public Bordereau(){
        this.remplir(list);
        Scanner sc =new Scanner(System.in);
        System.out.println("inserez le nombre de livraison (max 5)");
        this.indice = sc.nextInt();
        if (indice <= 5 & indice > 0 ){
            for(int ligne=0; ligne< indice; ligne++){
                for(int colonne=0; colonne<list[ligne].length; colonne++){
                    list[ligne][2] = ligne+1;
                    int n = ligne + 1;
                    switch (colonne) {
                        case 0 -> {
                            System.out.println("inserez la colonne " + n + " (la valeur doit étre entre 0 et 9)");
                            int a = sc.nextInt();
                            if (a >= 0 & a <= 9){
                                list[ligne][0] = a;
                            }else{
                                while(a < 0 | a > 9 ){
                                    System.err.println("cette valeur est refusée, réeesayez");
                                    a = sc.nextInt();
                                    list[ligne][0] = a;
                                }
                            }
                        break;}
                        case 1 -> {
                            System.out.println("inserez la ligne " + n + " (la valeur doit etre entre 0 et 9)");
                            int b = sc.nextInt();
                            if (b >= 0 & b <= 9){
                                list[ligne][1] = b;
                            }else{
                                while(b < 0 | b > 9 ){
                                    System.err.println("cette valeur est refusée, réeesayez");
                                    b = sc.nextInt();
                                    list[ligne][1] = b;
                                }
                            }
                        break;}
                        case 2 -> {
                            System.out.println("inserez le code reception " + n);
                            list[ligne][3] = sc.nextInt();
                        break;}
                    }
                }
            }
        }
        else{
            System.err.println("vous ne pouvez pas faire ce nombre de livraison");
        }
    }


    private void remplir(int[][] tableau){
         for (int[] ints : tableau) {
             Arrays.fill(ints, 0);
         }
     }

    public void affichBord(int i){
        System.out.println("Les informations de la livraison n° "+ (i+1) +" : " );
        for(int colonne=0; colonne<list[i].length; colonne++){
            System.out.println(list[i][colonne]);
        }
    }

    public static void main(String[] args) {
        Bordereau yanis = new Bordereau();

    }

}

