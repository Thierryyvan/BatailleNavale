
package batailleNavale;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class JButtonCoordonnee extends JButton {

	private Coordonnee c;

	public JButtonCoordonnee(Coordonnee c) {
		this.c = c;
	}

	public Coordonnee getCoordonnee() {
		return c;
	}
}

/**
 * Classe représentant un composant graphique "Grille". Une grille est composée
 * de JButton
 * 
 * @author jerome.david@univ-grenoble-alpes.fr
 * 
 */
public class GrilleGraphique extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8857166149660579225L;

	/**
	 * La matrice des boutons (cases de la grille)
	 */
	private JButton[][] cases;

	/**
	 * La coordonnee actuellement selectionnée. Null si aucune selection en cours
	 */
	private Coordonnee coordonneeSelectionnee;

	/**
	 * Initialise une grille carrée de taille donnée
	 * 
	 * @param taille la taille de la grille
	 */
	public GrilleGraphique(int taille) {
		try {
			// Certains LookAndFeels ne colorient pas les boutons.
			// on choisi celui le plus simple (et le moins joli)
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		this.setLayout(new GridLayout(taille + 1, taille + 1));

		this.add(new JLabel());
		for (int i = 0; i < taille; i++) {
			JLabel lbl = new JLabel(String.valueOf((char) ('A' + i)));
			lbl.setHorizontalAlignment(JLabel.CENTER);
			this.add(lbl);
		}
		cases = new JButton[taille][taille];
		for (int i = 0; i < taille; i++) {
			JLabel lbl = new JLabel(String.valueOf(i + 1));
			lbl.setHorizontalAlignment(JLabel.CENTER);
			this.add(lbl);
			for (int j = 0; j < taille; j++) {
				cases[i][j] = new JButtonCoordonnee(new Coordonnee(i, j));
				this.add(cases[i][j]);
				cases[i][j].addActionListener(this);
				cases[i][j].setPreferredSize(new Dimension(40, 40));
			}
		}
		coordonneeSelectionnee = null;
	}

	/**
	 * Colorie la case indiquée par la coordonnée
	 * 
	 * @param coord la coordonnée de la case à colorier
	 * @param color la couleur de la case
	 */
	public void colorie(Coordonnee cord, Color color) {
		cases[cord.getLigne()][cord.getColonne()].setBackground(color);

	}
	public void setImage(Coordonnee cord, boolean touche) {
		if ( touche ) {
			BufferedImage feu = null;
			try {
				feu = ImageIO.read(getClass().getResourceAsStream("feu.png"));
				feu = resize(feu, 40, 40);

			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			ImageIcon courante = new ImageIcon(feu);
			cases[cord.getLigne()][cord.getColonne()].setIcon(courante);
			cases[cord.getLigne()][cord.getColonne()].setDisabledIcon(courante);

		}
		else {
			BufferedImage eau = null;
			try {
				eau = ImageIO.read(getClass().getResourceAsStream("eau.png"));
				eau = resize(eau, 40, 40);

			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			ImageIcon courante = new ImageIcon(eau);
			cases[cord.getLigne()][cord.getColonne()].setIcon(courante);
			cases[cord.getLigne()][cord.getColonne()].setDisabledIcon(courante);

		}
	}

	/**
	 * Colorie le rectangle compris entre les deux coordonnees
	 * 
	 * @param debut Coordonnée du début de la zone à colorier (haut gauche)
	 * @param fin   Coordonnée de la fin de la zone à colorier (bas droit)
	 * @param color la couleur de la case
	 */
	public void colorie(Coordonnee debut, Coordonnee fin, Color color) {
		for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
			for (int j = debut.getColonne(); j <= fin.getColonne(); j++) {
				cases[i][j].setBackground(color);
			}
		}
	}

	public void setImageNavireVertical(Coordonnee debut, Coordonnee fin) {
		for (int i = debut.getLigne() + 1; i <= fin.getLigne() - 1; i++) {
			for (int j = debut.getColonne(); j <= fin.getColonne(); j++) {
				BufferedImage bateauVertical = null;
				try {
					bateauVertical = ImageIO.read(getClass().getResourceAsStream("navire.png"));
					bateauVertical = resize(bateauVertical, 40, 40);

				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
				System.out.println("je suis là");
				ImageIcon courante = new ImageIcon(bateauVertical);
				cases[i][j].setIcon(courante);
				cases[i][j].setDisabledIcon(courante);

			}
		}
		BufferedImage hautVertical = null;
		BufferedImage basVertical = null;
		try {
			hautVertical = ImageIO.read(getClass().getResourceAsStream("hautNavire.png"));
			hautVertical = resize(hautVertical, 40, 40);
			basVertical = ImageIO.read(getClass().getResourceAsStream("basNavire.png"));
			basVertical = resize(basVertical, 40, 40);

		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		ImageIcon haut = new ImageIcon(hautVertical);
		ImageIcon bas = new ImageIcon(basVertical);
		cases[debut.getLigne()][debut.getColonne()].setDisabledIcon(haut);
		cases[fin.getLigne()][fin.getColonne()].setDisabledIcon(bas);
		cases[debut.getLigne()][debut.getColonne()].setIcon(haut);
		cases[fin.getLigne()][fin.getColonne()].setIcon(bas);
	}

	

	public void setImageNavireHorizontal(Coordonnee debut, Coordonnee fin) {
		for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
			for (int j = debut.getColonne() + 1; j <= fin.getColonne() - 1; j++) {
				BufferedImage bateauVertical = null;
				try {
					bateauVertical = ImageIO.read(getClass().getResourceAsStream("navire.png"));
					bateauVertical = resize(bateauVertical, 40, 40);

				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
				System.out.println("je suis là");
				ImageIcon courante = new ImageIcon(bateauVertical);
				cases[i][j].setDisabledIcon(courante);
				cases[i][j].setIcon(courante);

			}
		}
		BufferedImage gauche = null;
		BufferedImage droite = null;
		try {
			gauche = ImageIO.read(getClass().getResourceAsStream("gaucheNavire.png"));
			gauche = resize(gauche, 40, 40);
			droite = ImageIO.read(getClass().getResourceAsStream("droiteNavire.png"));
			droite = resize(droite, 40, 40);

		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		ImageIcon gaucheNavire = new ImageIcon(gauche);
		ImageIcon droiteNavire = new ImageIcon(droite);
		cases[debut.getLigne()][debut.getColonne()].setDisabledIcon(gaucheNavire);
		cases[fin.getLigne()][fin.getColonne()].setDisabledIcon(droiteNavire);
		cases[debut.getLigne()][debut.getColonne()].setIcon(gaucheNavire);
		cases[fin.getLigne()][fin.getColonne()].setIcon(droiteNavire);
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return dimg;
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		d.setSize(d.width, d.width);
		return d;
	}

	public void setClicActive(boolean active) {
		SwingUtilities.invokeLater(() -> {
			this.setEnabled(false);
			for (JButton[] ligne : cases) {
				for (JButton bt : ligne) {
					bt.setEnabled(active);
				}
			}
			this.setEnabled(true);
		});
	}

	/**
	 * Methode appelée lorsque l'on clique sur une case de la grille. Elle
	 * "reveille" la méthode getCoordonneeSelectionnee
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setClicActive(false);
		coordonneeSelectionnee = ((JButtonCoordonnee) e.getSource()).getCoordonnee();
		synchronized (this) {
			this.notifyAll();
		}
	}

	/**
	 * Attend que l'utilisateur selectionne (clic) sur une case de la grille et
	 * retourne la coordonnee qui a été selectionnée
	 * 
	 * @return la coordonnée selectionnée
	 */
	public synchronized Coordonnee getCoordonneeSelectionnee() {
		this.setClicActive(true);
		try {
			this.wait();
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
		return coordonneeSelectionnee;
	}

}
