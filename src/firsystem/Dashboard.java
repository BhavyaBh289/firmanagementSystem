package firsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import firsystem.Main;

public class Dashboard extends JFrame {
	private JButton registerButton, receiveButton, mapButton, aboutUsButton, historyButton, exitButton;

    public Dashboard() {
        super("Dashboard Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Add a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBackground(Color.BLACK);

        // Create and add the buttons with icons
     
     // Create and add the buttons with icons
        ImageIcon registerIcon = new ImageIcon("list.jpg");
        registerButton = new JButton(registerIcon);
        registerButton.setToolTipText("About the project");
        registerButton.setPreferredSize(new Dimension(150, 149));
        registerButton.setBackground(Color.WHITE);
        
        buttonPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == registerButton) {
                    Main main = new Main();
                    dispose(); // close the current window
                }
            }
        });
        
        
        

        ImageIcon receiveIcon = new ImageIcon("decrypt.jpg");
        receiveButton = new JButton(receiveIcon);
        receiveButton.setToolTipText("Decrypt FIR");
        receiveButton.setPreferredSize(new Dimension(150, 150));
        receiveButton.setBackground(Color.WHITE);
        buttonPanel.add(receiveButton);
        receiveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == receiveButton) {
                	
                	DecryptorGUI decryptorGUI = new DecryptorGUI();
                	
                    //dispose(); // close the current window
                }
            }
        });

        ImageIcon mapIcon = new ImageIcon("map.jpg");
        mapButton = new JButton(mapIcon);
        mapButton.setToolTipText("Map");
        mapButton.setPreferredSize(new Dimension(150, 150));
        mapButton.setBackground(Color.WHITE);
        buttonPanel.add(mapButton);
        mapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mapButton) {
                    Map gui = new Map();
                    gui.setVisible(true);
                    dispose(); // close the current window
                }
            }
        });

        ImageIcon aboutUsIcon = new ImageIcon("data.jpg");
        aboutUsButton = new JButton(aboutUsIcon);
        aboutUsButton.setToolTipText("Criminal Data entry");
        aboutUsButton.setPreferredSize(new Dimension(150, 153));
        aboutUsButton.setBackground(Color.WHITE);
        buttonPanel.add(aboutUsButton);
        aboutUsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == aboutUsButton) {
                    CriminalDataEntry criminalDataEntry = new CriminalDataEntry();
                    criminalDataEntry.setVisible(true);
                    dispose();
                }
            }
        });
        ImageIcon historyButtonIcon = new ImageIcon("record.jpg");
        historyButton = new JButton(historyButtonIcon);
        historyButton.setToolTipText("History");
        historyButton.setPreferredSize(new Dimension(150, 149));
        historyButton.setBackground(Color.WHITE);
        buttonPanel.add(historyButton);
        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == historyButton) {
                	CriminalDetailsGUI frame = new CriminalDetailsGUI();
                    frame.setVisible(true);
                    dispose(); // close the current window
                }
            }
        });

        ImageIcon exitIcon = new ImageIcon("exit.png");
        exitButton = new JButton(exitIcon);
        exitButton.setToolTipText("Exit");
        exitButton.setPreferredSize(new Dimension(150, 150));
        exitButton.setBackground(Color.WHITE);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }


public static void main(String[] args) {
try {
UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
} catch (Exception e) {
e.printStackTrace();
}

Dashboard dashboard = new Dashboard();
dashboard.setVisible(true); // Show the dashboard page
}
}//
