package batailleNavale;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FenetreJoueur extends JFrame {

    private static final long serialVersionUID = 1L;

    private GrilleNavaleGraphique grilleDefense;
    private GrilleGraphique grilleTirs;

    public FenetreJoueur() {
        this("Nom du joueur", 10, new int[]{1,2,3,4});
    }

    public FenetreJoueur(String nom, int taille) {
        this(nom, taille, new int[]{1,2,3,4});
    }

    public FenetreJoueur(String nom, int taille, int[] navires) {

        setTitle("Centre naval de " + nom);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 450);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new GridLayout(1, 2, 10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Grilles
        grilleTirs = new GrilleGraphique(taille);
        grilleDefense = new GrilleNavaleGraphique(taille);

        // PLACEMENT DES NAVIRES (OBLIGATOIRE)
        grilleDefense.placementAuto(navires);

        // Activation clics
        grilleTirs.setClicActive(true);
        grilleDefense.getGrilleGraphique().setClicActive(false);

        // ----- Panel Attaque -----
        JPanel panelAttaque = new JPanel(new BorderLayout());
        panelAttaque.add(new JLabel("Grille d'attaque", JLabel.CENTER), BorderLayout.NORTH);
        panelAttaque.add(grilleTirs, BorderLayout.CENTER);

        // ----- Panel Défense -----
        JPanel panelDefense = new JPanel(new BorderLayout());
        panelDefense.add(new JLabel("Grille de défense", JLabel.CENTER), BorderLayout.NORTH);
        panelDefense.add(grilleDefense.getGrilleGraphique(), BorderLayout.CENTER);

        contentPane.add(panelAttaque);
        contentPane.add(panelDefense);
    }

    public GrilleGraphique getGrilleTirs() {
        return grilleTirs;
    }

    public GrilleNavaleGraphique getGrilleDefense() {
        return grilleDefense;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FenetreJoueur("Test", 10).setVisible(true);
        });
    }
}
