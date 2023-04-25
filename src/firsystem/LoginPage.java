package firsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {

	;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;
    private JLabel titleLabel, usernameLabel, passwordLabel, backgroundLabel;

    public LoginPage() {
        super("Login Page");
        setSize(1060, 838);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add background image
        ImageIcon background = new ImageIcon("fir.jpeg");
        backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds (0,0,1060, 838);
        add(backgroundLabel);

        titleLabel = new JLabel("Welcome back!");
        
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        backgroundLabel.add(titleLabel);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setOpaque(false);
        inputPanel.setBounds(150, 100, 300, 100);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        inputPanel.add(usernameLabel);
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        inputPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        
     // Set horizontal alignment to center for labels, text fields, and password field
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setHorizontalAlignment(JTextField.CENTER);


        backgroundLabel.add(inputPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(200, 220, 200, 50);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.WHITE);
        buttonPanel.add(loginButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);

        backgroundLabel.add(buttonPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Connect to the database and check the user's credentials
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/fir_management_system", "root", "");

                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM users WHERE username=? AND password=?");
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    dispose(); // Close the login page
                    Dashboard dashboard = new Dashboard();
                    dashboard.setVisible(true); // Show the main page
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password!");
                }

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Login failed!");
            }
        } else if (e.getSource() == backButton) {
            dispose(); // Close the login page
           // WelcomePage welcomePage = new WelcomePage();
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true); // Show the welcome page
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginPage loginPage = new LoginPage();
       
    }
}

