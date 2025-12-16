package batailleNavale;

public abstract class Joueur {

    public static final int TOUCHE = 1;
    public static final int COULE = 2;
    public static final int A_L_EAU = 3;
    public static final int GAMEOVER = 4;

    private Joueur adversaire;
    private int tailleGrille;
    private String nom;

    public Joueur(int tailleGrille, String nom) {
        if (tailleGrille <= 2) {
            throw new IllegalArgumentException("Taille de grille incorrecte");
        }
        if (nom == null) {
            throw new IllegalArgumentException("Nom absent");
        }

        this.tailleGrille = tailleGrille;
        this.nom = nom;
        this.adversaire = null;
    }

    public Joueur(int tailleGrille) {
        if (tailleGrille <= 2) {
            throw new IllegalArgumentException("Taille de grille incorrecte");
        }

        this.tailleGrille = tailleGrille;
        this.nom = "Joueur 1";
        this.adversaire = null;
    }

    public int getTailleGrille() {
        return tailleGrille;
    }

    public String getNom() {
        return nom;
    }

    public void jouerAvec(Joueur j) {
        if (j == null) {
            throw new IllegalArgumentException("Adversaire inexistant");
        }
        if (this == j) {
            throw new IllegalArgumentException("Même joueur");
        }
        if (this.tailleGrille != j.tailleGrille) {
            throw new IllegalArgumentException("Tailles de grilles différentes");
        }

        this.adversaire = j;
        j.adversaire = this;

        deroulementJeu(this, j);
    }

    private static void deroulementJeu(Joueur attaquant, Joueur defenseur) {
        int res = 0;

        while (res != GAMEOVER) {
            Coordonnee c = attaquant.choixAttaque();
            res = defenseur.defendre(c);

            attaquant.retourAttaque(c, res);
            defenseur.retourDefense(c, res);

            Joueur tmp = attaquant;
            attaquant = defenseur;
            defenseur = tmp;
        }
    }

    protected abstract void retourAttaque(Coordonnee c, int etat);

    protected abstract void retourDefense(Coordonnee c, int etat);

    public abstract Coordonnee choixAttaque();

    public abstract int defendre(Coordonnee c);
}