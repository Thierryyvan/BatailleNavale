package batailleNavale;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FenetreJoueur extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // Grille de défense (navires + tirs reçus)
    private GrilleNavaleGraphique grilleDefense;

    // Grille d'attaque (où le joueur clique pour tirer)
    private GrilleGraphique grilleTirs;

    public FenetreJoueur() {
        this("Nom du joueur", 10);
    }

    public FenetreJoueur(String nom, int taille) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(nom);

        // Taille de la fenêtre
        if (taille < 19) {
            setBounds(45 * taille, 45 * taille, 90 * taille, 53 * taille);
        } else {
            setBounds(45 * taille, 45 * taille, 90 * taille, 47 * taille);
        }

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(1, 2));
        setContentPane(contentPane);

        // Création des grilles
        grilleTirs = new GrilleGraphique(taille);
        grilleDefense = new GrilleNavaleGraphique(taille);

        // Gestion des clics
        grilleTirs.setClicActive(true);
        grilleDefense.getGrilleGraphique().setClicActive(false);

        // Coloration de la grille de défense
        grilleDefense.getGrilleGraphique().colorie(
                new Coordonnee(0, 0),
                new Coordonnee(taille - 1, taille - 1),
                new Color(0, 51, 102)
        );

        // ===== Panel attaque =====
        JPanel panelAttaque = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblAttaque = new JLabel("Grille d'attaque");
        lblAttaque.setFont(new Font("Dialog", Font.BOLD, 14));
        lblAttaque.setHorizontalAlignment(SwingConstants.CENTER);

        panelAttaque.add(lblAttaque);
        panelAttaque.add(grilleTirs);

        // ===== Panel défense =====
        JPanel panelDefense = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblDefense = new JLabel("Grille de défense");
        lblDefense.setFont(new Font("Dialog", Font.BOLD, 14));
        lblDefense.setHorizontalAlignment(SwingConstants.CENTER);

        panelDefense.add(lblDefense);
        panelDefense.add(grilleDefense.getGrilleGraphique());

        contentPane.add(panelAttaque);
        contentPane.add(panelDefense);
    }

    public GrilleGraphique getGrilleTirs() {
        return grilleTirs;
    }

    public GrilleNavaleGraphique getGrilleDefense() {
        return grilleDefense;
    }

    // Test
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            FenetreJoueur f = new FenetreJoueur("oui", 18);
            f.setVisible(true);
        });
    }
}
