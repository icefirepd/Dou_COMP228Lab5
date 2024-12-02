package com.lab.fangdou_comp228lab5;

import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class DatabaseController {
    private final DatabaseManager model;
    private final HelloController view;

    public DatabaseController(DatabaseManager model, HelloController view) {
        this.model = model;
        this.view = view;

        view.getAddGameButton().setOnAction(this::addGame);
        view.getAddPlayerButton().setOnAction(this::addPlayer);
        view.getUpdatePlayerButton().setOnAction(this::updatePlayer);
        view.getViewPlayerGamesButton().setOnAction(this::viewPlayerGames);
    }

    private void addGame(ActionEvent e) {
        int gameId = Integer.parseInt(view.getGameId());
        String gameTitle = view.getGameTitle();
        int score = Integer.parseInt(view.getScore());
        String datePlayed = view.getDatePlayed();
        int playerId = Integer.parseInt(view.getPlayerId());  // Assuming player ID is entered

        model.addGame(gameId, gameTitle, score, datePlayed, playerId);
        view.setResultArea("Game added: " + gameTitle + " with score: " + score);
    }

    private void addPlayer(ActionEvent e) {
        int playerId = Integer.parseInt(view.getPlayerId());
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String address = view.getAddress();
        String postalCode = view.getPostalCode();
        String province = view.getProvince();
        String phoneNumber = view.getPhoneNumber();
        int gameId = Integer.parseInt(view.getGameId());
        int score = Integer.parseInt(view.getScore());
        String datePlayed = view.getDatePlayed();

        model.addPlayer(playerId, firstName, lastName, address, postalCode, province, phoneNumber, gameId, score, datePlayed);
        view.setResultArea("Player added: " + firstName + " " + lastName);
    }

    private void updatePlayer(ActionEvent e) {
        int playerId = Integer.parseInt(view.getPlayerId());
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String address = view.getAddress();
        String postalCode = view.getPostalCode();
        String province = view.getProvince();
        String phoneNumber = view.getPhoneNumber();

        model.updatePlayer(playerId, firstName, lastName, address, postalCode, province, phoneNumber);
        view.setResultArea("Player updated: " + firstName + " " + lastName);
    }

    private void viewPlayerGames(ActionEvent e) {
        int playerId = Integer.parseInt(view.getPlayerId());
        List<String> games = null;
        if (playerId != 0) {
            games = model.getPlayerGames(playerId);
        } else {
            games = model.getAllPlayerGames();
        }
        view.setResultArea(String.join("\n", games));
    }

//    private void viewPlayerGames(ActionEvent e) {
//        int playerId = Integer.parseInt(view.getPlayerId());
//        var games = model.getPlayerGames(playerId);
//        view.setResultArea(String.join("\n", games));
//    }
}
