package batailleNavale;

import javax.swing.JOptionPane;
import java.awt.Color;

public class JoueurGraphique extends JoueurAvecGrille {

    private GrilleGraphique grilleTirs; 

    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
        super(grilleDefense, nom);
        this.grilleTirs = grilleTirs;
    }

    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs) {
        super(grilleDefense);
        this.grilleTirs = grilleTirs;
    }

    //------------------ Choix de la case à attaquer
    @Override
    public Coordonnee choixAttaque() {
        return grilleTirs.getCoordonneeSelectionnee();
    }

    //------------------ Retour après attaque
    @Override
    protected void retourAttaque(Coordonnee c, int etat) {
        Color couleur = (etat == A_L_EAU) ? Color.BLUE : Color.RED;   
        grilleTirs.colorie(c, couleur); 

        switch (etat) {
            case TOUCHE:
                JOptionPane.showMessageDialog(
                    grilleTirs,
                    "Vous avez touché un navire en " + c
                );
                break;

            case COULE:
                JOptionPane.showMessageDialog(
                    grilleTirs,
                    "Vous avez coulé un navire en " + c
                );
                break;

            case GAMEOVER:
                JOptionPane.showMessageDialog(
                    grilleTirs,
                    "Vous avez gagné !!!"
                );
                break;

            default:
                // Cas de sécurité : état inattendu
                break;
        }
    }

    //------------------ Retour après défense (adversaire tire sur moi)
    @Override
    protected void retourDefense(Coordonnee c, int etat) {
        switch (etat) {
            case TOUCHE:
                JOptionPane.showMessageDialog(
                    grilleTirs,
                    "L'adversaire m'a touché en " + c + "!"
                );
                break;

            case COULE:
                JOptionPane.showMessageDialog(
                    grilleTirs,
                    "L'adversaire a coulé un de mes navires en " + c + "!"
                );
                break;

            case GAMEOVER:
                JOptionPane.showMessageDialog(
                    grilleTirs,
                    "C'est fini, j'ai perdu."
                );
                break;

            default:
                // Cas de sécurité : état inattendu
                break;
        }
    }
}
