package sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Difficulty extends JFrame {

    public static int frameSize = 500;

    protected JFrame mainFrame;
    private JButton returnButton, easy, medium, hard;
    private JLabel title;
    protected MainMenu mainMenu;
    private String difficulty = "MEDIUM";

    public Difficulty() {
        // Creating mainframe and panel
        mainFrame = new JFrame("Set Difficulty");
        mainFrame.setPreferredSize(new Dimension(frameSize, frameSize));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel controlPanel = new JPanel();
        // controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        controlPanel.setOpaque(false);

        JPanel titlePanel = new JPanel();
        // titlePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titlePanel.setOpaque(false);

        // JPanel topPanel = new JPanel();
        // topPanel.setOpaque(false);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(85, 50, 0, 25));

        // Background image
        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);

        // switch screen instane
        SwitchScreens switchingScreen = new SwitchScreens();

        //Toggle difficulty instance
        ToggleDifficulty toggle = new ToggleDifficulty();

        // Initializing attributes
        title = new JLabel("Set Difficulty");
        title.setOpaque(false);
        returnButton = new JButton("Return to Menu");
        returnButton.addActionListener(switchingScreen);

        // Creating difficulty toggle
        JPanel difficultyPanel = new JPanel();
        easy = new JButton("Easy");
        medium = new JButton("Medium");
        hard = new JButton("Hard");
        easy.addActionListener(toggle);
        medium.addActionListener(toggle);
        hard.addActionListener(toggle);
        difficultyPanel.add(easy);
        difficultyPanel.add(medium);
        difficultyPanel.add(hard);

        // Editing buttons and title
        title.setFont(new Font("Chiller", Font.BOLD, 50));
        title.setOpaque(false);

        // Adding attributes to panel
        titlePanel.add(title);
        controlPanel.add(returnButton);

        // titlePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        // controlPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

        // moving panel

        // Adding panel to mainframe and making mainframe visible
        // imagePanel.add(topPanel);
        imagePanel.add(titlePanel, BorderLayout.CENTER);
        imagePanel.add(difficultyPanel, BorderLayout.CENTER);
        imagePanel.add(controlPanel, BorderLayout.CENTER);
        mainFrame.add(imagePanel, BorderLayout.CENTER);
        mainFrame.setSize(400, 400);
        mainFrame.setResizable(false);
        mainFrame.pack();
    }
    public void addMainMenu(MainMenu MainMenu){
        this.mainMenu = MainMenu;

    }

    public String getDifficulty() {
        return difficulty;
    }

    public class SwitchScreens implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == returnButton) {
                System.out.println(difficulty);
                // returning to home
                System.out.println("return was clicked");
                mainFrame.setVisible(false);
                mainMenu.mainFrame.setVisible(true);
            }
            
        }
    }

    public class ToggleDifficulty implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == easy){
                difficulty = "EASY";
                System.out.println("Difficulty: EASY");
            }

            if (event.getSource() == medium){
                difficulty = "MEDIUM";
                System.out.println("Difficulty: MEDIUM");
            }

            if (event.getSource() == hard){
                difficulty = "HARD";
                System.out.println("Difficulty: HARD");
            }
        }
    }
}
