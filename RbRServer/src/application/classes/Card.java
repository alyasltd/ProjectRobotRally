package application.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Card implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// Constructeur 
	public Card(){
		aleaDirection();
		aleaPuissance();
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
	//alea -- elle permet de generer aleatoirement un objet Card automatiquement
	public Card aleaDirection() {
		double nb = Math.random();
		
		if(nb<=0.25) {
			direction = Direction.haut;
		}
		
		else if (nb<= 0.50) {
			direction = Direction.bas;
		}
		
		else if (nb<= 0.75) {
			direction = Direction.droite;
		}
		
		else if (nb<= 0.99) {
			direction = Direction.gauche;
		}
		
		return this;
	}
	
	public Card aleaPuissance() {
		double nb = Math.random();
		
		if(nb<=0.50)
			puissance = 1;
		else if (nb<= 0.80)
			puissance =2;
		else if (nb< 1)
			puissance =3;

		return this;
	}


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
