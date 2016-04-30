import java.awt.event.*;
import javax.swing.*;

public class CommencerJeu implements ActionListener{
		private Accueil ac;
		
		public CommencerJeu(Accueil ac){
			this.ac=ac;
		}
		
		public void actionPerformed(ActionEvent ae){
			ac.setVisible(false);
			new BombInsa();
		}
}
