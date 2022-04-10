package application.classes;

public class BlackHole extends Case{
	
	
	private static final long serialVersionUID = 1L;

	public BlackHole(Position position) {
		super(position);
	}
	
	Players players;
	
	// M�thode le personnage est mort
	public void impactCase(Player player) {
		player.setHealthPoints(0);
		players.remove(player);
		//player.isDead()=true;
	}
}

