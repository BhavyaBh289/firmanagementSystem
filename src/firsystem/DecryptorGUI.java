package firsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DecryptorGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField victimNameField, dobField, firNoField, genderField, accusedNameField, crimeCommittedField;
    private JButton decryptButton,backButton;

    public DecryptorGUI() {
        // Initialize components
        victimNameField = new JTextField(20);
        dobField = new JTextField(20);
        firNoField = new JTextField(20);
        genderField = new JTextField(20);
        accusedNameField = new JTextField(20);
        crimeCommittedField = new JTextField(20);
        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String secretKey = "mySecretKey12345"; // Replace with your own secret key

                    // Read the encrypted data from the file
                    File file = new File("encrypted_data.txt");
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String encodedVictimName = reader.readLine().substring(13);
                    String encodedDOB = reader.readLine().substring(5);
                    String encodedFIRNo = reader.readLine().substring(8);
                    String encodedGender = reader.readLine().substring(8);
                    String encodedAccusedName = reader.readLine().substring(14);
                    String encodedCrimeCommitted = reader.readLine().substring(17);
                    reader.close();

                    // Decode the Base64 encoded data
                    byte[] encryptedVictimName = Base64.getDecoder().decode(encodedVictimName);
                    byte[] encryptedDOB = Base64.getDecoder().decode(encodedDOB);
                    byte[] encryptedFIRNo = Base64.getDecoder().decode(encodedFIRNo);
                    byte[] encryptedGender = Base64.getDecoder().decode(encodedGender);
                    byte[] encryptedAccusedName = Base64.getDecoder().decode(encodedAccusedName);
                    byte[] encryptedCrimeCommitted = Base64.getDecoder().decode(encodedCrimeCommitted);

                    // Decrypt the data using AES decryption
                    SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                    String victimName = new String(cipher.doFinal(encryptedVictimName));
                    String dob = new String(cipher.doFinal(encryptedDOB));
                    String firNo = new String(cipher.doFinal(encryptedFIRNo));
                    String gender = new String(cipher.doFinal(encryptedGender));
                    String accusedName = new String(cipher.doFinal(encryptedAccusedName));
                    String crimeCommitted = new String(cipher.doFinal(encryptedCrimeCommitted));

                    // Set the decrypted data to the text fields
                    victimNameField.setText(victimName);
                    dobField.setText(dob);
                    firNoField.setText(firNo);
                    genderField.setText(gender);
                    accusedNameField.setText(accusedName);
                    crimeCommittedField.setText(crimeCommitted);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Dashboard dashboard = new Dashboard();
            	dashboard.setVisible(true); 
                frame.dispose(); 
                
            }
        });

        // Create panel and add components
        panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Victim Name:"));
        panel.add(victimNameField);
        // Add the date of birth field and its corresponding text field
        panel.add(new JLabel("Date of Birth:"));
        panel.add(dobField);

        // Add the FIR number field and its corresponding text field
        panel.add(new JLabel("FIR Number:"));
        panel.add(firNoField);

        // Add the gender field and its corresponding text field
        panel.add(new JLabel("Gender:"));
        panel.add(genderField);

        // Add the accused name field and its corresponding text field
        panel.add(new JLabel("Accused Name:"));
        panel.add(accusedNameField);

        // Add the crime committed field and its corresponding text field
        panel.add(new JLabel("Crime Committed:"));
        panel.add(crimeCommittedField);

        // Add the decrypt button
        panel.add(new JLabel(""));
        panel.add(decryptButton);
        
        panel.add(new JLabel(""));
        panel.add(backButton);

        // Create the frame and add the panel to it
        frame = new JFrame("Decryptor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        DecryptorGUI decryptorGUI = new DecryptorGUI();
    }
} 
