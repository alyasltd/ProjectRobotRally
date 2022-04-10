package application.classes;

import java.io.IOException;
import java.net.Socket;

public class GameMaster
{
    public Players getPlayers()
    {
        return players;
    }

    public boolean isCaseOccupied(int x, int y)
    {
        for(Player player : players.getPlayers())
        {
            if(player.getPosition().getX() == x && player.getPosition().getY() == y)
                return true;
        }

        return false;
    }

    private Players players = null;
    public static GameMaster instance = null;

    public Script getScript()
    {
        return script;
    }

    Script script = new Script();

    public GameSituation getGameSituation()
    {
        return gameSituation;
    }

    GameSituation gameSituation = new GameSituation();
    //=======================
    //GESTION CLIENTS
    //=======================
    public void removePlayer(Player player)
    {
        players.remove(player);
    }

    GameMaster()
    {
        players = new Players();
    }
    public static void init()
    {
        GameMaster gameMaster = new GameMaster();
        instance = gameMaster;
    }

    public void addPlayer(Socket socket)
    {
        Player player = new Player(players.getUnusedId(), socket);
        players.add(player);

        if(players.count() == 1)
        {
            player.getPosition().setPosition(0,0);
        }
        else if(players.count() == 2)
        {
            player.getPosition().setPosition(26,0);
        }
        else if(players.count() == 3)
        {
            player.getPosition().setPosition(0,11);
        }
        else if(players.count() == 4)
        {
            player.getPosition().setPosition(26,11);
        }
    }

    public void sendRefreshBoardToPlayers() throws IOException
    {
        for(Player p : players.getPlayers())
        {
            p.getThread().getOos().reset();
            p.getThread().getOos().writeObject(players);
            p.getThread().getOos().flush();
        }
    }

    public void sendGameSituation() throws IOException
    {
        for(Player p : players.getPlayers())
        {
            p.getThread().getOos().reset();
            p.getThread().getOos().writeObject(gameSituation);
            p.getThread().getOos().flush();
        }
    }


    public void sendCardsToPlayers() throws IOException
    {
        for(Player p : players.getPlayers())
        {
            Cards cards = new Cards();
//            if(p.getDamagePoints()>=5) {
//                cards.generateCards(5 - (10 - p.getDamagePoints()));
//            }
//            else {
//                cards.generateCards(9);
//            }
            cards.generateCards(9);
            p.setCards(cards);
            p.getThread().getOos().reset();
            p.getThread().getOos().writeObject(p);
            p.getThread().getOos().flush();
        }
    }

    public void clearChosenCardsForAllPlayers() throws IOException
    {
        for(Player p : players.getPlayers())
        {
            p.getChoosenCards().clear();
        }
    }

    public boolean allPlayerChooseCards() throws IOException
    {
        if(players.getPlayers().isEmpty())
            return false;

        for(Player p : players.getPlayers())
        {
            if(p.getChoosenCards().getCardsList().size() != 5)
                return false;
        }

        return true;
    }


    public boolean canConnect()
    {
        return players.count() < 4 && ! gameSituation.isStarted();

    }

    //=============================
    //COMMUNICATION CLIENTS
    //=============================
    /**
     * Envoi une notification à tous les clients connectés
     */
    void notification(String notif)
    {
        for(Player player : players.getPlayers()) {
            player.getThread().print(notif);
        }
    }

}
