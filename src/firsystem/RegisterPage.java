package firsystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import firsystem.LoginPage;
import firsystem.RegisterPage;

public class RegisterPage extends JFrame implements ActionListener {

    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;
    private JLabel titleLabel, usernameLabel, emailLabel, passwordLabel, backgroundLabel;

    public RegisterPage() {
        super("Register Page");
        setSize(607, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add background image
        ImageIcon background = new ImageIcon("C:\\\\Users\\\\sanch\\\\Downloads\\\\fir.jpeg");
        backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds (0,0,607, 1080);
        add(backgroundLabel);

        titleLabel = new JLabel("Create an Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        backgroundLabel.add(titleLabel);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setOpaque(false);
        inputPanel.setBounds(150, 100, 300, 150);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        inputPanel.add(usernameLabel);
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        inputPanel.add(emailLabel);
        emailField = new JTextField();
        inputPanel.add(emailField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        inputPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        // Set horizontal alignment to center for labels, text fields, and password field
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        emailLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        usernameField.setHorizontalAlignment(JTextField.CENTER);
        emailField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setHorizontalAlignment(JTextField.CENTER);

        backgroundLabel.add(inputPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(200, 270, 200, 50);

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        registerButton.setBackground(Color.GREEN);
        registerButton.setForeground(Color.WHITE);
        buttonPanel.add(registerButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);

        backgroundLabel.add(buttonPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            // Connect to the database and register the new user
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/fir_management_system", "root", "Sanch@123");

                String username = usernameField.getText();
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, password);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                    dispose(); // Close the register page
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                } else {
                	JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
                	}
                con.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == backButton) {
            // Close the register page and go back to the login page
            dispose();
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
        }
    }

    public static void main(String[] args) {
        RegisterPage registerPage = new RegisterPage();
    }
}

