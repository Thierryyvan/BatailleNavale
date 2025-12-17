package batailleNavale;

public class JoueurAutoNiveau2 extends JoueurAuto {

    private boolean[][] dejaTire;

    private boolean navireAdetruire = false;
    private boolean deuxiemeTouchee = false;

    private Coordonnee premiereNavire = null;
    private Coordonnee courante;

    private int essai = 0;
    private char sens;
    private int etatCourant;

    public JoueurAutoNiveau2(GrilleNavale g, String nom) {
        super(g, nom);
        dejaTire = new boolean[getTailleGrille()][getTailleGrille()];
    }

    public JoueurAutoNiveau2(GrilleNavale g) {
        super(g);
        dejaTire = new boolean[getTailleGrille()][getTailleGrille()];
    }

    @Override
    protected void retourAttaque(Coordonnee c, int etat) {
        courante = c;
        etatCourant = etat;

        if (etat == TOUCHE && !navireAdetruire) {
            navireAdetruire = true;
            premiereNavire = new Coordonnee(c.getLigne(), c.getColonne());
        } 
        else if (etat == TOUCHE && navireAdetruire && !deuxiemeTouchee) {
            deuxiemeTouchee = true;
        } 
        else if (etat == COULE) {
            navireAdetruire = false;
            deuxiemeTouchee = false;
            essai = 0;
        }
    }

    @Override
    public Coordonnee choixAttaque() {

        if (!navireAdetruire) {
            return tirAleatoire();
        }

        if (!deuxiemeTouchee) {
            return chercherOrientation();
        }

        return choixSuivant(sens);
    }

    private Coordonnee tirAleatoire() {
        for (int i = 0; i < getTailleGrille(); i++) {
            for (int j = 0; j < getTailleGrille(); j++) {
                if (!dejaTire[i][j]) {
                    dejaTire[i][j] = true;
                    return new Coordonnee(i, j);
                }
            }
        }
        return null;
    }

    private Coordonnee chercherOrientation() {

        if (essai == 0) {
            essai++;
            if (!toutEnHaut(premiereNavire) && !dejaTire[premiereNavire.getLigne() - 1][premiereNavire.getColonne()]) {
                sens = 'n';
                return nord(premiereNavire);
            }
        }

        if (essai == 1) {
            essai++;
            if (!toutEnBas(premiereNavire) && !dejaTire[premiereNavire.getLigne() + 1][premiereNavire.getColonne()]) {
                sens = 's';
                return sud(premiereNavire);
            }
        }

        if (essai == 2) {
            essai++;
            if (!toutAgauche(premiereNavire) && !dejaTire[premiereNavire.getLigne()][premiereNavire.getColonne() - 1]) {
                sens = 'o';
                return ouest(premiereNavire);
            }
        }

        if (essai == 3) {
            essai++;
            if (!toutAdroite(premiereNavire) && !dejaTire[premiereNavire.getLigne()][premiereNavire.getColonne() + 1]) {
                sens = 'e';
                return est(premiereNavire);
            }
        }

        return null;
    }

    public Coordonnee choixSuivant(char c) {

        if (c == 'n') {
            if (toutEnHaut(courante) || etatCourant == A_L_EAU || dejaTire[courante.getLigne() - 1][courante.getColonne()]) {
                sens = 's';
                return sud(premiereNavire);
            }
            return nord(courante);
        }

        if (c == 's') {
            if (toutEnBas(courante) || etatCourant == A_L_EAU || dejaTire[courante.getLigne() + 1][courante.getColonne()]) {
                sens = 'n';
                return nord(premiereNavire);
            }
            return sud(courante);
        }

        if (c == 'o') {
            if (toutAgauche(courante) || etatCourant == A_L_EAU || dejaTire[courante.getLigne()][courante.getColonne() - 1]) {
                sens = 'e';
                return est(premiereNavire);
            }
            return ouest(courante);
        }

        if (toutAdroite(courante) || etatCourant == A_L_EAU || dejaTire[courante.getLigne()][courante.getColonne() + 1]) {
            sens = 'o';
            return ouest(premiereNavire);
        }

        return est(courante);
    }

    public Coordonnee nord(Coordonnee c) {
        dejaTire[c.getLigne() - 1][c.getColonne()] = true;
        return new Coordonnee(c.getLigne() - 1, c.getColonne());
    }

    public Coordonnee sud(Coordonnee c) {
        dejaTire[c.getLigne() + 1][c.getColonne()] = true;
        return new Coordonnee(c.getLigne() + 1, c.getColonne());
    }

    public Coordonnee ouest(Coordonnee c) {
        dejaTire[c.getLigne()][c.getColonne() - 1] = true;
        return new Coordonnee(c.getLigne(), c.getColonne() - 1);
    }

    public Coordonnee est(Coordonnee c) {
        dejaTire[c.getLigne()][c.getColonne() + 1] = true;
        return new Coordonnee(c.getLigne(), c.getColonne() + 1);
    }

    public boolean toutAdroite(Coordonnee c) {
        return c.getColonne() == getTailleGrille() - 1;
    }

    public boolean toutAgauche(Coordonnee c) {
        return c.getColonne() == 0;
    }

    public boolean toutEnHaut(Coordonnee c) {
        return c.getLigne() == 0;
    }

    public boolean toutEnBas(Coordonnee c) {
        return c.getLigne() == getTailleGrille() - 1;
    }
}
