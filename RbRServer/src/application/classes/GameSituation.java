package application.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameSituation implements Serializable
{
    private static final long serialVersionUID = 1L;

    public boolean isStarted()
    {
        return isStarted;
    }

    public boolean isChoosingCards()
    {
        return isChoosingCards;
    }

    public boolean isMoving()
    {
        return isMoving;
    }

    public void setStarted(boolean started) throws IOException
    {
        isStarted = started;
        GameMaster.instance.sendRefreshBoardToPlayers();
        GameMaster.instance.sendGameSituation();
    }

    public void setChoosingCards(boolean choosingCards)
    {
        isChoosingCards = choosingCards;
    }

    public void setMoving(boolean moving)
    {
        isMoving = moving;
    }

    boolean isStarted = false;
    boolean isChoosingCards = false;
    boolean isMoving = false;

    private  void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.isStarted = ois.readBoolean();
        this.isChoosingCards = ois.readBoolean();
        this.isMoving = ois.readBoolean();
    }

    // méthode writeObject, utilisée lors de la sérialization
    private  void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeBoolean(isStarted);
        oos.writeBoolean(isChoosingCards);
        oos.writeBoolean(isMoving);

    }
}
