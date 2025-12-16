package batailleNavale;

public class Navire {

    private Coordonnee debut;
    private Coordonnee fin;
    private Coordonnee[] partiesTouchees;
    private int nbTouchees;

    public Navire(Coordonnee debut, int longueur, boolean estVertical) {
        if (longueur <= 0) {
            throw new IllegalArgumentException("La longueur d'un bateau ne peut pas être négative ou nulle : " + longueur);
        }

        this.debut = debut;
        this.nbTouchees = 0;
        this.partiesTouchees = new Coordonnee[longueur];

        if (estVertical) {
            this.fin = new Coordonnee(debut.getLigne() + longueur - 1, debut.getColonne());
        } else {
            this.fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur - 1);
        }
    }

    private boolean estVertical() {
        return debut.getColonne() == fin.getColonne();
    }

    @Override
    public String toString() {
        String axe;
        int longueur;

        if (estVertical()) {
            axe = "vertical";
            longueur = fin.getLigne() - debut.getLigne() + 1;
        } else {
            axe = "horizontal";
            longueur = fin.getColonne() - debut.getColonne() + 1;
        }

        return "Navire(" + debut + ", " + longueur + ", " + axe + ")";
    }

    public Coordonnee getDebut() {
        return debut;
    }

    public Coordonnee getFin() {
        return fin;
    }

    public boolean contient(Coordonnee c) {
        // (dans ce projet, debut est supposée "inférieure" à fin)
        return c.getLigne() >= debut.getLigne() && c.getLigne() <= fin.getLigne() &&
               c.getColonne() >= debut.getColonne() && c.getColonne() <= fin.getColonne();
    }

    // true ssi this et n partagent au moins une case
    public boolean chevauche(Navire n) {
        if (this.estVertical()) {
            // on parcourt toutes les cases de this
            for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
                Coordonnee c = new Coordonnee(i, debut.getColonne());
                if (n.contient(c)) return true;
            }
        } else {
            for (int j = debut.getColonne(); j <= fin.getColonne(); j++) {
                Coordonnee c = new Coordonnee(debut.getLigne(), j);
                if (n.contient(c)) return true;
            }
        }
        return false;
    }

    // true ssi this est adjacent à n (SANS diagonale)
    public boolean touche(Navire n) {
        // si ils se chevauchent, c'est un conflit => on renvoie true
        if (this.chevauche(n)) return true;

        // on parcourt toutes les cases de this, et on regarde si une est voisine d'une case de n
        if (this.estVertical()) {
            for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
                Coordonnee cThis = new Coordonnee(i, debut.getColonne());
                if (n.estVertical()) {
                    for (int k = n.debut.getLigne(); k <= n.fin.getLigne(); k++) {
                        Coordonnee cN = new Coordonnee(k, n.debut.getColonne());
                        if (cThis.voisine(cN)) return true; // voisine = vertical/horizontal (pas diagonale)
                    }
                } else {
                    for (int k = n.debut.getColonne(); k <= n.fin.getColonne(); k++) {
                        Coordonnee cN = new Coordonnee(n.debut.getLigne(), k);
                        if (cThis.voisine(cN)) return true;
                    }
                }
            }
        } else {
            for (int j = debut.getColonne(); j <= fin.getColonne(); j++) {
                Coordonnee cThis = new Coordonnee(debut.getLigne(), j);
                if (n.estVertical()) {
                    for (int k = n.debut.getLigne(); k <= n.fin.getLigne(); k++) {
                        Coordonnee cN = new Coordonnee(k, n.debut.getColonne());
                        if (cThis.voisine(cN)) return true;
                    }
                } else {
                    for (int k = n.debut.getColonne(); k <= n.fin.getColonne(); k++) {
                        Coordonnee cN = new Coordonnee(n.debut.getLigne(), k);
                        if (cThis.voisine(cN)) return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean recoitTir(Coordonnee c) {
        if (this.contient(c)) {
            // si déjà touché, on renvoie true mais on ne recompte pas
            if (!this.estTouche(c)) {
                partiesTouchees[nbTouchees] = c;
                nbTouchees = nbTouchees + 1;
            }
            return true;
        }
        return false;
    }

    public boolean estTouche(Coordonnee c) {
        for (int i = 0; i < nbTouchees; i++) {
            if (partiesTouchees[i].equals(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean estTouche() {
        return nbTouchees > 0;
    }

    public boolean estCoule() {
        return nbTouchees == partiesTouchees.length;
    }
}
