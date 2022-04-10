package application.classes;

import application.enums.OrientationJoueur;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.Serializable;


public class Player<ClientThread> implements Serializable{


	//constructeur
	public Player(int id) {
		this.id = id;
		this.position=new Position(0,0);
	}

	private static final long serialVersionUID = 1L;
	private int id;
	private int healthPoints=3;
	private int damagePoints=0;

	private String playerName;
	private Position position;
	private ClientThread clientThread;



	private OrientationJoueur playerDirection; //pour determiner selon où le joueur est dans la map au départ.
	private Cards mesCartes;
	private ImageView playerImage;
	private int nbCheckPointObtenus;

	Position positionCheckPoint = new Position(0,0);
	private boolean isDead;
	private Cards cards=new Cards();


	// setters et getters
	public Cards getCards() {
		return cards;
	}

	public void setCards(Cards c){
		this.cards=c;
	}


	public OrientationJoueur getPlayerDirection() {
		return playerDirection;
	}
	public void setHealthPoints(int healthPoints)
	{
		this.healthPoints = healthPoints;
	}

	public void setDamagePoints(int damagePoints)
	{
		this.damagePoints = damagePoints;
	}


	public int getId()
	{
		return id;
	}

	public int getHealthPoints()
	{
		return healthPoints;
	}

	public int getDamagePoints()
	{
		return damagePoints;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public Position getPosition()
	{
		return position;
	}

	private  void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		this.id = ois.readInt() ;
		this.position = (Position) ois.readObject() ;
		this.healthPoints = ois.readInt() ;
		this.damagePoints = ois.readInt() ;
		this.playerName = (String) ois.readObject() ;
		this.cards = (Cards) ois.readObject();

	}

	// méthode writeObject, utilisée lors de la sérialization
	private  void writeObject(ObjectOutputStream oos) throws IOException {

		oos.writeInt(id);
		oos.writeObject(position);
		oos.writeInt(healthPoints);
		oos.writeInt(damagePoints);
		oos.writeObject(playerName);
		oos.writeObject(cards);
	}

	@Override
	public String toString() {
		return "Player"+"\n"+
				"playerName=" + playerName +"\n"+
				"healthPoints=" + healthPoints +"\n"+
				"damagePoints=" + damagePoints+"\n"+
				"playerDirection="+ playerDirection +"\n"+
				"position=" + position +"\n"+
				"Cartes ="+ mesCartes.toString()+"\n"+
				"Nombre de checkPoint ="+ nbCheckPointObtenus;
	}



}



