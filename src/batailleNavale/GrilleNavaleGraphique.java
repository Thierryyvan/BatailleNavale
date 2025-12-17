package batailleNavale;

import java.awt.Color;

/**
 * @author Thierry
 * @author Irvin
 * @author Samir
 * @author Louis
 * @author Ziang
 * @author Aleksei
 */

public class GrilleNavaleGraphique extends GrilleNavale {
	private GrilleGraphique grille;
	
	public GrilleNavaleGraphique(int taille) {
		super(taille, 5);
		grille = new GrilleGraphique(taille);
		
	}
	
	public GrilleGraphique getGrilleGraphique() {
		return this.grille;
	}
	
	public boolean ajouteNavire(Navire n) {
		if (super.ajouteNavire(n)) {
			this.grille.colorie(n.getDebut(), n.getFin(), Color.GREEN);
			return true;
		} else {
			 return false;
		}
		
	}
	public boolean recoitTir(Coordonnee c) {
		boolean touche = super.recoitTir(c);
		if (touche) {
			if (this.estTouche(c)) {
				this.grille.colorie(c, Color.RED);
			} else {
				this.grille.colorie(c, Color.BLUE);
			}
		}
		return touche;
	}

}
