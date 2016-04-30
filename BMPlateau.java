/*
 * 0=case indestructible
 * 1=case destructible
 * 2=case avec bombe
 * 3=case libre
 * 4=case en feu. Celui qui soit sur elle meurt.
 * 
 * 
 * 
 * */
import java.util.Arrays;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BMPlateau extends JPanel{
	
	private int xis=1; //C'est la coordonne x pour la position du personnage
	private int yec=1; //C'est la coordonne y pour la position du personnage
	private int originX=0; //C'est l'origine en x de la case qu'on va dessiner
	private int originY=0; //idem
	private int incrementX=0; //C'est la longueur de la case
	private int incrementY=0; //C'est la largeur de la case
	private int[] positionBombes; //On garde ici la position de bombes en jeu
	private int[]pos; //On garde ici les positions du l'IA
	private boolean bombeActive=false; //Nous dit si des bombes sont acitfs en jeu
	private boolean bombeActiveIA=false; //Nous dit si des bombes sont acitfs en jeu
	private boolean finJeu=false;
	private Timer t; //Timer pour le temps d'explosion
	private Timer tIA; //Timer pour le temps d'explosion pour l'IA
	private Timer timer;
	private IA ia;
	private int[][] plateau = {{0,0,0,0,0,0,0,0,0,0},
							   {0,3,3,1,1,1,1,1,1,0},
							   {0,3,0,1,0,1,0,1,0,0},
							   {0,1,1,1,1,1,1,1,1,0},
							   {0,1,0,1,0,1,0,1,0,0},
							   {0,1,1,1,1,1,1,1,1,0},
							   {0,1,0,1,0,1,0,1,0,0},
							   {0,1,1,1,1,1,1,1,3,0},
							   {0,1,0,1,0,1,0,3,3,0},
							   {0,0,0,0,0,0,0,0,0,0}};
							  
	private int[][] gagne={{0,0,0,0,0,0,0,0,0,0},
						   {0,0,0,0,0,0,0,0,0,0},
						   {0,0,3,3,0,0,3,3,0,0},
						   {0,0,3,3,0,0,3,3,0,0},
						   {0,0,0,0,0,0,0,0,0,0},
						   {0,3,0,0,0,0,0,0,3,0},
						   {0,3,0,0,0,0,0,0,3,0},
						   {0,0,3,0,0,0,0,3,0,0},
						   {0,0,0,3,3,3,3,0,0,0},
						   {0,0,0,0,0,0,0,0,0,0}};
						   
	private int[][] perdu={{0,0,0,0,0,0,0,0,0,0},
						   {0,0,0,0,0,0,0,0,0,0},
						   {0,0,3,3,0,0,3,3,0,0},
						   {0,0,3,3,0,0,3,3,0,0},
						   {0,0,0,0,0,0,0,0,0,0},
						   {0,0,0,0,3,3,0,0,0,0},
						   {0,0,0,3,0,0,3,0,0,0},
						   {0,0,3,0,0,0,0,3,0,0},
						   {0,3,0,0,0,0,0,0,3,0},
						   {0,3,0,0,0,0,0,0,3,0}};
							   
	public BMPlateau(){
		positionBombes=new int[4]; //deux c'est le maximum de bombes en jeu simultanement
		ia=new IA(this);
		pos=ia.jouer();
		repaint();
		timer = new Timer(700, new JouerIA(this));
		timer.start();
	}
	//Getteur
	public void montrerTableau(){
		for(int[] array : plateau) System.out.println(Arrays.toString(array));
		System.out.println();
	}
	public int[][] getPlateau(){
		return plateau;
	}
	public IA getIA(){
		return ia;
	}
	public int[] getPosIA(){
		return pos;
	}
	public int[] getPositionBombes(){
		return positionBombes;
	}
	//Setteur
	public void setPosIA(int[] tab){
		pos=tab;
	}
	public int getX(){return xis;}
	
	public int getY(){return yec;}
	
	//Methode qui finit le jeu si l'un des joueurs meurt
	public void finirJeu(boolean aGagne){
		finJeu=true;
		stopTimer();
		stopTimerIA();
		timer.stop();
		if(aGagne){
			plateau=gagne;
		} else{
			plateau=perdu;
		}
		repaint();
	}
	
	//Methode qui nous dit si des bombes vient d'etre deposees
	public boolean bombeDeposee(){
		return bombeActive;
	}
	//Methode qui nous dit si des bombes de l'IA vient d'etre deposees
	public boolean bombeDeposeeIA(){
		return bombeActiveIA;
	}
	//On depose la bombe et on initialise un timer pour que la bombe "explose" dans 5 seconds	
	public void deposerBombe(){
		bombeActive=true;
		plateau[yec][xis]=2;
		positionBombes[0]=xis;
		positionBombes[1]=yec;
		t = new Timer(5000, new BombeEclat(this,false));
		t.start();
		repaint();
	}
	//On depose la bombe de l'IA et on initialise un timer pour que la bombe "explose" dans 5 seconds
	public void deposerBombeIA(int x, int y){
		bombeActiveIA=true;
		plateau[y][x]=2;
		positionBombes[2]=x;
		positionBombes[3]=y;
		tIA = new Timer(5000, new BombeEclat(this,true));
		tIA.start();
		repaint();
	}
	//Cette methode est execute par le Timer et garde la positon des bombes dans un tableau pour le redessiner
	public void next(){
		int x = positionBombes[0];
		int y = positionBombes[1];
		detruireCases(x,y);
		repaint();
	}
	//Idem que precedemment mais pour l'IA
	public void nextIA(){
		int x = positionBombes[2];
		int y = positionBombes[3];
		detruireCasesIA(x,y);
		repaint();
	}
	public void stopTimer(){
		t.stop();
	}
	public void stopTimerIA(){
		tIA.stop();
	}
	/**Methode pour detruire les cases lors de l'explosion de la bombe. Sa portee est d'une case.**/
	public void detruireCases(int x, int y){
		//On recupere la position de la bombe du joueur
		int x1 = positionBombes[0];
		int y1 = positionBombes[1];
		//On marque la case ou la bombe a ete deposee comme case brulante
		plateau[y1][x1]=4;
		//On initialise le timer qui va faire "l'explosion de la case"
		Timer t1 = new Timer(300, new Bruler(this));
		t1.start();
		if(plateau[y][x+1]!=0) plateau[y][x+1]=4;
		if(plateau[y][x-1]!=0) plateau[y][x-1]=4;
		if(plateau[y+1][x]!=0) plateau[y+1][x]=4;
		if(plateau[y-1][x]!=0) plateau[y-1][x]=4;
		bombeActive=false;
	}
	public void detruireCasesIA(int x, int y){
		//On recupere la position de la bombe de l'IA
		int x1 = positionBombes[2];
		int y1 = positionBombes[3];
		//On marque la case ou la bombe a ete deposee comme case brulante
		plateau[y1][x1]=4;
		Timer t1 = new Timer(300, new Bruler(this));
		t1.start();
		//On verifie si les cases autour sont destructibles et si oui on les marque en case brulante
		if(plateau[y][x+1]!=0) plateau[y][x+1]=4;
		if(plateau[y][x-1]!=0) plateau[y][x-1]=4;
		if(plateau[y+1][x]!=0) plateau[y+1][x]=4;
		if(plateau[y-1][x]!=0) plateau[y-1][x]=4;
		//On previent que il n'y a plus de bombes actives
		bombeActiveIA=false;
	}
	/**Metodes pour deplacer le personnage**/
	public void avancerUp(){
		int x = xis;
		int y = yec-1;
		if(y>=0 & plateau[y][x]>2){
			yec--;
			repaint();
		}
	}
	public void avancerDown(){
		int x = xis;
		int y = yec+1;
		if(y<plateau.length & plateau[y][x]>2){
			yec++;
			repaint();
		}
	}
	public void avancerLeft(){
		int x = xis-1;
		int y = yec;
		if(x>=0 & plateau[y][x]>2){
			xis--;
			repaint();
		}
	}
	public void avancerRight(){
		int x = xis+1;
		int y = yec;
	if(x<plateau.length & plateau[y][x]>2){
			xis++;
			repaint();
		}
	}
	/**Methode qui dessine les figures**/
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		
		//On va initialiser l'increment que c'est la largeur(incrementX) et longueur(incrementY) des cases
		incrementX = getWidth()/plateau[0].length;
		incrementY = getHeight()/plateau.length;
		
		for(int i=0;i<plateau.length;i++){
				
			for(int j=1;j<plateau[0].length;j++){
				//On dessine ici la case indestructible
				if(plateau[i][j]==0) g.setColor(Color.BLACK);
				//On dessine ici la case destructible
				else if(plateau[i][j]==1) g.setColor(new Color(191,191,191));
				//On dessine ici la case libre
				else if(plateau[i][j]==3) g.setColor(Color.WHITE);
				//On dessine la cases en feu
				else if(plateau[i][j]==4) g.setColor(Color.RED);
				//On se deplace suivant l'axe des x
				originX=j*incrementX;
				originY=i*incrementY;
				g.fillRect(originX, originY,incrementX, incrementY);
				//On dessine ici les bords des cases
				g.setColor(Color.WHITE);
				g.drawRect(originX, originY,incrementX, incrementY);
				//On se deplace suivant l'axe des y
				
				//On dessine ici les bombes
				if(plateau[i][j]==2){
					g.setColor(Color.RED);
					//On recupere la position des bombes
					int x1 = positionBombes[0];
					int y1 = positionBombes[1];
					int x2 = positionBombes[2];
					int y2 = positionBombes[3];
					//On dessine ici la bombre
					if (bombeActive) g.fillOval(x1*incrementX+10,y1*incrementY+5, incrementX/2,incrementY/2);
					if(bombeActiveIA) g.fillOval(x2*incrementX+10,y2*incrementY+5, incrementX/2,incrementY/2);
				}
			}
		}
		if(!finJeu){
			//On dessine ici le personnage
			g.setColor(Color.BLUE);
			int originXis = incrementX*xis;
			int originYec = incrementY*yec;
			g.fillOval(originXis, originYec, incrementX,incrementY);
			
			//On dessine ici l'intelligence artificiel
			g.setColor(Color.GREEN);
			int originIAX = incrementX*pos[0];
			int originIAY = incrementY*pos[1];
			g.fillOval(originIAX, originIAY, incrementX,incrementY);
		}
		//montrerTableau();
	}
}
