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
        // Affecte la valeur du paramètre ligne à l'attribut ligne
        this.ligne = ligne;

        // Affecte la valeur du paramètre colonne à l'attribut colonne
        this.colonne = colonne;
    }

    // Constructeur n°2
    // Permet de créer une coordonnée à partir d'une chaîne de caractères
    // Exemple : "A5"
    public Coordonnee(String s) {
        // Convertit la première lettre (ex : 'A') en indice de ligne
        // 'A' devient 0, 'B' devient 1, etc.
        this.ligne = s.charAt(0) - 'A';

        // Convertit la partie numérique de la chaîne (ex : "5") en entier
        this.colonne = Integer.parseInt(s.substring(1));
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