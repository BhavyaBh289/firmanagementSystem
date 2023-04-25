package firsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Decryptor {
    public static void main(String[] args) throws Exception {
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

        // Print the decrypted data
        System.out.println("Victim Name: " + victimName);
        System.out.println("DOB: " + dob);
        System.out.println("FIR No: " + firNo);
        System.out.println("Gender: " + gender);
        System.out.println("Accused Name: " + accusedName);
        System.out.println("Crime Committed: " + crimeCommitted);
    }
}

