package batailleNavale;

/**
 * @author Thierry
 * @author Irvin
 * @author Samir
 * @author Louis
 * @author Ziang
 * @author Aleksei
 *
 * @param navires : tableau des navires presents sur la grille
 * @param nbNavires : nombre de navires presents sur la grille
 * @param taille : taille de la grille (taille x taille)
 * @param tirsRecus : tableau des coordonnees des tirs recus
 * @param nbTirsRecus : nombre de tirs recus
 */
public class GrilleNavale {
	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;
	
	public GrilleNavale(int taille, int[] taillesNavires) {
		int tailleMax = taillesNavires[0];
		
		for (int i = 1; i < taille; i++) {
			if (taillesNavires[i] > taillesNavires[i-1]) {
				tailleMax = taillesNavires[i];
			}
		}
		
		if (taille > 26 || taille < 2) {
			throw new IllegalArgumentException("Taille de la grille incompatible");
		} else if (taille < tailleMax) {
			throw new IllegalArgumentException("Taille de la grille incompatible");
		} else {
			this.taille = taille; 
		}
		
		for (int i = 0; i < taillesNavires.length; i++) {
			if (taillesNavires[i] < 1) {
				throw new IllegalArgumentException("La taille d'un navire doit �tre sup�rieure � 1"); // A revoir pour une taille max
			}	
		}
		nbNavires = 0;
		navires = new Navire[taillesNavires.length];
		nbTirsRecus = 0;
		tirsRecus = new Coordonnee[taille * taille];
		placementsAuto(taillesNavires); //TODO 
	}
	
	public GrilleNavale(int taille, int nbNavires) {
		
		if (taille > 26 || taille < 2) {
			throw new IllegalArgumentException("Taille de la grille incompatible");
		} else {
			this.taille = taille; 
		}
		if (nbNavires <= 0) {
			throw new IllegalArgumentException("il faut au moins un navire pour jouer");
		}
		this.nbNavires = 0;
		
		nbTirsRecus = 0;
		navires = new Navire[nbNavires];
		tirsRecus = new Coordonnee[taille * taille];
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		// Cr�ation de l'ent�te
		sb.append("  ");
		for (int i = 0; i < taille; i++) {
			sb.append(""+(char) ('A'+i)+" ");
		}
		sb.append("\n");
		// Cr�ation des indices de lignes
		for (int i = 1; i <= taille; i++) {
			if (i < 10) {
				sb.append(" " + i + " ");
			} else {
				sb.append(i + " ");
			}
			for (int j = 0; j < taille; j++) {
				sb.append(" .");
			}
			
			sb.append("\n");
		}
		return sb.toString();
		
	}
	
	public int getTaille() {
		return taille;
	}
	public boolean ajouteNavire(Navire n) {
		
	}
	
}













































>>>>>>> 97c25796a5846888fe14dfae2498a0c38112623c
