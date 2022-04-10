package application.classes;



public class Mur extends Case {

	
	private static final long serialVersionUID = 1L;
	private OrientationJoueur orientation;

	public Mur(Position position, OrientationJoueur orientation) {
		super(position);
		this.orientation=orientation;
		
	}
	
	public OrientationJoueur getOrientation() {
		return orientation;
	}

	public void setOrientation(OrientationJoueur orientation) {
		this.orientation = orientation;
	}
	@Override

	public void impactCase(Player player) {
		if(this.orientation==player.getPlayerDirection()) {
			player.avance(0);
		}
	}

	

}
