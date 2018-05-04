package at.dropical.client.impl;

import at.dropical.client.DropicalListener;
import at.dropical.client.DropicalProxy;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.container.ListDataContainer;
import at.dropical.shared.net.requests.JoinRequest;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class testUI extends Application implements DropicalListener {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;


        DropicalProxy proxy = new DropicalProxy("localhost", 45000, this);

        proxy.writeToServer(new JoinRequest("KempsUI"));

    }

    @Override
    public void countDown(CountDownContainer container) {


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

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        });
    }

    @Override
    public void somebodyJoinedTheLobby(ListDataContainer container) {

    }

    @Override
    public void onGameOver(GameOverContainer container) {

    }
}
