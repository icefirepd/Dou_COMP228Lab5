package com.lab.fangdou_comp228lab5;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML private TextField gameIdField;
    @FXML private TextField gameTitleField;
    @FXML private TextField playerIdField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField;
    @FXML private TextField postalCodeField;
    @FXML private TextField provinceField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField scoreField;
    @FXML private DatePicker datePlayedField;
    @FXML private Button addGameButton;
    @FXML private Button addPlayerButton;
    @FXML private Button updatePlayerButton;
    @FXML private Button viewPlayerGamesButton;
    @FXML private TextArea resultArea;

    public String getGameId() {
        return gameIdField.getText();
    }
    public String getGameTitle() {
        return gameTitleField.getText();
    }
    public String getPlayerId() {
        return playerIdField.getText();
    }
    public String getFirstName() {
        return firstNameField.getText();
    }
    public String getLastName() {
        return lastNameField.getText();
    }
    public String getAddress() {
        return addressField.getText();
    }
    public String getPostalCode() {
        return postalCodeField.getText();
    }
    public String getProvince() {
        return provinceField.getText();
    }
    public String getPhoneNumber() {
        return phoneNumberField.getText();
    }
    public void setResultArea(String text) {
        resultArea.setText(text);
    }
    public Button getAddGameButton() {
        return addGameButton;
    }
    public Button getAddPlayerButton() {
        return addPlayerButton;
    }
    public Button getUpdatePlayerButton() {
        return updatePlayerButton;
    }
    public Button getViewPlayerGamesButton() {
        return viewPlayerGamesButton;
    }
    public String getScore() {
        return scoreField.getText();
    }
    public String getDatePlayed() {
        return datePlayedField.getValue() != null ? datePlayedField.getValue().toString() : "";
    }
}