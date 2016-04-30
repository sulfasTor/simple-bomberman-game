import javax.swing.*;
import java.awt.event.*;

public class JouerIA implements ActionListener {
	
	private BMPlateau bm;
	private IA ia;
	private int[] pos;
	
	public JouerIA(BMPlateau bm){
		this.bm =bm;
		ia = bm.getIA();
	}
	public void actionPerformed(ActionEvent ae){
		pos = ia.jouer();
		bm.setPosIA(pos);
		bm.repaint();
	}
}

