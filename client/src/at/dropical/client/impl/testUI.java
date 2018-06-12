package at.dropical.client.impl;

import at.dropical.client.DropicalListener;
import at.dropical.client.DropicalProxy;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.container.ListDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;
import at.dropical.shared.net.requests.JoinRequest;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class testUI extends Application implements DropicalListener {

    private Stage stage;
    private Scene scene;
    private DropicalProxy proxy;
    private String BESCHTER_PLAYERNAME="Kemps_JFX_GUI2";

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        scene=new Scene(new Label("New Game Starting"),32*10*2,32*20);
        stage.setScene(scene);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case W:
                    proxy.writeToServer(new HandleInputRequest(BESCHTER_PLAYERNAME,PlayerAction.UP));
                    break;
                case A:
                    proxy.writeToServer(new HandleInputRequest(BESCHTER_PLAYERNAME,PlayerAction.LEFT));
                    break;
                case S:
                    proxy.writeToServer(new HandleInputRequest(BESCHTER_PLAYERNAME,PlayerAction.DOWN));
                    break;
                case D:
                    proxy.writeToServer(new HandleInputRequest(BESCHTER_PLAYERNAME,PlayerAction.RIGHT));
                    break;
                case SPACE:
                    proxy.writeToServer(new HandleInputRequest(BESCHTER_PLAYERNAME,PlayerAction.DROP));
                    break;
                case P:
                    proxy.writeToServer(new HandleInputRequest(BESCHTER_PLAYERNAME,PlayerAction.PAUSE));
                    break;
            }
        });
        stage.show();

        proxy = new DropicalProxy("localhost", 45000, this);
        //fixme Change that back.
        //proxy.writeToServer(new JoinRequest(BESCHTER_PLAYERNAME));
        proxy.writeToServer(new JoinRequest(BESCHTER_PLAYERNAME, true));

    }

    @Override
    public void countDown(CountDownContainer container) {
        Platform.runLater(()->{
            VBox root = new VBox();
            for (String s : container.getPlayernames()) {
                root.getChildren().add(new Label("Name: "+s));
            }
            root.getChildren().add(new Label("Time till start: "+container.getSeconds()));

            root.setPrefSize(200,100);
            scene.setRoot(root);
        });
    }

    @Override
    public void updateUI(GameDataContainer container) {
        Platform.runLater(() -> {
            HBox root = new HBox();

            GridPane left = new GridPane();
            GridPane right = new GridPane();

            root.getChildren().addAll(left, right);

            for (int y = 0; y < 20; y++) {
                for (int x = 0; x < 10; x++) {
//                if(container.getCurrTrockXs().get(0)==x&&container.getCurrTrocks().get(0)[y][x]!=0)
                    left.add(container.getArenas().get(0)[y][x] != 0 ? new Rectangle(32, 32, Paint.valueOf("green")) : new Rectangle(32, 32, Paint.valueOf("white")), x, y);
                    right.add(container.getArenas().get(1)[y][x] != 0 ? new Rectangle(32, 32, Paint.valueOf("blue")) : new Rectangle(32, 32, Paint.valueOf("white")), x, y);
                }
            }

            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if (container.getCurrTrockYs().get(0) + y >= 0 && container.getCurrTrocks().get(0)[y][x] != 0)
                        left.add(new Label("" + container.getCurrTrocks().get(0)[y][x]), container.getCurrTrockXs().get(0) + x, container.getCurrTrockYs().get(0) + y);
                    if (container.getCurrTrockYs().get(1) + y >= 0 && container.getCurrTrocks().get(1)[y][x] != 0)
                        right.add(new Label("" + container.getCurrTrocks().get(1)[y][x]), container.getCurrTrockXs().get(1) + x, container.getCurrTrockYs().get(1) + y);
                }
            }
            root.setMinSize(32*10*20,32*20);
            scene.setRoot(root);
        });
    }

    @Override
    public void somebodyJoinedTheLobby(ListDataContainer container) {
        Platform.runLater(()->{
            VBox root = new VBox();
            for (String s : container.getNames()) {
                root.getChildren().add(new Label("Name: "+s));
            }
            root.setPrefSize(200,100);
            scene.setRoot(root);
        });
    }

    @Override
    public void onGameOver(GameOverContainer container) {
        Platform.runLater(()->{
            VBox root = new VBox();
                    for (String s : container.getPlayernames()) {
                        if(!container.getLooser().equals(s))
                            root.getChildren().add(new Label("Winner: "+s));
                        else
                            root.getChildren().add(new Label("Looser: "+s));
                    }


            root.setPrefSize(100,100);
            scene.setRoot(root);
        });
    }
}
