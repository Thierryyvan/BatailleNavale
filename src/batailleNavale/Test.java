package batailleNavale;

public class Test {
	public static void main(String[] args) {
		// Création d'une grille 10x10 avec 2 navires
        int tailleGrille = 10;
        GrilleNavale grille = new GrilleNavale(tailleGrille, 3);

        // Création des navires (exemple)
        int[] tailles = {3, 3, 2};
        grille.placementAuto(tailles);
        System.out.println(grille.toString());

        // Effectuer des tirs
//        grille.recoitTir(3, 7); // touche nav2 -> X
//        grille.recoitTir(new Coordonnee(4, 3)); // case vide -> O
//        grille.recoitTir(new Coordonnee(2, 5)); // touche nav1 -> X

        // Afficher la grille
	}
}
