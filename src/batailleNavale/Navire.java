package batailleNavale;

public class Navire {

    private Coordonnee debut;
    private Coordonnee fin;
    private Coordonnee[] partiesTouchees;
    private int nbTouchees;

    // Constructeur
    public Navire(Coordonnee debut, int longueur, boolean estVertical) {
        if (longueur < 1 || longueur > 25)
            throw new IllegalArgumentException("La longueur doit être comprise entre 1 et 25");

        // Vérification débordement de la grille
        if ((estVertical && (debut.getLigne() + longueur - 1 > 24)) || 
            (!estVertical && (debut.getColonne() + longueur - 1 > 24))) {
            throw new IllegalArgumentException("Le navire sort de la taille max de la grille");
        }

        this.debut = debut;
        this.nbTouchees = 0;
        this.partiesTouchees = new Coordonnee[longueur];

        // Calcul de la coordonnée de fin
        if (estVertical) {
            this.fin = new Coordonnee(debut.getLigne() + longueur - 1, debut.getColonne());
        } else {
            this.fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur - 1);
        }
    }

    // Accesseurs
    public Coordonnee getDebut() {
        return debut;
    }

    public Coordonnee getFin() {
        return fin;
    }

    public Coordonnee[] getPartiesTouchees() {
        return partiesTouchees;
    }

    // Vérifie si le navire est vertical
    private boolean estVertical() {
        return debut.getColonne() == fin.getColonne();
    }

    @Override
    public String toString() {
        String axe = estVertical() ? "vertical" : "horizontal";
        int longueur = estVertical() ? (fin.getLigne() - debut.getLigne() + 1)
                                     : (fin.getColonne() - debut.getColonne() + 1);
        return "Navire(" + debut + ", " + longueur + ", " + axe + ")";
    }

    // Vérifie si une coordonnée appartient au navire
    public boolean contient(Coordonnee c) {
        return c.getLigne() >= debut.getLigne() && c.getLigne() <= fin.getLigne() &&
               c.getColonne() >= debut.getColonne() && c.getColonne() <= fin.getColonne();
    }

    // Vérifie si le navire chevauche un autre
    public boolean chevauche(Navire n) {
        return n.getFin().getLigne() >= this.getDebut().getLigne() &&
               this.getFin().getLigne() >= n.getDebut().getLigne() &&
               n.getFin().getColonne() >= this.getDebut().getColonne() &&
               this.getFin().getColonne() >= n.getDebut().getColonne();
    }

    // Vérifie si le navire touche (adjacent horizontal/vertical) un autre
    public boolean touche(Navire n) {
        if (this.chevauche(n)) return true; // si chevauchement, renvoie vrai

        // Vérifie si une case de this est voisine d'une case de n
        for (int iThisL = debut.getLigne(); iThisL <= fin.getLigne(); iThisL++) {
            for (int jThisC = debut.getColonne(); jThisC <= fin.getColonne(); jThisC++) {
                Coordonnee cThis = new Coordonnee(iThisL, jThisC);
                for (int iN = n.debut.getLigne(); iN <= n.fin.getLigne(); iN++) {
                    for (int jN = n.debut.getColonne(); jN <= n.fin.getColonne(); jN++) {
                        Coordonnee cN = new Coordonnee(iN, jN);
                        if (cThis.voisine(cN)) return true;
                    }
                }
            }
        }
        return false;
    }

    // Reçoit un tir sur une coordonnée
    public boolean recoitTir(Coordonnee c) {
        if (this.contient(c)) {
            if (!this.estTouche(c)) {
                partiesTouchees[nbTouchees] = c;
                nbTouchees++;
            }
            return true;
        }
        return false;
    }

    // Vérifie si le navire a été touché à une coordonnée
    public boolean estTouche(Coordonnee c) {
        for (int i = 0; i < nbTouchees; i++) {
            if (partiesTouchees[i].equals(c)) return true;
        }
        return false;
    }

    // Vérifie si le navire a été touché au moins une fois
    public boolean estTouche() {
        return nbTouchees > 0;
    }

    // Vérifie si le navire est coulé
    public boolean estCoule() {
        return nbTouchees == partiesTouchees.length;
    }
}
