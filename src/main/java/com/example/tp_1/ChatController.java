package com.example.tp_1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static com.example.tp_1.root.Screens.screen2;

public class ChatController implements Initializable {

    @FXML
    private Button button_send;

    @FXML
    private TextField tf_message;

    @FXML
    private VBox vbox_message;

    @FXML
    private ScrollPane sp_main;

    private Server server;

    @FXML
    public void onLogoutButtonClicked(ActionEvent event) throws IOException {
        Preferences preferences = Preferences.userRoot();
        preferences.putBoolean("is-auth", true);

        screen2("login-view.fxml", "Login server", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            server = new Server(new ServerSocket(2023));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating server");;
        }

        vbox_message.heightProperty().addListener(
            (observableValue, number, t1) -> {
                sp_main.setVvalue((Double) t1);
            }
        );

        server.receiveMassageFromClient(vbox_message);

        button_send.setOnAction(event -> {
            String messageToSend = tf_message.getText();
            if (!messageToSend.isEmpty()){
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));

                Text text = new Text(messageToSend);
                TextFlow textFlow = new TextFlow(text);

                textFlow.setStyle("-fx-color: #FFFFFF; " +
                        "-fx-background-color: #50c984; " +
                        "-fx-background-radius: 20px");
                textFlow.setPadding(new Insets(5, 5, 5, 10));
                text.setFill(Color.color(0.934, 0.945, 0.996));

                hBox.getChildren().add(textFlow);
                vbox_message.getChildren().add(hBox);

                server.sendMessageToClient(messageToSend);
                tf_message.clear();
            }
        });
    }

    public static void addLabel(String messageFromClient, VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(messageFromClient);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-color: #9da7a7; " +
                "-fx-background-color: #FFFFFF; " +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 5, 5, 10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(() -> vBox.getChildren().add(hBox));
    }
}