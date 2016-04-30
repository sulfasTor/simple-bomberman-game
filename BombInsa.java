import javax.swing.*;
import java.awt.*;

public class BombInsa extends JFrame {
	
	private BMPlateau bm;
	private JPanel boutons;
	private JPanel panneau;
	private JButton monter;
	private JButton descendre;
	private JButton droite;
	private JButton gauche;
	private JButton bombe;
	
	public BombInsa(){
		panneau = new JPanel(new BorderLayout());
		boutons =new JPanel(new GridLayout(3,2));
		bm = new BMPlateau();
		monter = new JButton("^");
		descendre = new JButton("v");
		droite = new JButton(">");
		gauche = new JButton("<");
		bombe = new JButton("Deposer bombe");
		
		boutons.add(monter);
		boutons.add(bombe);
		boutons.add(gauche);
		boutons.add(droite);
		boutons.add(descendre);
		
		panneau.add(bm, BorderLayout.CENTER );
		panneau.add(boutons, BorderLayout.EAST);
		setContentPane(panneau);
		setFocusable(true);
		setSize(1200, 700);
		setVisible(true);
		
		monter.addActionListener(new EcouteurBoutons(this,1));
		descendre.addActionListener(new EcouteurBoutons(this,2));
		gauche.addActionListener(new EcouteurBoutons(this,3));
		droite.addActionListener(new EcouteurBoutons(this,4));
		bombe.addActionListener(new EcouteurBoutons(this,5));
	}
	public BMPlateau getBMPlateau(){
		return bm;
	}
}

