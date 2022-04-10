package application.classes;

import application.controllers.CtrlBoard;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket socket;
    private BufferedReader input;

    public ObjectInputStream getOis()
    {
        return ois;
    }

    private ObjectInputStream ois;

    public ObjectOutputStream getOos()
    {
        return oos;
    }

    private ObjectOutputStream oos;

    public ServerThread(Socket s) throws IOException {
        this.socket = s;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }
    @Override
    public void run() {
        while(true) {
            Object object;
            try {
                object = (Object)ois.readObject(); // Deserialization function
            } catch (Exception e) {
                break;
            }


            if(object instanceof Task)
            {
                Task task = (Task)object;
                System.out.println(String.valueOf("TASK ->  " + ": " + task.taskName + " " + task.message)) ;
                if(task.taskName.equalsIgnoreCase("playerId"))
                {
                    GameStat.instance.setCurrentClientPlayerId(Integer.parseInt(task.message));

                }
            }

            else if(object instanceof Player)
            {
                Player player = (Player)object;
                GameStat.instance.setCurrentClientPlayerId(player.getId());
                System.out.println(player.getId());

                if(GameStat.instance.getCurrentClientPlayer() != null) {
                    Player cplayer = GameStat.instance.getCurrentClientPlayer();

                    cplayer.setDamagePoints(player.getDamagePoints());
                    cplayer.setHealthPoints(player.getHealthPoints());
                    cplayer.setCards(player.getCards());

                }
                Platform.runLater(()->
                {
                    CtrlBoard.instance.refreshLabelsAndButtons();
                    CtrlBoard.instance.refreshCards();
                });
                continue;
            }
            else if(object instanceof GameSituation)
            {
                GameSituation gs = (GameSituation)object;
                GameStat.instance.setGameSituation(gs);
                Platform.runLater(()->
                {
                    CtrlBoard.instance.refreshLabelsAndButtons();
                });

                continue;
            }
            else if(object instanceof Players)
            {
                System.out.println("Players refresh");
                Players players = (Players)object;
                GameStat.instance.players().setListPlayer(players.getPlayers());
                Platform.runLater(()->
                {
                    CtrlBoard.instance.refreshBoard();
                });
            }
        }

        System.out.println("Out of while(true)");


    }
    
}
