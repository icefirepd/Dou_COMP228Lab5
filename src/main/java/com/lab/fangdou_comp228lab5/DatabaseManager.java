package com.lab.fangdou_comp228lab5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final String dbURL = "jdbc:oracle:thin:@//199.212.26.208:1521/SQLD";
    private final String dbUser = "COMP228_F24_soh_8";
    private final String dbPassword = "password123";

    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGame(int gameId, String gameTitle, int score, String datePlayed, int playerId) {
        String query = "INSERT INTO Fang_Dou_game (game_id, game_title) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, gameId);
            statement.setString(2, gameTitle);
            statement.executeUpdate();
            String gameQuery = "INSERT INTO Fang_Dou_player_and_game (game_id, player_id, score, playing_date) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement gameStatement = connection.prepareStatement(gameQuery)) {
                gameStatement.setInt(1, gameId);
                gameStatement.setInt(2, playerId);
                gameStatement.setInt(3, score);
                gameStatement.setString(4, datePlayed);
                gameStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPlayer(int playerId, String firstName, String lastName, String address,
                          String postalCode, String province, String phoneNumber, int gameId,
                          int score, String datePlayed) {

        String query = "INSERT INTO Fang_Dou_player (player_id, first_name, last_name, address, postal_code, province, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, address);
            statement.setString(5, postalCode);
            statement.setString(6, province);
            statement.setString(7, phoneNumber);
            statement.executeUpdate();

            String gameQuery = "INSERT INTO Fang_Dou_player_and_game (game_id, player_id, score, playing_date) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement gameStatement = connection.prepareStatement(gameQuery)) {
                gameStatement.setInt(1, gameId);
                gameStatement.setInt(2, playerId);
                gameStatement.setInt(3, score);
                gameStatement.setString(4, datePlayed);
                gameStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayer(int playerId, String firstName, String lastName, String address,
                             String postalCode, String province, String phoneNumber) {
        String query = "UPDATE Fang_Dou_player SET first_name = ?, last_name = ?, address = ?, postal_code = ?, province = ?, phone_number = ? " +
                "WHERE player_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, postalCode);
            statement.setString(5, province);
            statement.setString(6, phoneNumber);
            statement.setInt(7, playerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPlayerGames(int playerId) {
        String query;
        if (playerId > 0) {
            query = "SELECT g.game_title, pg.playing_date, pg.score " +
                    "FROM F_D_game g " +
                    "JOIN F_D_player_and_game pg ON g.game_id = pg.game_id " +
                    "WHERE pg.player_id = ?";
        } else {
            query = "SELECT p.player_id, g.game_title, pg.playing_date, pg.score " +
                    "FROM F_D_player_and_game pg " +
                    "JOIN F_D_game g ON pg.game_id = g.game_id " +
                    "JOIN F_D_player p ON pg.player_id = p.player_id";
        }

        List<String> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if (playerId > 0) {
                statement.setInt(1, playerId);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String gameInfo;
                if (playerId > 0) {
                    gameInfo = "Game: " + resultSet.getString("game_title") +
                            ", Date: " + resultSet.getDate("playing_date") +
                            ", Score: " + resultSet.getInt("score");
                } else {
                    gameInfo = "Player ID: " + resultSet.getInt("player_id") +
                            ", Game: " + resultSet.getString("game_title") +
                            ", Date: " + resultSet.getDate("playing_date") +
                            ", Score: " + resultSet.getInt("score");
                }
                results.add(gameInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<String> getAllPlayerGames() {
        String query = "SELECT g.game_title, pg.playing_date, pg.score, p.first_name, p.last_name " +
                "FROM Fang_Dou_game g " +
                "JOIN Fang_Dou_player_and_game pg ON g.game_id = pg.game_id " +
                "JOIN Fang_Dou_player p ON p.player_id = pg.player_id";
        List<String> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String gameInfo = "Player: " + resultSet.getString("first_name") + " " + resultSet.getString("last_name") +
                        ", Game: " + resultSet.getString("game_title") +
                        ", Date: " + resultSet.getDate("playing_date") +
                        ", Score: " + resultSet.getInt("score");
                results.add(gameInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}