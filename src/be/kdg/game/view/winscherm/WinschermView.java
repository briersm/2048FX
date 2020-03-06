package be.kdg.game.view.winscherm;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class WinschermView extends GridPane {
    private Label lblWin;
    private Button btnContinue;
    private Label lblScore;
    private Label lblHighscore;
    private Button btnQuit;
    private Button btnHighScores;
    private VBox vbox;
    private HBox hbox;
    private Button btnRestart;

    public WinschermView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    public void initialiseNodes() {
        this.btnHighScores = new Button("Highscores");
        this.btnQuit = new Button("Quit");
        this.lblWin = new Label("YOU WIN");
        this.vbox = new VBox(10);       //10 spacing
        this.lblHighscore = new Label("Highscore: ");
        this.lblScore = new Label("Your Score: ");
        this.btnContinue = new Button("Continue");
        this.btnRestart = new Button("Restart");
        this.hbox = new HBox(5);

        //Fonts
        Font titleFont = new Font("Verdana", 50);
        Font btnFont = new Font("Verdana", 25);
        Font scoreFont = new Font("Verdana", 25);
        Font conresFont = new Font("Verdana", 20);

        lblWin.setFont(titleFont);
        lblScore.setFont(scoreFont);
        lblHighscore.setFont(scoreFont);
        btnHighScores.setFont(btnFont);
        btnQuit.setFont(btnFont);
        btnContinue.setFont(conresFont);
        btnRestart.setFont(conresFont);


        //Styling
        btnQuit.setPrefWidth(300);
        btnHighScores.setPrefWidth(300);
        btnContinue.setPrefWidth(147.5);
        btnRestart.setPrefWidth(147.5);

    }

    public void layoutNodes() {
        this.setPadding(new Insets(20));

        this.add(lblWin, 0, 0, 2, 1); //col, row, colspan, rowspan
        this.add(vbox, 0, 1);

        hbox.getChildren().addAll(btnContinue, btnRestart);
        vbox.getChildren().addAll(lblScore, lblHighscore, hbox, btnHighScores, btnQuit);
        vbox.setAlignment(Pos.CENTER);


        GridPane.setHalignment(lblWin, HPos.CENTER);
        GridPane.setHalignment(btnQuit, HPos.CENTER);
        GridPane.setHalignment(btnHighScores, HPos.CENTER);
        GridPane.setHalignment(btnContinue, HPos.CENTER);
        GridPane.setHalignment(lblHighscore, HPos.CENTER);
        GridPane.setHalignment(lblScore, HPos.CENTER);


        setVgap(30);
    }

    //Getters

    public Label getLblWin() {
        return lblWin;
    }

    public Button getBtnContinue() {
        return btnContinue;
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