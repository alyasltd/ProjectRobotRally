package application.controllers;

import application.classes.GameStat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlMenu implements Initializable
{
    public static CtrlMenu instance = null;
    @FXML
    private Button btnStart;

    @FXML
    private Label lblRobot;

    @FXML
    private Label lblError;

    @FXML
    private TextField tfIp;

    @FXML
    private TextField tfPseudo;

    @FXML
    private ProgressIndicator progressBar;

    private Stage board = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        instance = this;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ViewBoard.fxml"));
        Parent parent = null;
        try {
            parent = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        board = new Stage();
        board.setTitle("Board");
        board.setScene(new Scene(parent, 900, 400));
        board.show();
        board.setMaximized(true);

        setBoardVisible(false);
    }

    @FXML
    private void btnStartClicked(ActionEvent event) throws IOException
    {
//        event.consume();
        if(tfPseudo.getText().isEmpty() || tfIp.getText().isEmpty())
            return;

        System.out.println("Button start clicked");

        progressBar.setVisible(true);

        Socket socket = new Socket(tfIp.getText(), 2566);

        try {
            GameStat.instance.setServerThread(socket);

        } catch (Exception e) {
            System.out.println("Exception occured in client main: " + e.getStackTrace());
            lblError.setVisible(true);
            lblError.setText("Connexion to server failed");
            progressBar.setVisible(false);
            return;
        }

        try {
            setBoardVisible(true);
            setMenuVisible(false);
        } catch(Exception e) {
            progressBar.setVisible(false);
            e.printStackTrace();
            lblError.setVisible(true);
            lblError.setText("Exception occured in client main: " + e.getStackTrace());
            return;
        }
    }

    public void setBoardVisible(boolean visible)
    {
        if(visible)
            board.show();
        else
            board.hide();
    }

    public void setMenuVisible(boolean visible)
    {
        Stage stage = (Stage) btnStart.getScene().getWindow();
        if(visible)
            stage.show();
        else
            stage.hide();
    }

}
