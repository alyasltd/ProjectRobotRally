package application.classes;

public class Laser extends Case{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cle cle;

	public Laser(Position position,Cle cle) {
		super(position);
		this.setCle(cle);
	
	}

	public Cle getCle() {
		return cle;
	}


	public void setCle(Cle cle) {
		this.cle = cle;
	}
	
	// faire une clé simple double ou triple, 123 pts de d�gats
	@Override
	public void impactCase(Player player) {
		if (this.cle==Cle.simple) {
			player.gainDamagePoint(1);
		}
		if (this.cle==Cle.Double) {
			player.gainDamagePoint(2);
		}
		
	}


	
	
	
	

}
