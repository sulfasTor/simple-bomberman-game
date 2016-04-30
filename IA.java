/*
 * Classe de l'Intelligence Artificielle
 * 
 * 
 * */
public class IA {
	
	private BMPlateau bm; //On recupere le plateau 
	private int[][] plateau; //On le garde ici
	private int x; //position actuel en x
	private int y; //position actuel en y
	private int[] positions; //tableau qu'on va envoyer 
	private static int repetionsUP=0;
	private static int repetionsDOWN=0;
	private static int repetionsRIGHT=0;
	private static int repetionsLEFT=0;
	
	public IA(BMPlateau bm){
		this.bm = bm;
		positions=new int[2]; //On 
		plateau=bm.getPlateau();
		x=plateau[0].length-2;
		y=plateau.length-2;
		positions[0]=x;
		positions[1]=y;
	}
	public int[] jouer(){
		
		//IA
		if(!murDerriere()&repetionsDOWN<=1) avancerDerriere();
		else if(!murDevant()&repetionsUP<=2) avancerDevant();
		else if(!murDroite()&repetionsRIGHT<=1) avancerDroite();
		else if(!murGauche()&repetionsLEFT<=2) avancerGauche();
		
		else if(murDerriere()&murDevant()&murGauche()&&murDroite()) System.out.println("l'ia est coince");
		else if(murDerriere()&murDevant()&murGauche()&&!murDroite()) avancerDroite();
		else if(murDerriere()&murDevant()&!murGauche()&&murDroite()) avancerGauche();
		else if(murDerriere()&!murDevant()&murGauche()&&murDroite()) avancerDevant();
		else if(!murDerriere()&murDevant()&murGauche()&&murDroite()) avancerDerriere();
		else{
			repetionsDOWN=0;
			repetionsUP=0;
			repetionsRIGHT=0;
			repetionsLEFT=0;
		}
		
		positions[0] = x;
		positions[1] = y;
		deposerBombe();
		bm.repaint();
		return positions;
	}
	public void avancerDevant(){
		if(y>=0  && x>=0){
			y--;
			repetionsUP++;
		}
	}
	public void avancerDerriere(){
		if(x<plateau[0].length  && y<plateau.length){
			y++;
			repetionsDOWN++;
		}
	}
	public void avancerGauche(){
		if(x>=0  && y>-0){
			x--;
			repetionsLEFT++;
		}
	}
	public void avancerDroite(){
		if(y<plateau.length  && x<plateau[0].length){
			x++;
			repetionsRIGHT++;
		}
	}
	public boolean murDevant(){
		boolean mur=false;
		if(plateau[y-1][x]!=3) {
			mur= true;
		}
		return mur;
	}
	public boolean murDerriere(){
		boolean mur=false;
		if(plateau[y+1][x]!=3) {
			mur= true;
		}
		return mur;
	}
	public boolean murDroite(){
		boolean mur=false;
		if(plateau[y][x+1]!=3) {
			mur= true;
		}
		return mur;
	}
	public boolean murGauche(){
		boolean mur=false;
		if(plateau[y][x-1]!=3) {
			mur= true;
		}
		return mur;
	}

	public void deposerBombe(){
		if(plateau[y][x+1]==1) {
			bm.deposerBombeIA(x,y);
		}
		else if(plateau[y][x-1]==1){
			bm.deposerBombeIA(x,y);
		}
		else if(plateau[y+1][x]==1){
			bm.deposerBombeIA(x,y);
		}
		else if(plateau[y-1][x]==1) {
			bm.deposerBombeIA(x,y);
		}
	}
}

