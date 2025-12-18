package batailleNavale;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class BatailleNavale {

    // Couleurs et polices
    public static final Color BG_COLOR = new Color(245, 246, 251);
    public static final Color PANEL_COLOR = new Color(211, 217, 234);
    private static final Color BUTTON_COLOR = new Color(41, 79, 107);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Color ERROR_COLOR = Color.RED;

    private static final Font LABEL_FONT = new Font("Fira Sans", Font.PLAIN, 12);
    public static final Font PANEL_FONT = new Font("Fira Sans", Font.BOLD, 12);
    private static final Font BUTTON_FONT = new Font("Fira Sans", Font.BOLD, 13);

    private JFrame frame;
    private JTextField textTailleGrille;
    private JTextField textNomJ1;
    private JTextField textNomJ2;
    private JLabel lblErreur;

    private JRadioButton rdbtnJ1Graphique, rdbtnJ1Texte, rdbtnJ1Auto, rdbtnIamoyen;
    private JRadioButton rdbtnJ2Graphique, rdbtnJ2Texte, rdbtnJ2Auto, rdbtnIamoyen2;
    private final ButtonGroup groupeJ1 = new ButtonGroup();
    private final ButtonGroup groupeJ2 = new ButtonGroup();

    private Joueur joueur1, joueur2;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BatailleNavale window = new BatailleNavale();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BatailleNavale() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Bataille Navale");
        frame.setBounds(200, 200, 300, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(BG_COLOR);
        frame.getContentPane().setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(BG_COLOR);
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        // --- Taille de la grille ---
        JPanel panelTaille = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTaille.setBackground(BG_COLOR);
        panelPrincipal.add(panelTaille);

        JLabel lblTaille = new JLabel("Taille de la grille : ");
        lblTaille.setFont(LABEL_FONT);
        panelTaille.add(lblTaille);

        textTailleGrille = new JTextField("10", 5);
        textTailleGrille.setBackground(Color.WHITE);
        panelTaille.add(textTailleGrille);

        // --- Joueur 1 ---
        JPanel panelJ1 = new JPanel();
        panelJ1.setBorder(new TitledBorder(null, "Joueur 1", TitledBorder.LEFT, TitledBorder.TOP, PANEL_FONT));
        panelJ1.setLayout(new BoxLayout(panelJ1, BoxLayout.Y_AXIS));
        panelJ1.setBackground(BG_COLOR);
        panelPrincipal.add(panelJ1);

        JPanel panelNomJ1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNomJ1.setBackground(BG_COLOR);
        panelJ1.add(panelNomJ1);

        panelNomJ1.add(new JLabel("Nom : "));
        textNomJ1 = new JTextField("Samir", 12);
        textNomJ1.setBackground(Color.WHITE);
        panelNomJ1.add(textNomJ1);

        rdbtnJ1Graphique = new JRadioButton("Joueur graphique", true);
        rdbtnJ1Texte = new JRadioButton("Joueur texte");
        rdbtnJ1Auto = new JRadioButton("IA (Facile)");
        rdbtnIamoyen = new JRadioButton("IA (Moyen)");

        groupeJ1.add(rdbtnJ1Graphique);
        groupeJ1.add(rdbtnJ1Texte);
        groupeJ1.add(rdbtnJ1Auto);
        groupeJ1.add(rdbtnIamoyen);

        panelJ1.add(rdbtnJ1Graphique);
        panelJ1.add(rdbtnJ1Texte);
        panelJ1.add(rdbtnJ1Auto);
        panelJ1.add(rdbtnIamoyen);

        // --- Joueur 2 ---
        JPanel panelJ2 = new JPanel();
        panelJ2.setBorder(new TitledBorder(null, "Joueur 2", TitledBorder.LEFT, TitledBorder.TOP, PANEL_FONT));
        panelJ2.setLayout(new BoxLayout(panelJ2, BoxLayout.Y_AXIS));
        panelJ2.setBackground(BG_COLOR);
        panelPrincipal.add(panelJ2);

        JPanel panelNomJ2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNomJ2.setBackground(BG_COLOR);
        panelJ2.add(panelNomJ2);

        panelNomJ2.add(new JLabel("Nom : "));
        textNomJ2 = new JTextField("Louis", 12);
        textNomJ2.setBackground(Color.WHITE);
        panelNomJ2.add(textNomJ2);

        rdbtnJ2Graphique = new JRadioButton("Joueur graphique");
        rdbtnJ2Texte = new JRadioButton("Joueur texte");
        rdbtnJ2Auto = new JRadioButton("IA (Facile)", true);
        rdbtnIamoyen2 = new JRadioButton("IA (Moyen)");

        groupeJ2.add(rdbtnJ2Graphique);
        groupeJ2.add(rdbtnJ2Texte);
        groupeJ2.add(rdbtnJ2Auto);
        groupeJ2.add(rdbtnIamoyen2);

        panelJ2.add(rdbtnJ2Graphique);
        panelJ2.add(rdbtnJ2Texte);
        panelJ2.add(rdbtnJ2Auto);
        panelJ2.add(rdbtnIamoyen2);

        // --- Bouton et message d'erreur ---
        JPanel panelBas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBas.setBackground(BG_COLOR);
        panelPrincipal.add(panelBas);

        lblErreur = new JLabel(" ");
        lblErreur.setForeground(ERROR_COLOR);
        panelBas.add(lblErreur);

        JButton btnLancer = new JButton("Lancer la partie");
        btnLancer.setFont(BUTTON_FONT);
        btnLancer.setBackground(BUTTON_COLOR);
        btnLancer.setForeground(BUTTON_TEXT_COLOR);
        panelBas.add(btnLancer);

        btnLancer.addActionListener(e -> demarrerPartie());
    }

    private void demarrerPartie() {
        int taille;
        try {
            taille = Integer.parseInt(textTailleGrille.getText());
            if (taille < 3) {
                lblErreur.setText("La taille de la grille doit être >= 3");
                return;
            } else if (taille > 25) {
                lblErreur.setText("La taille de la grille doit être <= 25");
                return;
            }
        } catch (NumberFormatException e) {
            lblErreur.setText("Entrez un nombre pour la taille");
            return;
        }

        String nomJ1 = textNomJ1.getText().isEmpty() ? "Joueur 1" : textNomJ1.getText();
        String nomJ2 = textNomJ2.getText().isEmpty() ? "Joueur 2" : textNomJ2.getText();

        int[] navires = genererTableauNavires(taille);

        // --- Création Joueur 1 ---
        if (rdbtnJ1Graphique.isSelected()) {
            FenetreJoueur fj1 = new FenetreJoueur(nomJ1, taille, navires);
            joueur1 = new JoueurGraphique(fj1.getGrilleDefense(), fj1.getGrilleTirs(), nomJ1);
            fj1.setVisible(true);
        } else if (rdbtnJ1Texte.isSelected()) {
            joueur1 = new JoueurTexte(new GrilleNavale(taille, navires), nomJ1);
        } else if (rdbtnJ1Auto.isSelected()) {
            joueur1 = new JoueurAuto(new GrilleNavale(taille, navires), nomJ1);
        } else { // IA Moyen
            joueur1 = new JoueurAutoNiveau2(new GrilleNavale(taille, navires), nomJ1);
        }

        // --- Création Joueur 2 ---
        if (rdbtnJ2Graphique.isSelected()) {
            FenetreJoueur fj2 = new FenetreJoueur(nomJ2, taille, navires);
            joueur2 = new JoueurGraphique(fj2.getGrilleDefense(), fj2.getGrilleTirs(), nomJ2);
            fj2.setVisible(true);
        } else if (rdbtnJ2Texte.isSelected()) {
            joueur2 = new JoueurTexte(new GrilleNavale(taille, navires), nomJ2);
        } else if (rdbtnJ2Auto.isSelected()) {
            joueur2 = new JoueurAuto(new GrilleNavale(taille, navires), nomJ2);
        } else { // IA Moyen
            joueur2 = new JoueurAutoNiveau2(new GrilleNavale(taille, navires), nomJ2);
        }

        // Lancer la partie
        new Thread(() -> joueur1.jouerAvec(joueur2)).start();
    }

    private int[] genererTableauNavires(int taille) {
        int maxNavires = Math.min(15, taille * 2); // nombre max de navires selon la taille
        int[] navires = new int[maxNavires];

        int index = 0;
        for (int i = 1; i <= taille / 2 && index < maxNavires; i++) {
            navires[index++] = Math.min(i, 5); // longueur max = 5
        }

        return java.util.Arrays.copyOf(navires, index); // taille réelle du tableau
    }

}
