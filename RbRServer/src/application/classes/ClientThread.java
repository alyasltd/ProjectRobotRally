package application.classes;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class ClientThread extends Thread {
    private Socket socket;
    private PrintWriter output;
    private Player player;

    public ObjectOutputStream getOos()
    {
        return oos;
    }

    ObjectOutputStream  oos = null;

    public ObjectInputStream getOis()
    {
        return ois;
    }

    ObjectInputStream ois = null;

    public ClientThread(Player player, Socket socket) {
        this.socket = socket;
        this.player = player;
        this.setDaemon(true);
    }

    public void print(String text)
    {
        output.println(text);
    }

    public void sendRefreshPlayer() throws IOException
    {
        oos.reset();
        oos.writeObject(player);
        oos.flush();
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(),true);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            oos.reset();
            oos.writeObject(new Task("playerId", String.valueOf(player.getId())));
            oos.flush();
            GameMaster.instance.sendRefreshBoardToPlayers();
            oos.reset();
            oos.writeObject(player);
            oos.flush();

            while(true) {
                Object object;
                try {
                    object = (Object)ois.readObject();
                } catch (Exception e) {
                    System.out.println("Break client");
                    break;
                }

                if(object instanceof Task)
                {
                    Task task = (Task)object;
                    System.out.println(String.valueOf("TASK -> Player " + player.getId()) + ": " + task.taskName + " " + task.message);

                    if(task.message.equalsIgnoreCase("TestCards"))
                    {
                        GameMaster.instance.sendCardsToPlayers();
                    }
                    if(task.message.equalsIgnoreCase("Start"))
                    {
                        GameMaster.instance.getGameSituation().setStarted(true);
                    }
                }
                if(object instanceof Cards)
                {
                    Cards cards = (Cards)object;
                    System.out.println(String.valueOf("CHOOSEN CARDS -> Player " + cards.toString()));

                    player.setChoosenCards(cards);

                    System.out.println(GameMaster.instance.getPlayers().count());
                }

            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Client disconnected: " + socket.getInetAddress().getHostAddress() + " | id: " + player.getId());
            GameMaster.instance.removePlayer(player);
        }
        GameMaster.instance.removePlayer(player);
    }
}
