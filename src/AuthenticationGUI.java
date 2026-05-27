import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AuthenticationGUI {

    private final Authenticator authenticator =
            new MySQLAuthenticator();

    private JFrame frame;

    private CardLayout cardLayout;

    public AuthenticationGUI() {

        frame = new JFrame(
                "Timetable Management System"
        );

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.setSize(400, 400);

        cardLayout = new CardLayout();

        frame.setLayout(cardLayout);

        showLoginScreen();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void showLoginScreen() {

        JPanel loginPanel =
                new JPanel(new GridBagLayout());

        JLabel titleLabel =
                new JLabel("Login");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        JLabel usernameLabel =
                new JLabel("Email:");

        JTextField usernameField =
                new JTextField(15);

        JLabel passwordLabel =
                new JLabel("Password:");

        JPasswordField passwordField =
                new JPasswordField(15);

        JButton loginButton =
                new JButton("Login");

        JButton signUpButton =
                new JButton("Sign Up");

        JLabel statusLabel =
                new JLabel("");

        statusLabel.setForeground(Color.RED);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        loginPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;

        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;

        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;

        loginPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;

        loginPanel.add(signUpButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        loginPanel.add(statusLabel, gbc);

        frame.add(loginPanel, "login");

        cardLayout.show(
                frame.getContentPane(),
                "login"
        );

        loginButton.addActionListener(e -> {

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    ).trim();

            int status =
                    authenticator.isAuthenticated(
                            username,
                            password
                    );

            System.out.println(
                    "DEBUG: auth status = " + status
            );

            if (status == -1) {

                statusLabel.setForeground(Color.RED);

                statusLabel.setText(
                        "Invalid username or password."
                );

            } else {

                statusLabel.setForeground(Color.GREEN);

                statusLabel.setText(
                        "Login successful!"
                );

                if (status == 0) {

                    new TimeTableGUI(frame);

                } else if (status == 1) {

                    new AdminGUI(frame);
                }
            }
        });

        signUpButton.addActionListener(
                e -> showSignUpScreen()
        );
    }

    private void showSignUpScreen() {

        JPanel signUpPanel =
                new JPanel(new GridBagLayout());

        JLabel titleLabel =
                new JLabel("Sign Up");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        JLabel usernameLabel =
                new JLabel("Email:");

        JTextField usernameField =
                new JTextField(15);

        JLabel passwordLabel =
                new JLabel("Password:");

        JPasswordField passwordField =
                new JPasswordField(15);

        JButton signUpButton =
                new JButton("Sign Up");

        JButton backButton =
                new JButton("Back");

        JLabel statusLabel =
                new JLabel("");

        statusLabel.setForeground(Color.RED);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        signUpPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;

        signUpPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;

        signUpPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        signUpPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        signUpPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        signUpPanel.add(signUpButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;

        signUpPanel.add(backButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        signUpPanel.add(statusLabel, gbc);

        frame.add(signUpPanel, "signup");

        cardLayout.show(
                frame.getContentPane(),
                "signup"
        );

        signUpButton.addActionListener(e -> {

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    ).trim();

            if (!username.isEmpty()
                    && !password.isEmpty()) {

                authenticator.signUp(
                        username,
                        password
                );

                statusLabel.setForeground(
                        Color.GREEN
                );

                statusLabel.setText(
                        "Sign-up successful!"
                );

            } else {

                statusLabel.setForeground(
                        Color.RED
                );

                statusLabel.setText(
                        "All fields are required."
                );
            }
        });

        backButton.addActionListener(e -> {

            cardLayout.show(
                    frame.getContentPane(),
                    "login"
            );
        });
    }
}