package com.example.tp_1;

import com.example.tp_1.root.Root;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;

import static com.example.tp_1.root.Screens.screen;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Preferences preferences = Preferences.userRoot();

        if (!preferences.getBoolean("is-auth", true)){
            getRoot(Root.HOME, stage);
        }else {
            getRoot(Root.LOGIN, stage);
        }
    }

    public void getRoot(Root root, Stage stage) throws IOException {
        switch (root){
            case HOME: { screen("chat-view.fxml", "Chatting App", stage); } break;
            case LOGIN : { screen("login-view.fxml", "Login", stage); } break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}