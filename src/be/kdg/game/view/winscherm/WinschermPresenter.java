package be.kdg.game.view.winscherm;


import be.kdg.game.model.Game;
import be.kdg.game.model.GameModel;
import be.kdg.game.view.spel.SpelPresenter;
import be.kdg.game.view.spel.SpelView;
import be.kdg.game.view.startscherm.StartschermPresenter;
import be.kdg.game.view.startscherm.StartschermView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WinschermPresenter extends GridPane {
    private WinschermView view;
    private GameModel model;
    private Game game;

    public WinschermPresenter(WinschermView view, GameModel model) {
        this.view = view;
        this.model = model;
        this.game = new Game();

        this.addEventHandlers();
        this.updateView();
    }

    private void updateView() {

    }

    private void addEventHandlers() {
        view.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        view.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //continue playing

            }
        });

    }

}
