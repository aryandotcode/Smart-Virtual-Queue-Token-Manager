package com.smartqueue.ui;

import com.smartqueue.dao.TokenDAO;
import com.smartqueue.model.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class AdminDashboard extends JFrame {
    private JTextArea queueArea;
    private JButton serveButton, refreshButton, logoutButton;
    private TokenDAO tokenDAO;
    private Timer refreshTimer;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tokenDAO = new TokenDAO();

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        queueArea = new JTextArea(15, 40);
        queueArea.setEditable(false);
        queueArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(queueArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        serveButton = new JButton("Serve Next Token");
        serveButton.setFont(new Font("Arial", Font.BOLD, 14));
        serveButton.addActionListener(e -> serveNextToken());

        refreshButton = new JButton("Refresh Queue");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 14));
        refreshButton.addActionListener(e -> refreshQueue());

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());

        buttonPanel.add(serveButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(logoutButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Auto-refresh every 5 seconds
        refreshTimer = new Timer(5000, e -> refreshQueue());
        refreshTimer.start();

        refreshQueue();
        setVisible(true);
    }

    private void refreshQueue() {
        try {
            List<Token> tokens = tokenDAO.getAllPendingTokens();
            StringBuilder sb = new StringBuilder();
            sb.append("================= CURRENT QUEUE =================\n");
            sb.append(" Token ID | Status \n");
            sb.append("-------------------------------\n");

            if (tokens.isEmpty()) {
                sb.append("      No tokens in queue\n");
            } else {
                for (Token token : tokens) {
                    sb.append(String.format("   %-6d | %-7s \n", token.getId(), token.getStatus()));
                }
            }
            queueArea.setText(sb.toString());
        } catch (SQLException ex) {
            queueArea.setText("Error loading queue: " + ex.getMessage());
        }
    }

    private void serveNextToken() {
        try {
            Token token = tokenDAO.getNextPendingToken();
            if (token != null) {
                if (tokenDAO.serveToken(token.getId())) {
                    refreshQueue();
                    JOptionPane.showMessageDialog(this, "Served token #" + token.getId());
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to serve token");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No pending tokens");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error serving token: " + ex.getMessage());
        }
    }

    private void logout() {
        refreshTimer.stop();
        dispose();
        new LoginForm();
    }
}