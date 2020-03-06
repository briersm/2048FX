package be.kdg.game.view.startscherm;

import be.kdg.game.model.Cell;
import be.kdg.game.model.Game;
import be.kdg.game.model.GameModel;
import be.kdg.game.view.gameover.GameOverPresenter;
import be.kdg.game.view.gameover.GameOverView;
import be.kdg.game.view.spel.SpelPresenter;
import be.kdg.game.view.spel.SpelView;
import be.kdg.game.view.winscherm.WinschermPresenter;
import be.kdg.game.view.winscherm.WinschermView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StartschermPresenter {
    private GameModel model;
    private StartschermView view;
    private static final int CELL_SIZE = 64;

    public StartschermPresenter(GameModel model, StartschermView view) {
        this.model = model;
        this.view = view;

        this.addEventHandlers();
    }

    public void addEventHandlers() {
        //Play Game
        view.getBtnPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SpelView spelView = new SpelView();
                SpelPresenter spelPresenter = new SpelPresenter(model, spelView);
                Stage toevoegenStage = new Stage();
                toevoegenStage.initOwner(view.getScene().getWindow());
                toevoegenStage.setScene(new Scene(spelView));
                view.getScene().getWindow().hide();
                toevoegenStage.setTitle("2048");
                toevoegenStage.getIcons().add(new Image("icon2048.jpg"));




                FlowPane rootNode = new FlowPane();

                toevoegenStage.setResizable(false);
                toevoegenStage.setOnCloseRequest(event -> Platform.exit());

                Game game = new Game();
                Scene myScene = new Scene(rootNode, game.getWidth(), game.getHeight());
                toevoegenStage.setScene(myScene);

                myScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {        //game over simuleren
                        if (event.getCode() == KeyCode.L){
                            game.lose = true;
                        }

                        if (event.getCode() == KeyCode.W){      //win simuleren
                            game.win = true;
                        }

                        if (event.getCode() == KeyCode.ESCAPE) {
                            game.resetGame();
                        }

                        if (!game.canMove() || (!game.win && !game.canMove())) {
                            game.lose = true;
                        }


                        if (!game.win && !game.lose) {
                            switch (event.getCode()) {
                                case LEFT:
                                    game.left();
                                    break;
                                case RIGHT:
                                    game.right();
                                    break;
                                case DOWN:
                                    game.down();
                                    break;
                                case UP:
                                    game.up();
                                    break;
                            }
                        }
                        game.relocate(330, 390);
                    }
                });



                rootNode.getChildren().add(game);
                toevoegenStage.show();

                new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        GraphicsContext gc = game.getGraphicsContext2D();
                        gc.setFill(Color.rgb(187, 173, 160, 1.0));
                        gc.fillRect(0, 0, game.getWidth(), game.getHeight());

                        for(int y = 0; y < 4; y++) {
                            for(int x = 0; x < 4; x++){
                                Cell cell = game.getCells()[x + y * 4];
                                int value = cell.number;
                                int xOffset = offsetCoors(x);
                                int yOffset = offsetCoors(y);

                                gc.setFill(cell.getBackground());
                                gc.fillRoundRect(xOffset, yOffset, CELL_SIZE, CELL_SIZE, 14, 14);
                                gc.setFill(cell.getForeground());

                                final int size = value < 100 ? 32 : value < 1000 ? 28 : 24;
                                gc.setFont(Font.font("Verdana", FontWeight.BOLD, size));
                                gc.setTextAlign(TextAlignment.CENTER);


                                String s = String.valueOf(value);

                                if (value != 0)
                                    gc.fillText(s, xOffset + CELL_SIZE / 2, yOffset + CELL_SIZE / 2 - 2);
                                if(game.win || game.lose) {
                                    gc.setFill(Color.rgb(255, 255, 255));
                                    gc.fillRect(0, 0, game.getWidth(), game.getHeight());
                                    gc.setFill(Color.rgb(78, 139, 202));
                                    gc.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
                                    if(game.win){
                                        GameModel model = new GameModel();
                                        WinschermView wView = new WinschermView();
                                        WinschermPresenter wPresenter = new WinschermPresenter(wView, model);
                                        Stage wStage = new Stage();
                                        wStage.initOwner(view.getScene().getWindow());
                                        wStage.setScene(new Scene(wView));
                                        toevoegenStage.close();
                                        wStage.show();
                                        game.win = false;

                                    }
                                    if(game.lose) {
                                        GameModel model = new GameModel();
                                        GameOverView goView = new GameOverView();
                                        GameOverPresenter goPresenter = new GameOverPresenter(goView, model);
                                        Stage goStage = new Stage();
                                        goStage.initOwner(view.getScene().getWindow());
                                        goStage.setScene(new Scene(goView));
                                        toevoegenStage.hide();
                                        goStage.show();
                                        game.lose=false;
                                    }

                                }
                                gc.setFont(Font.font("Verdana", FontWeight.LIGHT, 18));
                                gc.fillText("Score: " + game.score, 200, 350);
                            }
                        }
                    }
                }.start();
            }

            private int offsetCoors(int arg) {
                return arg * (16 + 64) + 16;
            }

        });


        //Quit Game
        view.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        //Highscores
        view.getBtnHighScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //highscore scherm
            }
        });
    }
}
