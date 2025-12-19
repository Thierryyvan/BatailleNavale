package batailleNavale;

import java.awt.Color;

public class GrilleNavaleGraphique extends GrilleNavale {

    private final GrilleGraphique grille;

    public GrilleNavaleGraphique(int taille) {
        super(taille, taille); // 5 navires par défaut
        grille = new GrilleGraphique(taille);
        grille.colorie(new Coordonnee(0, 0), new Coordonnee(taille - 1, taille - 1), BatailleNavale.BG_COLOR);
        grille.setClicActive(false);
    }

    public GrilleGraphique getGrilleGraphique() {
        return grille;
    }

    public void colorierTouteGrille(Color c) {
        grille.colorie(new Coordonnee(0, 0), new Coordonnee(getTaille() - 1, getTaille() - 1), c);
    }

    @Override
    public boolean ajouteNavire(Navire n) {
        if (!super.ajouteNavire(n)) return false;

        // Colorie le navire en gris clair
        grille.colorie(n.getDebut(), n.getFin(), Color.BLACK);
        return true;
    }

    @Override
    public boolean recoitTir(Coordonnee c) {
        boolean touche = super.recoitTir(c);

        if (estCoule(c) || estTouche(c)) {
            grille.colorie(c, Color.RED);
        } else {
            grille.colorie(c, Color.BLUE);
        }

        return touche;
    }
}
