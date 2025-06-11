package com.smartqueue.ui;

import com.smartqueue.dao.UserDAO;
import com.smartqueue.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class RegisterForm extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;

    public RegisterForm() {
        setTitle("Register User");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Role:"));
        roleBox = new JComboBox<>(new String[]{"user", "admin"});
        panel.add(roleBox);

        JButton registerButton = new JButton("Register");
        panel.add(registerButton);

        JButton backButton = new JButton("Back to Login");
        panel.add(backButton);

        registerButton.addActionListener(e -> registerUser());
        backButton.addActionListener(e -> backToLogin());

        add(panel);
        setVisible(true);
    }

    private void registerUser() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String role = (String) roleBox.getSelectedItem();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        try {
            User user = new User(name, email, password, role);
            UserDAO dao = new UserDAO();
            if (dao.registerUser(user)) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                backToLogin();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(this, "Email already registered");
            } else {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }
        }
    }

    private void backToLogin() {
        dispose();
        new LoginForm();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterForm());
    }
}