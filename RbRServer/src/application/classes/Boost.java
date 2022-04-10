package application.classes;

public class Boost extends Case{
	
	public Boost(Position position) {
		super(position);
	}
	
	@Override
	public void impactCase(Player player) {
		player.avance(1);
	}
	
}
