// Déclaration du package : indique que cette classe appartient au package batailleNavale
package batailleNavale;

// Déclaration de la classe Coordonnee
// Elle implémente l'interface Comparable<Coordonnee>
// ce qui permet de comparer deux objets Coordonnee entre eux
public class Coordonnee implements Comparable<Coordonnee> {

    // Attribut représentant la ligne de la coordonnée
    private int ligne;

    // Attribut représentant la colonne de la coordonnée
    private int colonne;

    // Constructeur n°1
    // Permet de créer une coordonnée à partir de deux entiers
    public Coordonnee(int ligne, int colonne) {
        if (ligne < 1 || colonne < 1) {
            throw new IllegalArgumentException("Coordonnée invalide");
        }
        this.ligne = ligne;
        this.colonne = colonne;
    }


    // Constructeur n°2
    // Permet de créer une coordonnée à partir d'une chaîne de caractères
    // Exemple : "A5"
    public Coordonnee(String s) {
        if (s == null || s.length() < 2) {
            throw new IllegalArgumentException("Coordonnée invalide");
        }

        s = s.trim().toUpperCase();

        char lettre = s.charAt(0);
        if (lettre < 'A' || lettre > 'Z') {
            throw new IllegalArgumentException("Lettre invalide");
        }

        // Colonne : A -> 1, B -> 2, ...
        this.colonne = lettre - 'A' + 1;

        try {
            // Ligne : partie numérique
            this.ligne = Integer.parseInt(s.substring(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Numéro de ligne invalide");
        }

        if (ligne < 1 || colonne < 1) {
            throw new IllegalArgumentException("Coordonnée hors grille");
        }
    }


    // Accesseur (getter) pour la ligne
    public int getLigne() {
        // Retourne la valeur de l'attribut ligne
        return ligne;
    }

    // Accesseur (getter) pour la colonne
    public int getColonne() {
        // Retourne la valeur de l'attribut colonne
        return colonne;
    }

    // Redéfinition de la méthode toString()
    // Permet d'afficher une coordonnée sous forme lisible
    
    public String toString() {
        // Retourne une chaîne de caractères représentant la coordonnée
        return "(" + ligne + "," + colonne + ")";
    }

 
    public boolean equals(Object obj) {
    	if (!(obj instanceof Coordonnee)) return false;

        // Conversion de l'objet en Coordonnee
        Coordonnee c = (Coordonnee) obj;

        // Deux coordonnées sont égales si ligne et colonne sont identiques
        return ligne == c.ligne && colonne == c.colonne;
    }

   
    public boolean voisine(Coordonnee c) {
        if (this.colonne==c.colonne){
            return (c.ligne==this.ligne+1) || (c.ligne==this.ligne-1);
        }
        if(c.ligne==this.ligne){
            return (c.colonne==this.colonne+1) || (c.colonne==this.colonne-1);
        }
        return false;
    }


    public int compareTo(Coordonnee c) {
        // Si les lignes sont différentes, on compare d'abord les lignes
        if (this.ligne != c.ligne) {
            return this.ligne - c.ligne;
        }

        return this.colonne - c.colonne;
    }
}