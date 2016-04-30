import javax.swing.*;
import java.awt.event.*;

public class BombeEclat implements ActionListener {
	
	private BMPlateau bm;
	private boolean ia;
	
	public BombeEclat(BMPlateau bm, boolean ia){
		this.bm = bm;
		this.ia =ia;
	}
	public void actionPerformed(ActionEvent ae){
		if(ia){
			bm.nextIA(); //On passe a l'action suivante
			bm.stopTimerIA(); //On arrete le timer car la bombe n'explose qu'une seule fois
			bm.repaint();
		}else{
			bm.next();
			bm.stopTimer();
			bm.repaint();
		}
	}
}

