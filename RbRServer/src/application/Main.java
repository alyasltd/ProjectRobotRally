package application;

import application.classes.ClientThread;
import application.classes.GameMaster;
import application.classes.Script;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{

    public static void main(String[] args) {

        GameMaster.init();
        GameMaster.instance.getScript().start();

        try (ServerSocket serversocket = new ServerSocket(2566)){
            System.out.println("Server ready.");
            while(true) {

                Socket socket = serversocket.accept();
                if( ! GameMaster.instance.canConnect())
                {
                    PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
                    output.println("/task quit");
                    socket.close();
                    continue;
                }
                GameMaster.instance.addPlayer(socket);
            }
        } catch (Exception e) {
            System.out.println("Error occured in main: " + e.getStackTrace());
        }
    }
}