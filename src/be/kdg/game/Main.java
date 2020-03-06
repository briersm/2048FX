package be.kdg.game;

import be.kdg.game.model.GameModel;
import be.kdg.game.view.startscherm.StartschermView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import be.kdg.game.view.startscherm.*;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameModel model = new GameModel();
        StartschermView view = new StartschermView();
        StartschermPresenter presenter = new StartschermPresenter(model, view);
        stage.setScene(new Scene(view));
        presenter.addEventHandlers();
        stage.setTitle("2048");
        stage.show();
        stage.getIcons().add(new Image("icon2048.jpg"));
        stage.setResizable(false); //resizen werkt toch niet
    }
}
