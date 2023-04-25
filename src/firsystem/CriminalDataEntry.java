package firsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class CriminalDataEntry extends JFrame {
    private JLabel lblName, lblAge, lblAddress, lblCrimeType, lblDescription;
    private JTextField txtName, txtAge, txtAddress;
    private JComboBox<String> cmbCrimeType;
    private JTextArea txtDescription;
    private JButton btnSave, btnCancel;

    public CriminalDataEntry() {
        setTitle("Criminal Data Entry");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new GridLayout(6, 2));
        c.setBackground(Color.BLACK);

        lblName = new JLabel("Name: ");
        lblName.setForeground(Color.WHITE);
        txtName = new JTextField();
        c.add(lblName);
        c.add(txtName);

        lblAge = new JLabel("Age: ");
        lblAge.setForeground(Color.WHITE);
        txtAge = new JTextField();
        c.add(lblAge);
        c.add(txtAge);

        lblAddress = new JLabel("Address: ");
        lblAddress.setForeground(Color.WHITE);
        txtAddress = new JTextField();
        c.add(lblAddress);
        c.add(txtAddress);

        lblCrimeType = new JLabel("Crime Type: ");
        lblCrimeType.setForeground(Color.WHITE);
        cmbCrimeType = new JComboBox<String>();
        cmbCrimeType.addItem("Robbery");
        cmbCrimeType.addItem("Murder");
        cmbCrimeType.addItem("Assault");
        c.add(lblCrimeType);
        c.add(cmbCrimeType);

        lblDescription = new JLabel("Description: ");
        lblDescription.setForeground(Color.WHITE);
        txtDescription = new JTextArea();
        c.add(lblDescription);
        c.add(txtDescription);

        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        c.add(btnSave);
        c.add(btnCancel);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Establish a JDBC connection
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fir_management_system","root","Sanch@123");

                    // Create a statement object
                    PreparedStatement pst = con.prepareStatement("INSERT INTO criminals (name, age, address, crime_type, description) VALUES (?, ?, ?, ?, ?)");
                    pst.setString(1, txtName.getText());
                    pst.setInt(2, Integer.parseInt(txtAge.getText()));
                    pst.setString(3, txtAddress.getText());
                    pst.setString(4, cmbCrimeType.getSelectedItem().toString());
                    pst.setString(5, txtDescription.getText());

                    int rowsInserted = pst.executeUpdate();

                    if(rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Criminal data saved successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Unable to save criminal data");
                    }

                    con.close();

                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        CriminalDataEntry criminalDataEntry = new CriminalDataEntry();
        criminalDataEntry.setVisible(true);
    }
}

