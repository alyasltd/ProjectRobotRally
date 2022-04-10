package application.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Position implements Serializable
{
	private static final long serialVersionUID = 1L;
		//constructeur 
		Position(int x, int y ){
			this.setX(x); // abcisses
			this.setY(y); // ordonnes
		}
		
		//setters getters
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public void setPosition(int x, int y) {
			this.x=x;
			this.y=y;
		}

		// attributs 
		private int x=0; 
		private int y=0;
		
		
		//fonctions 
		public void goUp(int i) {
		y=y+i;	
		}
		
		public void goDown(int i) {
		y=y-i;	
		}
		
		public void goLeft(int i) {
		x=x-i;
		}
		
		public void goRigth(int i) {
		x=x+i;	
		}
		
		@Override
		public String toString() {
			return "Position [x=" + x + ", y=" + y + "]";
		}

	private  void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		this.x = ois.readInt() ;
		this.y = ois.readInt() ;
	}

	// méthode writeObject, utilisée lors de la sérialization
	private  void writeObject(ObjectOutputStream oos) throws IOException {
		oos.writeInt(x); ;
		oos.writeInt(y) ;
	}

}
