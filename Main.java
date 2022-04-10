package application;

import application.classes.GameStat;
import application.classes.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        GameStat.init();


        Parent root = FXMLLoader.load(getClass().getResource("views/ViewMenu.fxml"));
        Scene scene = new Scene(root, 900, 400);

        window.setTitle("Robot Rally");
        window.setScene(scene);
        window.show();
        window.setMaximized(true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
