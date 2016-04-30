import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.util.*;

//Cette classe est un paneau sur lequel est dessine une photo pour l'accueil du jeu

public class ImageDeFond extends JPanel{
	BufferedImage img;
	
	public ImageDeFond(){
		
		try{
			img=ImageIO.read(new File("bombinsa.jpg"));
		}catch(IOException io){
			io.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(img,0,0,getWidth(), getHeight(),null);
	}
	
}
