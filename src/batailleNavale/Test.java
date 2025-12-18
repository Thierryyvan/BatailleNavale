package batailleNavale;

public class Test {
    public static void main(String[] args) {

        //System.out.println("=== DEBUT TEST ===");

        // Creation d'une grille 10x10 avec 4 navires
        int tailleGrille = 10;
        GrilleNavale grille = new GrilleNavale(tailleGrille, 4);

        // Creation des navires
        int[] tailles = {3, 3, 4, 2};
        grille.placementAuto(tailles);

        // Tir 1
        Coordonnee c1 = new Coordonnee("J10");
        grille.recoitTir(c1);

        // Tir 2
        Coordonnee c2 = new Coordonnee("A1");
        grille.recoitTir(c2);

        // Tir 3
        Coordonnee c3 = new Coordonnee("F8");
        grille.recoitTir(c3);

        System.out.println("\n=== AFFICHAGE GRILLE ===");
        System.out.println(grille.toString());
    }
}   

