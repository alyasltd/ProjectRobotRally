package application.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Players implements Serializable
{

	private static final long serialVersionUID = 1L;

	//members
	private List<Player> players = new ArrayList<Player>();

	//functions
	public Player getPlayer(int id)
	{
		for (Player player: players) {
			if(player.getId() == id)
				return player;
		}

		return null;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public int count() {
		return players.size();
	}
	public void setListPlayer(List<Player> listPlayer) {
		this.players = listPlayer;
	}
	public void add(Player e ) {
		players.add(e);
	}
	public void remove(Player e) {
		players.remove(e);
	}
	public Players() {
		players = new ArrayList<Player>();
	}


	private  void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
	{
		this.players = (List<Player>) ois.readObject() ;
	}

	// méthode writeObject, utilisée lors de la sérialization
	private  void writeObject(ObjectOutputStream oos) throws IOException
	{
		oos.writeObject(players);
	}
	
	
}
