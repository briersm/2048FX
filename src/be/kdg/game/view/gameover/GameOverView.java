package be.kdg.game.view.gameover;

import be.kdg.game.model.Game;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GameOverView extends GridPane {
    private Label lblGO;
    private Button btnTryAgain;
    private Label lblScore;
    private Label lblHighscore;
    private Button btnQuit;
    private Button btnHighScores;
    private VBox vbox;
    private Game game;

    public GameOverView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    public void initialiseNodes() {
        this.btnTryAgain = new Button("Try Again");
        this.btnHighScores = new Button("Highscores");
        this.btnQuit = new Button("Quit");
        this.lblGO = new Label("GAME OVER");
        this.vbox = new VBox(10);       //10 spacing
        this.lblHighscore = new Label("Highscore: ");
        this.lblScore = new Label("Your Score: ");

        //Fonts
        Font titleFont = new Font("Verdana", 50);
        Font btnFont = new Font("Verdana", 25);
        Font scoreFont = new Font("Verdana", 25);

        lblGO.setFont(titleFont);
        lblScore.setFont(scoreFont);
        lblHighscore.setFont(scoreFont);
        btnTryAgain.setFont(btnFont);
        btnHighScores.setFont(btnFont);
        btnQuit.setFont(btnFont);

        //Styling
        btnTryAgain.setPrefWidth(300);
        btnQuit.setPrefWidth(300);
        btnHighScores.setPrefWidth(300);
    }

    public void layoutNodes() {
        this.setPadding(new Insets(20));

        this.add(lblGO, 0, 0, 2, 1); //col, row, colspan, rowspan
        this.add(vbox, 0, 1);

        vbox.getChildren().addAll(lblScore, lblHighscore, btnTryAgain, btnHighScores, btnQuit);
        vbox.setAlignment(Pos.CENTER);


        GridPane.setHalignment(lblGO, HPos.CENTER);
        GridPane.setHalignment(btnQuit, HPos.CENTER);
        GridPane.setHalignment(btnHighScores, HPos.CENTER);
        GridPane.setHalignment(btnTryAgain, HPos.CENTER);
        GridPane.setHalignment(lblHighscore, HPos.CENTER);
        GridPane.setHalignment(lblScore, HPos.CENTER);


        setVgap(30);
    }

    //Getters

    public Label getLblGO() {
        return lblGO;
    }

    public Button getBtnTryAgain() {
        return btnTryAgain;
    }

    public Label getLblScore() {
        return lblScore;
    }

    public Label getLblHighscore() {
        return lblHighscore;
    }

    public Button getBtnQuit() {
        return btnQuit;
    }

    public Button getBtnHighScores() {
        return btnHighScores;
    }

    public VBox getVbox() {
        return vbox;
    }
}
