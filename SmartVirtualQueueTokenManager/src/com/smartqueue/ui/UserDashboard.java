package com.smartqueue.ui;

import com.smartqueue.dao.TokenDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class UserDashboard extends JFrame {
    private JLabel statusLabel;
    private JButton generateButton;
    private TokenDAO tokenDAO;
    private Timer statusTimer;
    private int generatedTokenId = -1;

    public UserDashboard() {
        setTitle("User Dashboard");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tokenDAO = new TokenDAO();

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        statusLabel = new JLabel("Click 'Generate Token' to get a token", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(statusLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        generateButton = new JButton("Generate Token");
        generateButton.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonPanel.add(generateButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        buttonPanel.add(logoutButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        generateButton.addActionListener(e -> generateToken());

        // Auto-update status every 10 seconds
        statusTimer = new Timer(10000, evt -> updateTokenStatus());
        statusTimer.start();

        setVisible(true);
    }

    private void generateToken() {
        try {
            int tokenId = tokenDAO.generateToken();
            if (tokenId != -1) {
                generatedTokenId = tokenId;
                statusLabel.setText("Your token number is: " + tokenId);
                updateTokenStatus();
            } else {
                statusLabel.setText("Token generation failed. Try again.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error generating token: " + ex.getMessage());
        }
    }

    private void updateTokenStatus() {
        if (generatedTokenId == -1) return;

        try {
            int position = tokenDAO.getTokenPosition(generatedTokenId);
            if (position == -1) {
                statusLabel.setText("Token #" + generatedTokenId + " has been served!");
                statusTimer.stop();
            } else if (position == 0) {
                statusLabel.setText("Token #" + generatedTokenId + " - YOU ARE NEXT!");
            } else {
                statusLabel.setText("Token #" + generatedTokenId +
                        " - " + position + " tokens ahead of you");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error checking status: " + ex.getMessage());
        }
    }

    private void logout() {
        statusTimer.stop();
        dispose();
        new LoginForm();
    }
}