package batailleNavale;

import java.util.Scanner;

public class JoueurTexte extends JoueurAvecGrille {
	private Scanner sc;
	
	public JoueurTexte(GrilleNavale g, String nom) {
		super(g, nom);
		this.sc = new Scanner(System.in);
	}
	public JoueurTexte(GrilleNavale g) {
		super(g);
		this.sc = new Scanner(System.in);
	}
	
	@Override
	protected void retourAttaque(Coordonnee c, int etat) {
		System.out.println("Vous attaquez en " +  c + " : ");
		
		switch (etat) {
			case 1 : System.out.println("touche !");
			break;
			case 2 : System.out.println("coule !");
			break;
			case 3 : System.out.println("a l'eau !");
			break;
			case 4 : System.out.println("Navire coule ! Vous avez gagne, partie terminee");
			break;
			default: System.out.println("Resultat indisponible.");	
		}
	}
	@Override
	protected void retourDefense(Coordonnee c, int etat) {
	    System.out.print("L'adversaire attaque en " + c + " : ");

	    if (etat == 1) {
	        System.out.println("un de vos navires est touché !");
	    } else if (etat == 2) {
	        System.out.println("un de vos navires est coulé !");
	    } else if (etat == 3) {
	        System.out.println("à l'eau.");
	    } else if (etat == 4) {
	        System.out.println("un de vos navires est coulé ! Vous avez perdu.");
	    } else {
	        System.out.println("résultat indisponible.");
	    }

	}
	public Coordonnee choixAttaque() {
		while (true) {
			System.out.println("Entrez les coordonees de votre attaque (Ex: B7) : ");
			String s = sc.nextLine().trim().toUpperCase();
			try {
				return new Coordonnee(s);
			} catch (IllegalArgumentException e) {
				System.out.println("Coordonnee invalide. Veuillez reessayer");
			}
		}
		
		
	}
}
