package application.classes;

public class Outils extends Case{

	// Constructeur 
	public Outils(Position position, Cle cle) {
		super(position);
		setCle(Cle.simple);
	}

	
	
	//attributs
	private Cle cle; 	// une case outil pos�de une cl� - unique ou double - qui va r�parer un ou deux point de d�gat
	
	// getters et setters 
	public Cle getCle() {
		return cle;
	}

	public void setCle(Cle cle) {
		this.cle = cle;
	}
	
	
	
	//fonction 
	public void impactCase(Player player) {
		
		if(this.cle == Cle.simple ) {
			player.perdDamagePoint(1);
		}
		
		else if(this.cle == Cle.Double ) {
			player.perdDamagePoint(2);
		}
	}

	@Override
	public String toString() {
		return "Outils" + super.toString() + "[cle=" + cle + "] ]";
	}
	
	
	
	
	
	
	
}
