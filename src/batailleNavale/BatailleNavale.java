package batailleNavale;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.ButtonGroup;
import javax.swing.border.EtchedBorder;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class BatailleNavale {

	private JFrame frame;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BatailleNavale window = new BatailleNavale();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BatailleNavale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 233, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel north = new JPanel();
		frame.getContentPane().add(north, BorderLayout.NORTH);
		north.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Taille de grille : ");
		north.add(lblNewLabel);
		
		textField = new JTextField();
		north.add(textField);
		textField.setColumns(10);
		
		JPanel south = new JPanel();
		frame.getContentPane().add(south, BorderLayout.SOUTH);
		south.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Lancer la partie\r\n");
		south.add(btnNewButton);
		
		JPanel center = new JPanel();
		frame.getContentPane().add(center, BorderLayout.CENTER);
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		
		JPanel joueur_1 = new JPanel();
		joueur_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Joueur 1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		center.add(joueur_1);
		joueur_1.setLayout(new BoxLayout(joueur_1, BoxLayout.Y_AXIS));
		
		JPanel entete_1 = new JPanel();
		joueur_1.add(entete_1);
		entete_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Nom :");
		entete_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(15);
		entete_1.add(textField_1);
		
		JPanel panelRadiosWrapper = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelRadiosWrapper.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		joueur_1.add(panelRadiosWrapper);
		
		JPanel panelRadios = new JPanel();
		panelRadiosWrapper.add(panelRadios);
		panelRadios.setLayout(new BoxLayout(panelRadios, BoxLayout.Y_AXIS));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Joueur graphique");
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
		panelRadios.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Joueur texte");
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		panelRadios.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Joueur auto");
		rdbtnNewRadioButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		panelRadios.add(rdbtnNewRadioButton_2);
		
		JPanel joueur_2 = new JPanel();
		joueur_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Joueur 2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		center.add(joueur_2);
		joueur_2.setLayout(new BoxLayout(joueur_2, BoxLayout.Y_AXIS));
		
		JPanel entete_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) entete_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		joueur_2.add(entete_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nom :");
		entete_2.add(lblNewLabel_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(15);
		entete_2.add(textField_2);
		
		JPanel panelRadiosWrapper_2 = new JPanel();
		joueur_2.add(panelRadiosWrapper_2);
		panelRadiosWrapper_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panelRadios_2 = new JPanel();
		panelRadiosWrapper_2.add(panelRadios_2);
		panelRadios_2.setLayout(new BoxLayout(panelRadios_2, BoxLayout.Y_AXIS));
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Joueur graphique");
		panelRadios_2.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Joueur texte");
		panelRadios_2.add(rdbtnNewRadioButton_1_1);
		
		JRadioButton rdbtnNewRadioButton_2_1 = new JRadioButton("Joueur auto");
		panelRadios_2.add(rdbtnNewRadioButton_2_1);
	}

}
