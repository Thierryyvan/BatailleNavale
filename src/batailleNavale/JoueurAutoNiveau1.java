package batailleNavale;

public class JoueurAutoNiveau1 extends JoueurAuto{
	private Coordonnee [] coordonneeTouche;
    private int tir;
    public JoueurAutoNiveau1 (GrilleNavale g, String nom){
        super (g,nom);
        this.coordonneeTouche = new Coordonnee [g.getTaille()*g.getTaille()];
        this.tir = 0;
        }
    public JoueurAutoNiveau1(GrilleNavale g){
        super (g);
    }
    @Override
    public Coordonnee choixAttaque() {
        int taille = super.getTailleGrille();
        boolean estDoublon;

        Coordonnee c;
        do { 
            // initialisation de estDoublon a false avant de verifier           
            estDoublon = false;

            // 1. Génération aleatoire de coordonnées avec Math.random()
            // (int)(Math.random() * N) donne un entier entre 0 et N-1
            int ligne = (int)((Math.random() * taille)+1);
            int colonne = (int)((Math.random() * taille)+1);
            // Coordonnee aleatoire cree
            c = new Coordonnee(ligne, colonne);

            // 2. Verification du coordonnee dans le tableau
            // On parcourt uniquement les cases remplies (de 0 à tir - 1)
            for (int i = 0; i < tir; i++) {
                if (coordonneeTouche[i].equals(c)) {
                    estDoublon = true;
                    break; // On a trouvé un doublon, pas la peine de continuer, on recommence
                }
            }
    
        } while (estDoublon); // Tant que doublon est true, on boucle, on sort quand c'est false
        if (tir <= coordonneeTouche.length) {
            coordonneeTouche[tir] = c;
            tir++; // On incrémente le compteur seulement maintenant
        }
        return c;
    }
    @Override
    protected void retourAttaque(Coordonnee c, int etat) {
        switch (etat) {
            case TOUCHE:
                System.out.println(
                    "Attaque en " + c + " : touché."
                );
                
                break;
            case COULE:
                System.out.println(
                     "Attaque en " + c + " : coule."
                );

                break;
            case A_L_EAU:
                System.out.println(
                     "Attaque en " + c + " : a l'eau."
                );

                break;
            case GAMEOVER:
                System.out.println(
                     "Attaque en " + c + " : GAME OVER, vous avez gagné."
                );

                break;

            default:
                System.out.println(
                    "etat inconnu."
                );
                break;
        }


    }
    @Override
    protected void retourDefense(Coordonnee c, int etat) {
    
        switch (etat) {
            case TOUCHE:
                System.out.println("L'adversaire  m'a touche en " + c + "!");
                break;

            case COULE:
                System.out.println("L'adversaire en " + c + " a coule un de mes navires !");
                break;

            case A_L_EAU:
                System.out.println("plouf (à l'eau).");
                break;

            case GAMEOVER:
                System.out.println("c'est fini, j'ai perdu.");
                break;

            default:
                System.out.println("etat inconnu.");
                break;
        }
    }
}
