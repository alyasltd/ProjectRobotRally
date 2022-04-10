package application.classes;



public class Spirale extends Case{

	
	private static final long serialVersionUID = 1L;

	public Spirale(Position position) {
		super(position);
		
	}
	
	

	@Override
	public void impactCase(Player player) {
		double nb = Math.random();
		
		if ( nb < 0.25) {
			if ( player.getPlayerDirection()!= OrientationJoueur.droite) {
				player.setPlayerDirection(OrientationJoueur.droite);
			}
			else {
				impactCase(player);
			}
		}

		else if ( nb < 0.50) {
			if ( player.getPlayerDirection()!= OrientationJoueur.gauche) {
				player.setPlayerDirection(OrientationJoueur.gauche);
			}
			else {
				impactCase(player);
			}
		}

		else if ( nb < 0.75) {
			if ( player.getPlayerDirection()!= OrientationJoueur.haut) {
				player.setPlayerDirection(OrientationJoueur.haut);
			}
			else {
				impactCase(player);
			}
		}

		else if ( nb < 0.99) {
			if ( player.getPlayerDirection()!= OrientationJoueur.bas) {
				player.setPlayerDirection(OrientationJoueur.bas);
			}
			else {
				impactCase(player);
			}
		}
		
	}



	@Override
	public String toString() {
		return "Spirale [" + super.toString() + "]";
	}


	
	
	
	
	
	
	
	
}
