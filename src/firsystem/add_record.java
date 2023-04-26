package firsystem;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class add_record extends JFrame {
    public add_record() {
        setTitle("FIR Registration Page");
        getContentPane().setBackground(Color.BLACK);

        JLabel l8 =new JLabel(" FIR REGISTRATION PAGE ");
        l8.setBounds(700,20,200,100);
        l8.setForeground(Color.WHITE);
        add(l8);

        JLabel l1 = new JLabel("Victim NAME:");
        l1.setBounds(600,100,200,40);
        l1.setForeground(Color.WHITE);
        add(l1);
        JTextField t1 = new JTextField();
        t1.setBounds(700,100,200,30);
        add(t1);

        JLabel l2 = new JLabel("DATE OF BIRTH OF VICTIM:");
        l2.setBounds(535,200,200,40);
        l2.setForeground(Color.WHITE);
        add(l2);
        JTextField t2 = new JTextField();
        t2.setBounds(700,200,200,30);
        add(t2);

        JLabel l3 = new JLabel("FIR NO:");
        l3.setBounds(600,300,200,40);
        l3.setForeground(Color.WHITE);
        add(l3);
        JTextField t3 = new JTextField();
        t3.setBounds(700,300,200,30);
        add(t3);

        JLabel l5 = new JLabel("GENDER:");
        l5.setBounds(600,400,200,40);
        l5.setForeground(Color.WHITE);
        add(l5);
        JCheckBox t5 = new JCheckBox("MALE");
        t5.setBounds(700,400,200,30);
        add(t5);
        JCheckBox t6 = new JCheckBox("FEMALE");
        t6.setBounds(700,440,200,30);
        add(t6);

        JLabel l7 = new JLabel("ACCUSED NAME ");
        l7.setBounds(600,500,200,40);
        l7.setForeground(Color.WHITE);
        add(l7);
        JTextField t7 = new JTextField();
        t7.setBounds(700,500,200,30);
        add(t7);

        JLabel l4 = new JLabel("CRIME COMMITED:");
        l4.setBounds(580,570,200,60);
        l4.setForeground(Color.WHITE);
        add(l4);
        JTextArea t4 = new JTextArea();
        t4.setBounds(700,570,200,50);
        add(t4);

        JButton b1 = new JButton(" ADD FIR RECORD ");
        b1.setBounds(600,670,200,20);
        add(b1);
        JButton backButton = new JButton("Back");
        backButton.setBounds(800,670,200,20);
        add(backButton);
        setSize(1920,1080);
        setLayout(null);
        setVisible(true);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String victimName = t1.getText();
                String dob = t2.getText();
                String firNo = t3.getText();
                String gender = t5.isSelected() ? "MALE" : "FEMALE";
                String accusedName = t7.getText();
                String crimeCommitted = t4.getText();

                // Encrypt user input using AES encryption
                try {
                    String secretKey = "mySecretKey12345";
                    byte[] key = secretKey.getBytes();
                    SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                    byte[] encryptedVictimName = cipher.doFinal(victimName.getBytes());
                    byte[] encryptedDOB = cipher.doFinal(dob.getBytes());
                    byte[] encryptedFIRNo = cipher.doFinal(firNo.getBytes());
                    byte[] encryptedGender = cipher.doFinal(gender.getBytes());
                    byte[] encryptedAccusedName = cipher.doFinal(accusedName.getBytes());
                    byte[] encryptedCrimeCommitted = cipher.doFinal(crimeCommitted.getBytes());
                    // Encode the encrypted data in base64 format and print to console
                    String encodedVictimName = Base64.getEncoder().encodeToString(encryptedVictimName);
                    String encodedDOB = Base64.getEncoder().encodeToString(encryptedDOB);
                    String encodedFIRNo = Base64.getEncoder().encodeToString(encryptedFIRNo);
                    String encodedGender = Base64.getEncoder().encodeToString(encryptedGender);
                    String encodedAccusedName = Base64.getEncoder().encodeToString(encryptedAccusedName);
                    String encodedCrimeCommitted = Base64.getEncoder().encodeToString(encryptedCrimeCommitted);

                    System.out.println("Victim Name: " + encodedVictimName);
                    System.out.println("DOB: " + encodedDOB);
                    System.out.println("FIR No: " + encodedFIRNo);
                    System.out.println("Gender: " + encodedGender);
                    System.out.println("Accused Name: " + encodedAccusedName);
                    System.out.println("Crime Committed: " + encodedCrimeCommitted);

                    try {
                        // Create a file to store the encrypted data
                        File file = new File("encrypted_data.txt");
                        file.createNewFile();

                        // Write the encrypted data to the file
                        FileWriter writer = new FileWriter(file);
                        writer.write("Victim Name: " + encodedVictimName + "\n");
                        writer.write("DOB: " + encodedDOB + "\n");
                        writer.write("FIR No: " + encodedFIRNo + "\n");
                        writer.write("Gender: " + encodedGender + "\n");
                        writer.write("Accused Name: " + encodedAccusedName + "\n");
                        writer.write("Crime Committed: " + encodedCrimeCommitted + "\n");
                        writer.close();

                        System.out.println("Encrypted data written to file.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose();

            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        add_record add = new add_record();

    }
}


