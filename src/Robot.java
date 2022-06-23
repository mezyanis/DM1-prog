import java.util.Arrays;

public class Robot {


    Tiroi[] coffre = new Tiroi [5];
    Posi deb = new Posi(0,0,'N');
    Posi pose = deb ;
    Bordereau bdr ;
    String[] accusers = new String[5];
    

    public Robot(){
        this.bdr = new Bordereau();
    }

    
    private void remplir(Tiroi[] coffre){
        for (int i = 0; i< coffre.length; i++){
            coffre[i] = new Tiroi();
        }
    }

    public void charger(Bordereau bdr ){
        int indice = bdr.indice;
        int i=0;
        while (i<indice){
            coffre[i].nosalle = bdr.list[i][2];
            coffre[i].code = bdr.list[i][3];
            System.out.println("le Tiroire n°" + coffre[i].nosalle + " est entrain de se remplir...");
            System.out.println("Le code de la livraison n°" + coffre[i].nosalle + " est: " + coffre[i].code );
            i++;
        }
    }

    public int notiroi(int nosalle){
        int num_tir = 0 ;
        for (int i =0 ; i<coffre.length; i++){
            if(nosalle == coffre[i].nosalle){
                num_tir = i+1;
            }
        }
        return num_tir;
    }

    protected void avancer(int i, int j, int nosalle, Trajet Trj) {
        /*lorsque que le robot passe sur une case de la grille cette case prend la valeur 9 au lieu de -1
        pour eviter les décalages à l'affichage
        */
        int x0, y0, x1, y1;
        Tiroi tir = new Tiroi(nosalle, this.bdr.list[nosalle][3]);
        Agent ag = new Agent(nosalle);
        x0 = deb.lin;
        y0 = deb.col;
        pose.setOri('N');
        x1 = i;
        y1 = j;
        if (x0 >= x1) {
            System.out.println("tournez vers le sud");
            pose.setOri('S');
            for (int x = x0; x >= x1; x--) {
                Trj.plan[x][y0] = 9;
                pose.setLin(x);
                pose.printm();
                Trj.afficher(Trj.plan);
            }
        } else {
            System.out.println("tournez vers le nord");
            pose.setOri('N');
            for (int x = x0; x <= x1; x++) {
                Trj.plan[x][y0] = 9;
                pose.setLin(x);
                pose.printm();
                Trj.afficher(Trj.plan);
            }
        }
        x0 = x1;
        if (y0 >= y1) {
            System.out.println("tournez vers l'ouest");
            pose.setOri('O');
            for (int y = y0; y >= y1; y--) {
                Trj.plan[x0][y] = 9;
                pose.setCol(y);
                pose.printm();
                Trj.afficher(Trj.plan);
            }
        } else {
            System.out.println("tournez vers l'est");
            pose.setOri('E');
            for (int y = y0; y <= y1; y++) {
                Trj.plan[x0][y] = 9;
                pose.setCol(y);
                pose.printm();
                Trj.afficher(Trj.plan);
            }
        }
        y0 = y1;
        if (x0 == bdr.list[nosalle][1] & y0 == bdr.list[nosalle][0]) {
            int n = nosalle + 1;
            System.out.println("Livraison en cours ... ");
            System.out.println(" ");
            System.out.println("Entrez le code d'identifiation de la salle n°" + n);
            int a = 3;
            boolean code = tir.verifiCode(ag);
            while (a > 1) {
                if (code) {
                    bdr.list[nosalle][4] = 1;
                    System.out.println("remettre l'accuser de réception");
                    this.accusers[nosalle] = ag.accuser();
                    break;
                } else {
                    System.err.println("votre code est faux, encore " + (a-1) + " essais");
                    bdr.list[nosalle][4] = 0;
                    code = tir.verifiCode(ag);
                    if (code){
                        bdr.list[nosalle][4] = 1;
                        System.out.println("remettre l'accuser de réception");
                        this.accusers[nosalle] = ag.accuser();
                        break;
                    }
                }
                a--;
            }
            if(a<=1){
                System.out.println("code incorrect, livaison annulée!");
            }
        }
    }

    protected boolean pas_suivant(Trajet Trj){
        int nosalle = 0;
        boolean pas = false;
        Trj.makeTrajet(bdr);
        Trj.affichtrajet();
        while (nosalle < bdr.indice) {
            avancer(bdr.list[nosalle][1], bdr.list[nosalle][0], nosalle, Trj);
            pas = true;
            nosalle++;
            if (this.pose.col == bdr.list[bdr.indice - 1][0] & this.pose.lin == bdr.list[bdr.indice - 1][1]) {
                System.out.println("fin du trajet!");
                pas = false;
            }
        }
        return pas;
    }

    protected void tourner(Trajet Trj , Bordereau bdr){
        int nosalle =0;
        pose.printm();
        remplir(this.coffre);
        charger(bdr);
        Trj.makeTrajet(bdr);
        Trj.affichtrajet();
        Arrays.fill(accusers , "0");
        while (nosalle < bdr.indice) {
            avancer(bdr.list[nosalle][1], bdr.list[nosalle][0], nosalle, Trj);
            nosalle++;
        }
    }

    protected void rapport(Bordereau bdr){
        for (int i=0; i< bdr.indice; i++){
            if(accusers[i].equals("0")){
                System.out.println("La salle n°" + (i+1) + " n'a pas été livrée (code incorrect) ");
            }else{
                System.out.println("la salle n°"+ (i+1) + " a bien été livrée, voici l'accuser de reception: "+ accusers[i]);
            }
        }
    }

    public static void main(String[] args){
        Robot iti = new Robot();
        Trajet trj = new Trajet();
        iti.tourner(trj, iti.bdr);
        for(int i=0; i<iti.bdr.indice; i++ ){
            iti.bdr.affichBord(i);
        }
        iti.rapport(iti.bdr);
    }
}
