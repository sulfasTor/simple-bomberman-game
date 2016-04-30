import javax.swing.*;
import java.awt.*;

public class Accueil extends JFrame{
	
	private ImageDeFond img;
	private JButton jouer;
	private JLabel message;
	
	public Accueil(){
		super("BOMBINSA");
		
		img = new ImageDeFond();
		img.setLayout(new BorderLayout());
		jouer = new JButton("JOUER");
		message = new JLabel("<html><font color='red' size=6<b><center>BIENVENUE AU MEILLEUR JEU DE L'INSA:<br>BOMBINSA!!!</html>");
		img.add(jouer, BorderLayout.SOUTH);
		img.add(message, BorderLayout.NORTH);
		
		setContentPane(img);
		setSize(700, 500);
		setVisible(true);
		
		jouer.addActionListener(new CommencerJeu(this));
	}
	public static void main(String[] args){
		new Accueil();
	}
}
