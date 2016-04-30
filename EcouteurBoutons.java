import java.awt.event.*;
import javax.swing.*;

public class EcouteurBoutons implements ActionListener{
		private BombInsa te;
		private BMPlateau bm;
		private int direction;
		
		public EcouteurBoutons(BombInsa te, int direction){
			this.te = te;
			bm = te.getBMPlateau();
			this.direction = direction;
		}
		
		public void actionPerformed(ActionEvent ae){
			
			if(direction==1) bm.avancerUp();
			else if(direction==2) bm.avancerDown();
			else if(direction==3) bm.avancerLeft();
			else if(direction==4) bm.avancerRight();
			else if(direction==5) bm.deposerBombe();
		}
}
