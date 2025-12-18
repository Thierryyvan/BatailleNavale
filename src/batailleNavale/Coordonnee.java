package batailleNavale;

public class Coordonnee implements Comparable<Coordonnee> {

    private int ligne;   // numéro de ligne (0 à 25)
    private int colonne; // numéro de colonne (0 à 25)

    // Constructeur avec coordonnées numériques
    public Coordonnee(int ligne, int colonne) {
        if (ligne < 0 || ligne > 25 || colonne < 0 || colonne > 25) {
            throw new IllegalArgumentException("La ligne et la colonne doivent être entre 0 et 25.");
        }
        this.ligne = ligne;
        this.colonne = colonne;
    }

    // Constructeur à partir d'une chaîne type "A1"
    public Coordonnee(String s) {
        if (s == null || s.length() < 2 || s.length() > 3) {
            throw new IllegalArgumentException("Coordonnée invalide : format attendu [A-Z][1-26]");
        }

        s = s.trim().toUpperCase();
        char lettre = s.charAt(0);

        if (lettre < 'A' || lettre > 'Z') {
            throw new IllegalArgumentException("Colonne invalide : doit être une lettre de A à Z");
        }

        this.colonne = lettre - 'A';

        try {
            this.ligne = Integer.parseInt(s.substring(1)) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Numéro de ligne invalide : doit être un nombre");
        }

        if (ligne < 0 || ligne > 25) {
            throw new IllegalArgumentException("Numéro de ligne hors limites : doit être entre 1 et 26");
        }
    }

    // Getters
    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    // Affichage lisible pour le joueur
    @Override
    public String toString() {
        return "" + (char) ('A' + colonne) + (ligne + 1);
    }

    // Comparaison de coordonnées
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordonnee)) return false;
        Coordonnee c = (Coordonnee) obj;
        return this.ligne == c.ligne && this.colonne == c.colonne;
    }

    // Coordonnée voisine (haut/bas/gauche/droite)
    public boolean voisine(Coordonnee c) {
        if (c == null || this.equals(c)) return false;
        return (this.colonne == c.colonne && (this.ligne == c.ligne + 1 || this.ligne == c.ligne - 1))
                || (this.ligne == c.ligne && (this.colonne == c.colonne + 1 || this.colonne == c.colonne - 1));
    }

    // Ordre pour Comparable
    @Override
    public int compareTo(Coordonnee c) {
        int compareLigne = this.ligne - c.ligne;
        if (compareLigne != 0) return compareLigne;
        return this.colonne - c.colonne;
    }
}
