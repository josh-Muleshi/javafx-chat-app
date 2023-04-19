package com.example.tp_1;

import com.example.tp_1.source.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import static com.example.tp_1.root.Screens.screen2;

public class LoginController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Label incorrectUsernameOrPassword;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {

        String user_name = userName.getText();
        String pass_word = password.getText();

        ResultSet resultSet;
        PreparedStatement preparedStatement;
        DbConnection connection;

        try {
            if (!user_name.isEmpty() || !pass_word.isEmpty()){
                connection = DbConnection.getInstance();

                String query = "SELECT * FROM login where username = ? AND password = ?";
                preparedStatement = connection.getConnection().prepareCall(query);
                preparedStatement.setString(1, user_name);
                preparedStatement.setString(2, pass_word);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Preferences preferences = Preferences.userRoot();
                    preferences.putBoolean("is-auth", false);

                    screen2("chat-view.fxml", "Chatting App", event);

                    System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3) + "  ");
                }else {
                    errorMessage();
                }
                connection.getConnection().close();
            }else {
                errorMessage();
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void errorMessage(){
        incorrectUsernameOrPassword.setText("Nom d'utilisateur ou mot de passe incorrecte");
    }
}
