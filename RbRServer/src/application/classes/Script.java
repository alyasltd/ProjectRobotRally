package application.classes;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import java.io.*;
import java.net.Socket;

public class Script extends Thread
{

    public Script() {
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            System.out.println("Script exec");

            if( ! GameMaster.instance.getGameSituation().isStarted())
            {
                try {
                    GameMaster.instance.sendRefreshBoardToPlayers();
                    GameMaster.instance.sendGameSituation();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                continue;
            }
            //Script

            if( ! GameMaster.instance.getGameSituation().isMoving() && ! GameMaster.instance.getGameSituation().isChoosingCards())
            {
                GameMaster.instance.getGameSituation().setChoosingCards(true);
                try {
                    GameMaster.instance.sendCardsToPlayers();
                    GameMaster.instance.sendGameSituation();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(GameMaster.instance.getPlayers().count());
                continue;
            }
            else if(GameMaster.instance.getGameSituation().isChoosingCards())
            {
                try {
                    if(GameMaster.instance.allPlayerChooseCards())
                    {
                        System.out.println(GameMaster.instance.getPlayers().count());
                        GameMaster.instance.gameSituation.setChoosingCards(false);
                        GameMaster.instance.gameSituation.setMoving(true);
                        GameMaster.instance.sendGameSituation();
                        System.out.println(GameMaster.instance.getPlayers().count());
                        for(int moveNb = 0; moveNb < 5; moveNb++) {
                            for (Player p : GameMaster.instance.getPlayers().getPlayers()) {
                                Card card = p.getChoosenCards().getCardsList().get(moveNb);
                                System.out.println(GameMaster.instance.getPlayers().count());

                                if(card.getDirection() == Direction.droite) {
                                    if(p.getPosition().cangoRight())
                                        p.getPosition().goRigth(card.getPuissance());
                                }

                                else if(card.getDirection() == Direction.gauche) {
                                    if(p.getPosition().canGoLeft())
                                        p.getPosition().goLeft(card.getPuissance());
                                }

                                else if(card.getDirection() == Direction.bas) {
                                    if(p.getPosition().canGoDown())
                                        p.getPosition().goDown(card.getPuissance());
                                }

                                else if(card.getDirection() == Direction.haut) {
                                    if(p.getPosition().cangoUp())
                                        p.getPosition().goUp(card.getPuissance());
                                }

                                Thread.sleep(800);
                                GameMaster.instance.sendRefreshBoardToPlayers();
                            }
                        }

                        GameMaster.instance.clearChosenCardsForAllPlayers();
                        GameMaster.instance.gameSituation.setChoosingCards(true);
                        GameMaster.instance.gameSituation.setMoving(false);
                        GameMaster.instance.sendGameSituation();
                        GameMaster.instance.sendCardsToPlayers();

                    }
                    continue;
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}

