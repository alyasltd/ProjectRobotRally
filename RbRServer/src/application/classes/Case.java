package application.classes;

import java.io.Serializable;

public abstract class Case implements Serializable{

	
	private static final long serialVersionUID = 1L;


	//constructeur 
    public Case(Position position) {
        this.position = position;
        this.isOccupied = false;
    }
    
    
    
    //attribut
    private Position position;
    private boolean isOccupied;
    
    
    
    
    // Setters / Getters
    public Position getPostition() {
        return position;
    }

    public void setPostition(Position postition) {
        this.position = postition;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    
 
	@Override
	public String toString() {
		return "[position=" + position.toString() + ", isOccupied=" + isOccupied + "]";
	}
    
    
    // m�thode abstract 
	
	public abstract void impactCase(Player player);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
