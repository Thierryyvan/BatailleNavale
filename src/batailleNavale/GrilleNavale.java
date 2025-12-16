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
				throw new IllegalArgumentException("La taille d'un navire doit etre superieure a1"); // A revoir pour une taille max
			}	
		}
		nbNavires = 0;
		navires = new Navire[taillesNavires.length];
		nbTirsRecus = 0;
		tirsRecus = new Coordonnee[taille * taille];
		//placementsAuto(taillesNavires); //TODO 
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
		
		// Cr�er la grille de base \\
		
		// Mettre la premi�re ligne de lettres
		sb.append("  "); // espace vide pour alignement
		for(int i = 0; i < taille; i++) 
			sb.append(" "+(char) ('A'+ i)+" ");
		
		// Ajouter les autres lignes 
		
		for(int i = 1; i <= taille; i++) {
			sb.append("\n");
			
			// Ajouter un espace avant les nombres � 1 chiffre pour aligner, et mettre le n� de ligne
			if(i < 10) 
				sb.append(" "+ i);
			else 
				sb.append(i);
			
			// Ajouter les points
			for(int j = 1; j <= taille; j++) {
				Coordonnee caseActuelle = new Coordonnee(i,j);
				boolean tirRecu = false;
				for (int t = 0; t < nbTirsRecus; t++) {
					if (tirsRecus[t].equals(caseActuelle)) {
						tirRecu = true;
						break;
					}
				}
				boolean occupe = false;
				boolean touche = false;
				for (int n = 0; n < nbNavires; n++) {
					if (navires[n].contient(caseActuelle)) {
						occupe = true;
						if (navires[n].estTouche(caseActuelle)) {
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
				} else {
					symbole = '.';
				}
				sb.append(" " + symbole + " ");
			}
			
		}
			
		return sb.toString();
		
	}
	
	public int getTaille() {
		return taille;
	}
	public boolean ajouteNavire(Navire n) {
		if (n.getDebut().getLigne() < 1 || n.getDebut().getLigne() > taille
				|| n.getDebut().getColonne() < 1 || n.getDebut().getColonne() > taille
				|| n.getFin().getLigne() < 1 || n.getFin().getLigne() > taille
				|| n.getFin().getColonne() < 1 || n.getFin().getColonne() > taille) {
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
			Rlig = 1 + rand.nextInt(taille - tailleNavire + 1);
			Rcol = 1 + rand.nextInt(taille);
		} else {
			Rlig = 1 + rand.nextInt(taille);
			Rcol = 1 + rand.nextInt(taille - tailleNavire + 1);
		}
		return new Navire(new Coordonnee (Rlig, Rcol), tailleNavire, estVertical);
	}
	private boolean estDansGrille(Coordonnee c) {
	    int ligne = c.getLigne();
	    int colonne = c.getColonne();

	    // V�rifier si la ligne et la colonne sont dans les bornes valides
	    return ligne >= 1 && ligne <= taille && colonne >= 1 && colonne <= taille;
	}
	private boolean estDansTirsRecus(Coordonnee c) {
	    // Parcourir le tableau des tirs re�us
	    for (int i = 0; i < nbTirsRecus; i++) {
	        // V�rifier si la coordonn�e c correspond � un tir re�u
	        if (tirsRecus[i].equals(c)) {
	            return true;
	        }
	    }
	    // Si aucune coordonn�e ne correspond, retourner false
	    return false;
	}
	private boolean ajouteDansTirsRecus(Coordonnee c) {
	    // V�rifier si la coordonn�e est dans la grille
	    if (!this.estDansGrille(c)) {
	        return false;
	    }

	    // V�rifier si la coordonn�e est d�j� pr�sente dans tirsRecus
	    if (estDansTirsRecus(c)) {
	        return false; // La coordonn�e a d�j� �t� tir�e
	    }

	    // Ajouter la coordonn�e dans le premier emplacement vide de tirsRecus
	    for (int i = 0; i < this.tirsRecus.length; i++) {
	        if (this.tirsRecus[i] == null) { // Si l'emplacement est vide
	            this.tirsRecus[i] = c;  // Ajouter la coordonn�e
	            this.nbTirsRecus++;      // Augmenter le nombre de tirs re�us
	            return true;             // Retourner true pour indiquer que la liste a �t� modifi�e
	        }
	    }

	    return false; // Si on a parcouru tout le tableau sans trouver de place
	}
	public boolean recoitTir(Coordonnee c) {
	    // V�rifier si la coordonn�e a d�j� �t� re�ue
	    if (estDansTirsRecus(c)) {
	        return false; // Si la coordonn�e a d�j� �t� re�ue, ne rien faire et retourner false
	    }

	    // Ajouter la coordonn�e aux tirs re�us
	    if (!ajouteDansTirsRecus(c)) {
	        return false; // Si l'ajout �choue (par exemple, tableau plein), retourner false
	    }

	    // V�rifier si la coordonn�e touche un navire
	    for (int i = 0; i < nbNavires; i++) {
	        if (navires[i] != null && navires[i].recoitTir(c)) {
	            // Si le navire contient la coordonn�e et re�oit le tir, retourner true
	            return true;
	        }
	    }

	    // Si aucun navire n'a �t� touch�, retourner false
	    return false;
	}
	public boolean estTouche(Coordonnee c) {
	    // Parcourir tous les navires de la grille
	    for (int i = 0; i < nbNavires; i++) {
	        if (navires[i] != null && navires[i].contient(c)) {
	            // Si la coordonn�e est dans le navire et que ce navire a �t� touch� � cette coordonn�e
	            if (navires[i].estTouche(c)) {
	                return true; // Un navire a �t� touch� � la coordonn�e c
	            }
	        }
	    }
	    // Aucun navire n'a �t� touch� � la coordonn�e c
	    return false;
	}
	public boolean estALEau(Coordonnee c) {
	    // V�rifier si la coordonn�e est un tir re�u
	    if (!estDansTirsRecus(c)) {
	        return false; // Si la coordonn�e n'est pas un tir re�u, retourner false
	    }

	    // V�rifier que le tir ne touche pas un navire
	    if (estTouche(c)) {
	        return false; // Si la coordonn�e touche un navire, ce n'est pas de l'eau
	    }

	    // Si la coordonn�e est un tir re�u et ne touche pas un navire, c'est de l'eau
	    return true;
	}
	public boolean estCoule(Coordonnee c) {
	    // Parcourir tous les navires de la grille
	    for (int i = 0; i < nbNavires; i++) {
	        if (navires[i] != null && navires[i].contient(c)) {
	            // Si le navire contient la coordonn�e et que le navire est coul�
	            if (navires[i].estTouche(c) && navires[i].estCoule()) {
	                return true; // Le navire est coul�
	            }
	        }
	    }
	    // Aucun navire n'a �t� touch� ou coul� � la coordonn�e c
	    return false;
	}
	public boolean perdu() {
	    // Parcourir tous les navires
	    for (int i = 0; i < nbNavires; i++) {
	        if (navires[i] != null && !navires[i].estCoule()) {
	            return false; // Si un navire n'est pas coul�, retourner false
	        }
	    }
	    // Si tous les navires ont �t� coul�s, retourner true
	    return true;
	}

	
	
}













