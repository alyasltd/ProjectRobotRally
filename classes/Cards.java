package application.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Cards implements Serializable {

	private static final long serialVersionUID = 1L;


	public Cards() {
	}
	
	private List<Card> cardsList = new ArrayList<Card>();
	public List<Card> getCardsList() {
		return cardsList;
	}

	public void add(Card card)
	{
		cardsList.add(card);
	}

	public void clear()
	{
		cardsList.clear();
	}

	public int size()
	{
		return cardsList.size();
	}

		
	public String toString() {
		String mess = "";
		mess+= 
				"Mes Cartes:"+ "\n\n";
			
		Iterator <Card> i = cardsList.iterator();

		while(i.hasNext()) {
			Card e = i.next();
			mess+= e.toString()+ "\n";
			}

		return mess;
	}

	private  void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		this.cardsList=(List<Card>) ois.readObject();
	}

	private  void writeObject(ObjectOutputStream oos) throws IOException {
		oos.writeObject(cardsList);
	}
	
	

}
