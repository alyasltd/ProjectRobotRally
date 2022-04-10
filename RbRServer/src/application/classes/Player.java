package application.classes;

import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;


public class Player implements Serializable{

	//constructeur
	public Player(int id, Socket socket) {
		this.id = id;
		this.position=new Position(0,0);
		thread = new ClientThread(this, socket);
		thread.start();

		System.out.println("New client connected: " + socket.getInetAddress().getHostAddress() + " | id: " + id);
	}

	//attributs
	private static final long serialVersionUID = 1L;
	private int id;
	private int healthPoints=3;
	private Cards cards = new Cards();
	private int damagePoints=0;
	private String playerName;
	private Position position;
	private Cards choosenCards=new Cards();
	private OrientationJoueur playerDirection;

	private int nbCheckPointObtenus;
	Position positionCheckPoint = new Position(0,0);
	private Case dernierCheckPoint;
	private boolean isDead;


	//getters et setters

	public Cards getCards() {
		return cards;
	}
	public void setCards(Cards c){
		this.cards=c;
	}
	public ClientThread getThread()
	{
		return thread;
	}
	private transient ClientThread thread = null;
	public void setHealthPoints(int healthPoints)
	{
		this.healthPoints = healthPoints;
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
	public Cards getChoosenCards()
	{
		return choosenCards;
	}
	public void setChoosenCards(Cards choosenCards)
	{
		this.choosenCards = choosenCards;
	}
	public void setDamagePoints(int damagePoints)
	{
		this.damagePoints = damagePoints;
	}

	public int getNbCheckPointObtenus() {
		return nbCheckPointObtenus;
	}

	public void setNbCheckPointObtenus(int nbCheckPointObtenus) {
		this.nbCheckPointObtenus = nbCheckPointObtenus;
	}

	public OrientationJoueur getPlayerDirection() {
		return playerDirection;
	}
	public void setPosition(Position position) {
		this.position=position;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setPlayerDirection(OrientationJoueur playerDirection) {
		this.playerDirection = playerDirection;
	}




	// Nos fonctions de mouvement.
	public void recule(int i) {
		if (this.getPlayerDirection() == OrientationJoueur.droite) {
			this.getPosition().goLeft(i);

		}

		if (playerDirection == OrientationJoueur.gauche) {
			this.getPosition().goRigth(i);

		}

		if (playerDirection == OrientationJoueur.haut) {
			this.getPosition().goDown(i);

		}

		if (playerDirection == OrientationJoueur.bas) {
			this.getPosition().goUp(i);

		}
	}

	public void avance(int i) {
		if (playerDirection == OrientationJoueur.droite) {
			this.getPosition().goRigth(i);
		}

		if (playerDirection == OrientationJoueur.gauche) {
			this.getPosition().goLeft(i);

		}

		if (playerDirection == OrientationJoueur.haut) {
			this.getPosition().goUp(i);

		}

		if (playerDirection == OrientationJoueur.bas) {
			this.getPosition().goDown(i);

		}

	}

	public void seDecaleDroite(int i) {

		if (playerDirection == OrientationJoueur.droite) {
			this.getPosition().goDown(i);

		}

		if (playerDirection == OrientationJoueur.gauche) {
			this.getPosition().goUp(i);

		}

		if (playerDirection == OrientationJoueur.haut) {
			this.getPosition().goRigth(i);

		}

		if (playerDirection == OrientationJoueur.bas) {
			this.getPosition().goLeft(i);

		}

	}

	public void seDecaleGauche(int i) {

		if (playerDirection == OrientationJoueur.droite) {
			this.getPosition().goUp(i);

		}

		if (playerDirection == OrientationJoueur.gauche) {
			this.getPosition().goDown(i);

		}

		if (playerDirection == OrientationJoueur.haut) {
			this.getPosition().goLeft(i);

		}

		if (playerDirection == OrientationJoueur.bas) {
			this.getPosition().goRigth(i);

		}

	}

	// fonction de jeu
	public void perdDamagePoint(int i) {
		setDamagePoints(getDamagePoints()- i );
	}

	public void damagePointsIncrement()
	{
		if(damagePoints + 1 == 10) {
			damagePoints = 0;
			healthPoints--;
			return;
		}
		this.damagePoints++;
	}

	public void gainHP(int i ) {
		this.setHealthPoints(getHealthPoints()+i);
	}

	public void gainDamagePoint(int i ) {
		this.setDamagePoints(getDamagePoints()+i);
	}

	public void perdHP(int i ) {
		this.setHealthPoints(getHealthPoints()-i);
	}

	public void gagneUnCheckPoint(int i) {
		this.setNbCheckPointObtenus(this.getNbCheckPointObtenus()+1);
	}




	public void estSurUneCase(Plateau monPlateau) {
		for(Case cetteCase : monPlateau.getCaseList()){
			if ( this.position == cetteCase.getPostition()) {
				cetteCase.impactCase(this);
				cetteCase.setOccupied(true);

				if ( cetteCase instanceof CheckPoint) {
					((CheckPoint) cetteCase).renvoieCase(this);
				}
			}

		}
	}

	//sérialisation

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


			
}



