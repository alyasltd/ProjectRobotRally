package application.classes;

public class DoubleBoost extends Case{

	public DoubleBoost(Position position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void impactCase(Player player) {
		player.avance(2);
		
	}

	
	
}
