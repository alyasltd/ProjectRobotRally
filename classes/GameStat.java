package application.classes;

import com.sun.security.ntlm.Server;
import javafx.application.Platform;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class GameStat
{
    public static GameStat instance;
    private Players players = new Players();

    //Current client
    private ServerThread serverThread = null;
    private int clientPlayerId = -1;

    public GameSituation getGameSituation()
    {
        return gameSituation;
    }

    public void setGameSituation(GameSituation gameSituation)
    {
        this.gameSituation = gameSituation;
    }

    private GameSituation gameSituation = new GameSituation();


    public void setCurrentClientPlayerId(int clientPlayerId)
    {
        this.clientPlayerId = clientPlayerId;
    }



    public static void init()
    {
        GameStat gameStat = new GameStat();
        instance = gameStat;
    }


    public boolean isUsable()
    {
        boolean usable = true;
        if(clientPlayerId == -1)
            usable = false;

        return usable;
    }
    public void refreshFromServer()
    {
        // ...
    }

    public Players players()
    {
        return players;
    }

    public Player getCurrentClientPlayer()
    {
        return players.getPlayer(clientPlayerId);

    }

    public ServerThread getserverThread()
    {
        return serverThread;
    }

    public void setServerThread(Socket socket) throws IOException
    {
        ServerThread server = new ServerThread(socket);
        Thread t = new Thread(server);
        t.setDaemon(true);
        t.start();


        this.serverThread = server;
    }
}
