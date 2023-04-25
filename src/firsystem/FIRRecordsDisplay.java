
    package firsystem;

    import javax.swing.*;
    import java.awt.Color;
    import javax.crypto.Cipher;
    import javax.crypto.spec.SecretKeySpec;
    import java.util.Base64;
    import java.util.ArrayList;

    public class FIRRecordsDisplay {
        private static final String SECRET_KEY = "mySecretKey12345"; // Replace with your own secret key

        public static void main(String[] args) {
            JFrame f = new JFrame();
            f.setTitle("FIR Records Display");
            f.getContentPane().setBackground(Color.WHITE);

            JLabel l1 = new JLabel("FIR Records");
            l1.setBounds(10, 10, 100, 20);
            f.add(l1);

            JTextArea t1 = new JTextArea();
            t1.setBounds(10, 40, 400, 400);
            t1.setEditable(false);
            f.add(t1);

            JButton b1 = new JButton("Refresh");
            b1.setBounds(10, 460, 80, 20);
            f.add(b1);

            JButton b2 = new JButton("Back");
            b2.setBounds(100, 460, 80, 20);
            f.add(b2);

            f.setSize(440, 530);
            f.setLayout(null);
            f.setVisible(true);

            // Add action listener for the "Refresh" button
            b1.addActionListener(e -> {
                // Decrypt and display all FIR records
                try {
                    ArrayList<String[]> firRecords = new ArrayList<String[]>();
                    // Replace with code to read FIR records from a file or database
                    firRecords.add(new String[] { "encryptedVictimName1", "encryptedDOB1", "encryptedFIRNo1", "encryptedGender1", "encryptedAccusedName1", "encryptedCrimeCommitted1" });
                    firRecords.add(new String[] { "encryptedVictimName2", "encryptedDOB2", "encryptedFIRNo2", "encryptedGender2", "encryptedAccusedName2", "encryptedCrimeCommitted2" });
                    firRecords.add(new String[] { "encryptedVictimName3", "encryptedDOB3", "encryptedFIRNo3", "encryptedGender3", "encryptedAccusedName3", "encryptedCrimeCommitted3" });

                    t1.setText(""); // Clear previous FIR records
                    for (String[] firRecord : firRecords) {
                        String victimName = decrypt(firRecord[0]);
                        String dob = decrypt(firRecord[1]);
                        String firNo = decrypt(firRecord[2]);
                        String gender = decrypt(firRecord[3]);
                        String accusedName = decrypt(firRecord[4]);
                        String crimeCommitted = decrypt(firRecord[5]);

                        t1.append("Victim Name: " + victimName + "\n");
                        t1.append("DOB: " + dob + "\n");
                        t1.append("FIR No: " + firNo + "\n");
                        t1.append("Gender: " + gender + "\n");
                        t1.append("Accused Name: " + accusedName + "\n");
                        t1.append("Crime Committed: " + crimeCommitted + "\n");
                        t1.append("\n");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            // Add action listener for the "Back" button
            b2.addActionListener(e -> {
                 // Close the FIR Records Display window
            	Dashboard dashboard = new Dashboard();
            	dashboard.setVisible(true);
            	f.dispose();// Replace with code to go back to the previous window
            });
        }

    private static String decrypt(String encryptedText) throws Exception {
        byte[] key = SECRET_KEY.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedEncryptedText = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedTextBytes = cipher.doFinal(decodedEncryptedText);
        return new String(decryptedTextBytes);
    }
}

