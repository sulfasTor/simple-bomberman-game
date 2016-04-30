import javax.swing.*;
import java.awt.event.*;

public class Bruler implements ActionListener {
	
	private BMPlateau bm;
	private int[][] pos;
	
	public Bruler(BMPlateau bm){
		this.bm = bm;
		pos = bm.getPlateau();
	}
	public void actionPerformed(ActionEvent ae){
		for(int i=0;i<pos.length;i++){
			
			for(int j=0;j<pos[0].length;j++){
				
				int x=bm.getX();
				int y=bm.getY();
				int x1=bm.getPosIA()[0];
				int y1=bm.getPosIA()[1];
				
				if (pos[y][x]==4){
					bm.finirJeu(false);
				}
				/*else if(pos[y1][x1]==4){
					bm.finirJeu(true);
				}*/
				if(pos[i][j]==4){
					pos[i][j]=3;
					bm.repaint();
				}
			}
		}
		bm.repaint();
	}
}

