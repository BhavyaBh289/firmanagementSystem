package firsystem;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;

public class Map extends JFrame {
    private JLabel imageLabel;
    private JScrollPane scrollPane;
    private BufferedImage image;
    private double scale = 1.0;

    public Map() {
        setTitle("Image Zoom GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Load the image from file
        ImageIcon icon = new ImageIcon("map.jpg");
        image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        // Create a label to hold the image
        imageLabel = new JLabel(new ImageIcon(image));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to the dashboard page
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose(); // Close the current window
            }
        });

        // Add the back button to the top of the GUI
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        // Create a scroll pane to allow scrolling and zooming
        scrollPane = new JScrollPane(imageLabel);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Add a mouse listener to handle zooming
        imageLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Zoom in on left-click
                    scale += 0.1;
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    // Zoom out on right-click
                    scale -= 0.1;
                }
                imageLabel.setIcon(new ImageIcon(image.getScaledInstance((int) (image.getWidth() * scale), (int) (image.getHeight() * scale), Image.SCALE_SMOOTH)));
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Map gui = new Map();
        gui.setVisible(true);
    }
}
