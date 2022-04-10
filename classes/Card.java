package application.classes;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Card implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// Constructeur 
	public Card(){
	}

	// attributs
	private Direction direction;
	private int puissance;


	//setter et getter	
	public Direction getDirection() {
		return direction;
	}
	
	public int getPuissance() {
		return puissance;
	}



	//Fonctions


	public String getImage(){
		String direction;
		String puissance= String.valueOf(this.puissance);


		if ( this.direction ==Direction.haut)
			direction = "U";


		else if ( this.direction ==Direction.bas)
			direction = "D";

		else if ( this.direction ==Direction.droite)
			direction = "R";

		else
			direction = "L";

		//new javafx.scene.image.Image(getClass().getResourceAsStream("../images/Cards/C_"+direction+"_"+puissance+".png"));

		return "../images/Cards/C_"+direction+"_"+puissance+".png";
	}

	@Override
	public String toString() {
		return "Card [mouvement=" + direction + ", nbMouvementCase=" + puissance + "]";
	}

	private  void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		this.puissance = ois.readInt();
		this.direction = (Direction) ois.readObject();
	}

	// méthode writeObject, utilisée lors de la sérialization
	private  void writeObject(ObjectOutputStream oos) throws IOException {
		oos.writeInt(puissance);
		oos.writeObject(direction);
	}

	
	
	
	
	
	
	
	
	
}
