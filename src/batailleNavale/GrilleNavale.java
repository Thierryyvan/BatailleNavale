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
			throw new IllegalArgumentException("La Taille de la grille est incompatible");
		} else if (taille < tailleMax) {
			throw new IllegalArgumentException("La grille ne peut pas supporter la taille " + tailleMax);
		} else {
			this.taille = taille; 
		}
		
		for (int i = 0; i < taillesNavires.length; i++) {
			if (taillesNavires[i] < 1) {
				throw new IllegalArgumentException("La taille des navire doit etre superieure a 1"); // A revoir pour une taille max
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
		} else {
			this.taille = taille; 
		}
		if (nbNavires <= 0) {
			throw new IllegalArgumentException("Il faut au moins un navire pour jouer");
		}
		this.nbNavires = 0;
		
		nbTirsRecus = 0;
		navires = new Navire[nbNavires];
		tirsRecus = new Coordonnee[taille * taille];
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		// Creer la grille de base \\
		
		// Mettre la premiere ligne de lettres
		sb.append("  "); // espace vide pour alignement
		for(int i = 0; i < taille; i++) 
			sb.append(" "+(char) ('A'+ i)+" ");
		
		// Ajouter les autres lignes 
		
		for(int i = 0; i < taille; i++) {
			sb.append("\n");
			
			// Ajouter un espace avant les nombres a 1 chiffre pour aligner, et mettre le num de ligne
			if(i + 1 < 10) 
				sb.append(" "+ (i+1));
			else 
				sb.append(i + 1);
			
			// Ajouter les points
			for(int j = 0; j < taille; j++) {
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

	    // Vérifier si la ligne et la colonne sont dans les bornes valides (indexation commence à 0)
	    return ligne >= 0 && ligne < taille && colonne >= 0 && colonne < taille;
	}

	private boolean estDansTirsRecus(Coordonnee c) {
	    // Parcourir le tableau des tirs reï¿½us
	    for (int i = 0; i < nbTirsRecus; i++) {
	        // Verifier si la coordonnee c correspond a un tir recu
	        if (tirsRecus[i].equals(c)) {
	            return true;
	        }
	    }
	    // Si aucune coordonnee ne correspond, retourner false
	    return false;
	}
	private boolean ajouteDansTirsRecus(Coordonnee c) {
	    // Verifier si la coordonnee est dans la grille
	    if (!this.estDansGrille(c)) {
	        return false;
	    }

	    // Verifier si la coordonnee est deja presente dans tirsRecus
	    if (estDansTirsRecus(c)) {
	        return false; // La coordonnee a dï¿½jï¿½ ï¿½tï¿½ tiree
	    }

	    // Ajouter la coordonnee dans le premier emplacement vide de tirsRecus
	    for (int i = 0; i < this.tirsRecus.length; i++) {
	        if (this.tirsRecus[i] == null) { // Si l'emplacement est vide
	            this.tirsRecus[i] = c;  // Ajouter la coordonnee
	            this.nbTirsRecus++;      // Augmenter le nombre de tirs recus
	            return true;             // Retourner true pour indiquer que la liste a ete modifiee
	        }
	    }

	    return false; // Si on a parcouru tout le tableau sans trouver de place
	}
	public boolean recoitTir(Coordonnee c) {
	    
	    // Vérifier si la coordonnée est dans la grille
	    if (!this.estDansGrille(c)) {
	        return false;
	    }

	    // Vérifier si la coordonnée a déjà été reçue
	    if (estDansTirsRecus(c)) {
	        return false;
	    }

	    // Ajouter la coordonnée aux tirs reçus
	    if (ajouteDansTirsRecus(c)) {
	        return true;
	    }
	    return false;
	}

	public boolean estTouche(Coordonnee c) {
	    // Parcourir tous les navires de la grille
	    for (int i = 0; i < nbNavires; i++) {
	        if (navires[i] != null && navires[i].contient(c)) {
	            // Si la coordonnee est dans le navire et que ce navire a ete touche a cette coordonnee
	            if (navires[i].estTouche(c)) {
	                return true; // Un navire a ete touche a la coordonnee c
	            }
	        }
	    }
	    // Aucun navire n'a ete touche a la coordonnee c
	    return false;
	}
	public boolean estALEau(Coordonnee c) {
	    // Verifier si la coordonnee est un tir recu
	    if (!estDansTirsRecus(c)) {
	        return false; // Si la coordonnï¿½e n'est pas un tir reï¿½u, retourner false
	    }

	    // Verifier que le tir ne touche pas un navire
	    if (estTouche(c)) {
	        return false; // Si la coordonnee touche un navire, ce n'est pas de l'eau
	    }

	    // Si la coordonnee est un tir recu et ne touche pas un navire, c'est de l'eau
	    return true;
	}
	public boolean estCoule(Coordonnee c) {
	    // Parcourir tous les navires de la grille
	    for (int i = 0; i < nbNavires; i++) {
	        if (navires[i] != null && navires[i].contient(c)) {
	            // Si le navire contient la coordonnï¿½e et que le navire est coulï¿½
	            if (navires[i].estTouche(c) && navires[i].estCoule()) {
	                return true; // Le navire est coulï¿½
	            }
	        }
	    }
	    // Aucun navire n'a ï¿½tï¿½ touchï¿½ ou coulï¿½ ï¿½ la coordonnï¿½e c
	    return false;
	}
	public boolean perdu() {
	    // Parcourir tous les navires
	    for (int i = 0; i < nbNavires; i++) {
	        if (navires[i] != null && !navires[i].estCoule()) {
	            return false; // Si un navire n'est pas coulï¿½, retourner false
	        }
	    }
	    // Si tous les navires ont ï¿½tï¿½ coulï¿½s, retourner true
	    return true;
	}

	
	
}













