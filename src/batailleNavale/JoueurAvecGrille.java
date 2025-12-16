package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur {

    private GrilleNavale grille;

    private static int tailleOuErreur(GrilleNavale g) {
        if (g == null) throw new IllegalArgumentException("Grille nulle");
        return g.getTaille();
    }

    public JoueurAvecGrille(GrilleNavale g, String nom) {
        super(tailleOuErreur(g), nom);
        this.grille = g;
    }

    public JoueurAvecGrille(GrilleNavale g) {
        super(tailleOuErreur(g));
        this.grille = g;
    }

    public GrilleNavale getGrille() {
        return grille;
    }

    @Override
    public int defendre(Coordonnee c) {
        grille.recoitTir(c);

        if (grille.perdu()) return GAMEOVER;
        if (grille.estCoule(c)) return COULE;
        if (grille.estTouche(c)) return TOUCHE;
        return A_L_EAU;
    }
}