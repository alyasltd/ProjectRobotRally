package application.classes;

public class CheckPoint extends Case {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public CheckPoint(Position position) {
		super(position);
	}

	
	// M�thode Checkpoint
	public void impactCase (Player player) {
		if (player.getPosition()==this.getPostition()) {
			player.gagneUnCheckPoint(1);;
			
		}

	}
	
	public void renvoieCase(Player player) {
		if (player.getPosition()==this.getPostition() && player.isDead()==true) {
			player.setPosition(this.getPostition());
		}
	}
}