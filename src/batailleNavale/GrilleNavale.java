package batailleNavale;

/**
 * @author Thierry
 * @author Irvin
 * @author Samir
 * @author Louis
 * @author Ziang
 * @author Aleksei
 */
public class GrilleNavale {
    private Navire[] navires;
    private int nbNavires;
    private int taille;
    private Coordonnee[] tirsRecus;
    private int nbTirsRecus;

    public GrilleNavale(int taille, int[] taillesNavires) {
        // Calcul correct du navire le plus grand
        int tailleMax = taillesNavires[0];
        for (int i = 1; i < taillesNavires.length; i++) {
            if (taillesNavires[i] > tailleMax) {
                tailleMax = taillesNavires[i];
            }
        }

        if (taille > 26 || taille < 2) {
            throw new IllegalArgumentException("La taille de la grille est incompatible");
        } else if (taille < tailleMax) {
            throw new IllegalArgumentException("La grille ne peut pas supporter la taille " + tailleMax);
        } else {
            this.taille = taille;
        }

        for (int tailleNavire : taillesNavires) {
            if (tailleNavire < 1) {
                throw new IllegalArgumentException("La taille des navires doit être supérieure à 0");
            }
        }

        nbNavires = 0;
        navires = new Navire[taillesNavires.length];
        nbTirsRecus = 0;
        tirsRecus = new Coordonnee[taille * taille];
        placementAuto(taillesNavires);
    }

    public GrilleNavale(int taille, int nbNavires) {
        if (taille > 26 || taille < 2) {
            throw new IllegalArgumentException("Taille de la grille incompatible");
        }
        this.taille = taille;

        if (nbNavires <= 0) {
            throw new IllegalArgumentException("Il faut au moins un navire pour jouer");
        }

        this.nbNavires = 0;
        navires = new Navire[nbNavires];
        nbTirsRecus = 0;
        tirsRecus = new Coordonnee[taille * taille];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Ligne des lettres
        sb.append("  ");
        for (int i = 0; i < taille; i++) {
            sb.append(" ").append((char) ('A' + i)).append(" ");
        }

        // Lignes de la grille
        for (int i = 0; i < taille; i++) {
            sb.append("\n");

            // Numéro de ligne
            if (i < 9) sb.append(" ");
            sb.append(i + 1);

            // Cases
            for (int j = 0; j < taille; j++) {
                Coordonnee c = new Coordonnee(i, j);
                boolean tirRecu = estDansTirsRecus(c);
                boolean occupe = false;
                boolean touche = false;

                for (int n = 0; n < nbNavires; n++) {
                    if (navires[n].contient(c)) {
                        occupe = true;
                        if (navires[n].estTouche(c)) {
                            touche = true;
                        }
                        break;
                    }
                }

                char symbole = '.';
                if (tirRecu) {
                    symbole = touche ? 'X' : 'O';
                } else if (occupe) {
                    symbole = '#';
                }
                sb.append(" ").append(symbole).append(" ");
            }
        }
        return sb.toString();
    }

    public int getTaille() {
        return taille;
    }

    public boolean ajouteNavire(Navire n) {
        // Vérification 0-based
        if (n.getDebut().getLigne() < 0 || n.getDebut().getLigne() >= taille
                || n.getDebut().getColonne() < 0 || n.getDebut().getColonne() >= taille
                || n.getFin().getLigne() < 0 || n.getFin().getLigne() >= taille
                || n.getFin().getColonne() < 0 || n.getFin().getColonne() >= taille) {
            return false;
        }

        for (int i = 0; i < nbNavires; i++) {
            if (n.touche(navires[i]) || n.chevauche(navires[i])) {
                return false;
            }
        }

        navires[nbNavires] = n;
        nbNavires++;
        return true;
    }

    public void placementAuto(int[] taillesNavires) {
        java.util.Random rand = new java.util.Random();
        for (int tailleNavire : taillesNavires) {
            boolean place = false;
            while (!place) {
                Navire n = randomNavire(tailleNavire, rand);
                place = ajouteNavire(n);
            }
        }
    }

    private Navire randomNavire(int tailleNavire, java.util.Random rand) {
        boolean estVertical = rand.nextBoolean();
        int Rlig, Rcol;

        if (estVertical) {
            Rlig = rand.nextInt(taille - tailleNavire + 1);
            Rcol = rand.nextInt(taille);
        } else {
            Rlig = rand.nextInt(taille);
            Rcol = rand.nextInt(taille - tailleNavire + 1);
        }

        return new Navire(new Coordonnee(Rlig, Rcol), tailleNavire, estVertical);
    }

    private boolean estDansGrille(Coordonnee c) {
        int ligne = c.getLigne();
        int colonne = c.getColonne();
        return ligne >= 0 && ligne < taille && colonne >= 0 && colonne < taille;
    }

    private boolean estDansTirsRecus(Coordonnee c) {
        for (int i = 0; i < nbTirsRecus; i++) {
            if (tirsRecus[i].equals(c)) return true;
        }
        return false;
    }

    private boolean ajouteDansTirsRecus(Coordonnee c) {
        if (!estDansGrille(c) || estDansTirsRecus(c)) return false;

        for (int i = 0; i < tirsRecus.length; i++) {
            if (tirsRecus[i] == null) {
                tirsRecus[i] = c;
                nbTirsRecus++;
                return true;
            }
        }
        return false;
    }

    public boolean recoitTir(Coordonnee c) {
        if (!ajouteDansTirsRecus(c)) return false;

        for (int i = 0; i < nbNavires; i++) {
            if (navires[i] != null && navires[i].contient(c)) {
                navires[i].recoitTir(c);
            }
        }

        return true;
    }

    public boolean estTouche(Coordonnee c) {
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i] != null && navires[i].contient(c) && navires[i].estTouche(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean estALEau(Coordonnee c) {
        return estDansTirsRecus(c) && !estTouche(c);
    }

    public boolean estCoule(Coordonnee c) {
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i] != null && navires[i].contient(c) && navires[i].estCoule()) {
                return true;
            }
        }
        return false;
    }

    public boolean perdu() {
        for (int i = 0; i < nbNavires; i++) {
            if (navires[i] != null && !navires[i].estCoule()) return false;
        }
        return true;
    }
}
