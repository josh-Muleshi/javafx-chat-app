package com.example.tp_1.root;

import com.example.tp_1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Screens {
    static FXMLLoader fxmlLoader;
    static Scene scene;
    public static void screen(String view, String name, Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource(view));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle(name);
        stage.setScene(scene);
        stage.show();
    }
    public static void screen2(String view, String name, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource(view));
        Parent parent = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(parent);
        stage.setTitle(name);
        stage.setScene(scene);
        stage.show();
    }
}
