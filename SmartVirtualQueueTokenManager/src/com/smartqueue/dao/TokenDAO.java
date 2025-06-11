package com.smartqueue.dao;

import com.smartqueue.db.DatabaseConnection;
import com.smartqueue.model.Token;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TokenDAO {

    public int generateToken() throws SQLException {
        String query = "INSERT INTO tokens (status) VALUES ('pending')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new SQLException("Token generation failed, no ID obtained");
        }
    }

    public Token getNextPendingToken() throws SQLException {
        String query = "SELECT id, status FROM tokens WHERE status = 'pending' ORDER BY id ASC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return new Token(rs.getInt("id"), rs.getString("status"));
            }
            return null;
        }
    }

    public boolean serveToken(int id) throws SQLException {
        String query = "UPDATE tokens SET status = 'served' WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Token> getAllPendingTokens() throws SQLException {
        List<Token> tokens = new ArrayList<>();
        String query = "SELECT id, status FROM tokens WHERE status = 'pending' ORDER BY id ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                tokens.add(new Token(rs.getInt("id"), rs.getString("status")));
            }
        }
        return tokens;
    }

    public int getTokenPosition(int tokenId) throws SQLException {
        String query = "SELECT COUNT(*) AS position FROM tokens " +
                "WHERE status = 'pending' AND id < ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, tokenId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("position");
                }
            }
        }
        return -1;
    }
}