package com.example.tp_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.prefs.Preferences;

import static com.example.tp_1.root.Screens.screen2;

public class ChatController {
    @FXML
    public void onLogoutButtonClicked(ActionEvent event) throws IOException {
        Preferences preferences = Preferences.userRoot();
        preferences.putBoolean("is-auth", true);

        screen2("login-view.fxml", "Login", event);
    }
}