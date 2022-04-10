package application.controllers;

import application.classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CtrlBoard implements Initializable
{
    @FXML
    private GridPane gpBoard;

    @FXML
    private HBox hBoxCards;

    @FXML
    private Button btnStartGame;

    @FXML
    private HBox hBoxDp;

    @FXML
    private Label lblHp;

    @FXML
    private Label lblDp;

    @FXML
    private Label lblGameSituation;

    @FXML
    private ImageView imgMe;

    @FXML
    private ImageView imCard0;

    @FXML
    private ImageView imCard1;

    @FXML
    private ImageView imCard2;

    @FXML
    private ImageView imCard3;

    @FXML
    private ImageView imCard4;

    @FXML
    private ImageView imCard5;

    @FXML
    private ImageView imCard6;

    @FXML
    private ImageView imCard7;

    @FXML
    private ImageView imCard8;

    private List<ImageView> playersImage = new ArrayList<ImageView>();
    private List<ImageView> cardsImage = new ArrayList<ImageView>();

    public static CtrlBoard instance = null;

    private Cards choosenCards = new Cards();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println("Initialize board");
        instance = this;
        cardsImage.add(imCard0);
        cardsImage.add(imCard1);
        cardsImage.add(imCard2);
        cardsImage.add(imCard3);
        cardsImage.add(imCard4);
        cardsImage.add(imCard5);
        cardsImage.add(imCard6);
        cardsImage.add(imCard7);
        cardsImage.add(imCard8);
        initCases();
        refreshBoard();

    }

    @FXML
    void btnRightClick(ActionEvent event) throws IOException
    {
        GameStat.instance.getserverThread().getOos().reset();
        GameStat.instance.getserverThread().getOos().writeObject(new Task("Cards", "TestCards"));
        GameStat.instance.getserverThread().getOos().flush();
        //DropShadow ds = new DropShadow( 20, Color.DARKRED);
        //imCard0.setEffect(ds);

    }

    @FXML
    void btnStartClick(ActionEvent event) throws IOException
    {
        GameStat.instance.getserverThread().getOos().reset();
        GameStat.instance.getserverThread().getOos().writeObject(new Task("Gs", "Start"));
        GameStat.instance.getserverThread().getOos().flush();
    }

    private void imgClicked(ImageView imageView) throws IOException
    {
        if(choosenCards.size() < 5)
        {
            if(choosenCards.getCardsList().contains(GameStat.instance.getCurrentClientPlayer().getCards().getCardsList().get(cardsImage.indexOf(imageView))))
                return;

            DropShadow ds = new DropShadow(40, Color.DARKRED);
            imageView.setEffect(ds);
            choosenCards.add(GameStat.instance.getCurrentClientPlayer().getCards().getCardsList().get(cardsImage.indexOf(imageView)));

            if(choosenCards.size() == 5)
            {
                System.out.println("a");
                GameStat.instance.getserverThread().getOos().reset();
                GameStat.instance.getserverThread().getOos().writeObject(choosenCards);
                GameStat.instance.getserverThread().getOos().flush();
                System.out.println("b");
            }
        }
    }


    public void setCardImage(String path, ImageView imageView)
    {
        Image image = new Image(getClass().getResourceAsStream(path));
        imageView.setImage(image);
        imageView.fitHeightProperty().bind(hBoxCards.heightProperty().divide(2));
        //imageView.fitWidthProperty().bind(hBoxCards.widthProperty().divide(10));
        imageView.setPreserveRatio(true);
    }

    private void initCases()
    {
        for (int x = 0; x < 28; x++) {
            for (int y = 0; y < 12; y++) {
                setCaseImageToBoard("../images/board/Plato_" + String.valueOf(x) + "_" + String.valueOf(y) + ".png", x, y);
            }
        }
    }

    public void refreshAll()
    {
        refreshBoard();
        refreshLabelsAndButtons();
        refreshCards();
    }

    public void refreshBoard()
    {
        for (ImageView img : playersImage) {
            gpBoard.getChildren().remove(img);
        }
        playersImage.clear();
        for (Player player : GameStat.instance.players().getPlayers()) {
            int nb = GameStat.instance.players().getPlayers().indexOf(player);
            setPlayerImageToBoard(player, "../images/player/robot_" + String.valueOf(nb) + ".png", player.getPosition().getX(), player.getPosition().getY());
        }

    }

    public void refreshLabelsAndButtons()
    {
        lblHp.setText(String.valueOf(GameStat.instance.getCurrentClientPlayer().getHealthPoints()));
        lblDp.setText(String.valueOf(GameStat.instance.getCurrentClientPlayer().getDamagePoints()));

        if (!GameStat.instance.getGameSituation().isStarted()) {
            lblGameSituation.setText("En attente du chef pour lancer la partie ...");
        } else if (GameStat.instance.getGameSituation().isChoosingCards()) {
            lblGameSituation.setText("Choisissez vos cartes !");
        } else if (GameStat.instance.getGameSituation().isMoving()) {
            lblGameSituation.setText("Les joueurs se déplacent ...");
        } else
            lblGameSituation.setText("");

        if (GameStat.instance.players().getPlayers().indexOf(GameStat.instance.getCurrentClientPlayer()) == 0 && !GameStat.instance.getGameSituation().isStarted()) {
            btnStartGame.setVisible(true);
            lblGameSituation.setText("Cliquez pour lancer la partie !");
        } else
            btnStartGame.setVisible(false);


        int nb = GameStat.instance.players().getPlayers().indexOf(GameStat.instance.getCurrentClientPlayer());
        Image image = new Image(getClass().getResourceAsStream("../images/player/robot_" + String.valueOf(nb) + ".png"));
        ImageView imageView = imgMe;
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
    }

    public void refreshCards()
    {
        choosenCards.clear();

        for (ImageView i : cardsImage) {
            i.setImage(null);
            i.setEffect(null);
        }

        int i = 0;

        for (Card c : GameStat.instance.getCurrentClientPlayer().getCards().getCardsList()) {
            setCardImage(c.getImage(), cardsImage.get(i));
            i++;
        }


    }

    public void setCaseImageToBoard(String imagePath, int x, int y)
    {
        Image image = new Image(getClass().getResourceAsStream(imagePath), 50, 50, false, false);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.fitHeightProperty().bind(gpBoard.heightProperty().divide(11));
        imageView.fitWidthProperty().bind(gpBoard.widthProperty().divide(27));
        imageView.setPreserveRatio(false);
        gpBoard.add(imageView, x, y);
    }

    public void setPlayerImageToBoard(Player player, String imagePath, int x, int y)
    {
        Image image = new Image(getClass().getResourceAsStream(imagePath), 50, 50, true, true);
        ImageView imageView = new ImageView();

        imageView.setImage(image);
        imageView.fitHeightProperty().bind(gpBoard.heightProperty().divide(11));
        imageView.fitWidthProperty().bind(gpBoard.widthProperty().divide(27));
        imageView.xProperty().bind(gpBoard.layoutXProperty().add(x));
        imageView.setPreserveRatio(true);
        gpBoard.add(imageView, x, y);
        playersImage.add(imageView);
    }


    @FXML
    public void imgClicked0() throws IOException
    {
        imgClicked(imCard0);
    }

    @FXML
    public void imgClicked1() throws IOException
    {
        imgClicked(imCard1);
    }

    @FXML
    public void imgClicked2() throws IOException
    {
        imgClicked(imCard2);
    }

    @FXML
    public void imgClicked3() throws IOException
    {
        imgClicked(imCard3);
    }

    @FXML
    public void imgClicked4() throws IOException
    {
        imgClicked(imCard4);
    }

    @FXML
    public void imgClicked5() throws IOException
    {
        imgClicked(imCard5);
    }

    @FXML
    public void imgClicked6() throws IOException
    {
        imgClicked(imCard6);
    }

    @FXML
    public void imgClicked7() throws IOException
    {
        imgClicked(imCard7);
    }

    @FXML
    public void imgClicked8() throws IOException
    {
        imgClicked(imCard8);
    }

}