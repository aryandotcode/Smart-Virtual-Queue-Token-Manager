package com.smartqueue.ui;

import com.smartqueue.dao.UserDAO;
import com.smartqueue.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;

    public RegisterForm() {
        setTitle("Register User");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
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

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User(
                        nameField.getText(),
                        emailField.getText(),
                        new String(passwordField.getPassword()),
                        (String) roleBox.getSelectedItem()
                );

                UserDAO dao = new UserDAO();
                if (dao.registerUser(user)) {
                    JOptionPane.showMessageDialog(null, "User Registered Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed.");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterForm();
    }
}
