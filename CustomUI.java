import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CustomUI extends JFrame {

    private JTextArea textArea;
    private JPanel mainPanel;

    public CustomUI() {
        // Set up the frame
        setTitle("Custom UI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.GREEN);
        setContentPane(mainPanel);

        // Create the text area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);

        // Create menu items
        JMenuItem menuItem1 = new JMenuItem("Show Date and Time");
        JMenuItem menuItem2 = new JMenuItem("Save to Log");
        JMenuItem menuItem3 = new JMenuItem("Change Background Color");
        JMenuItem menuItem4 = new JMenuItem("Exit");

        // Add menu items to menu
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);

        // Add menu bar to frame
        setJMenuBar(menuBar);

        // Action listeners for menu items
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                textArea.append("Current Date and Time: " + formatter.format(date) + "\n");
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                    writer.write(textArea.getText());
                    writer.newLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                float hue = 0.25f + rand.nextFloat() * 0.25f; // Ensures hue is between 0.25 and 0.50 for green shades
                Color randomGreen = Color.getHSBColor(hue, 0.7f, 0.8f); // Adjusting saturation and brightness for better visual
                mainPanel.setBackground(randomGreen);
                menuItem3.setText(String.format("Change Background Color (Hue: %.2f)", hue));
            }
        });

        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomUI().setVisible(true);
            }
        });
    }
}
