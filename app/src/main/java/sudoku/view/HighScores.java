package sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.*;

public class HighScores extends JFrame /*implements java.io.Serializable*/{

    public static int frameSize = 500;

    public int score; //serializable

    protected JFrame mainFrame;
    private JButton returnButton;
    private JLabel title;
    protected MainMenu mainMenu;
    protected SudokuTable sudokuTable;

    public HighScores() {
        // Creating mainframe and panel
        //this.score = score;
        mainFrame = new JFrame("High scores");
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

        // Panel for highscores
        JPanel highScorePanel = new JPanel();
        // implement adding highscores
        /*if (sudokuTable.endGame()){
            score += 1;
        }*/

        // Background image
        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);

        // Initializing attributes
        title = new JLabel("Records");
        title.setOpaque(false);
        returnButton = new JButton("Return to Menu");
        SwitchScreens switchingScreen = new SwitchScreens();
        returnButton.addActionListener(switchingScreen);

        // Editing buttons and title
        title.setFont(new Font("Chiller", Font.BOLD, 50));
        title.setOpaque(false);

        // Adding attributes to panel
        titlePanel.add(title);
        controlPanel.add(returnButton);

        // Adding highscores

        // Adding panel to mainframe and making mainframe visible
        // imagePanel.add(topPanel);
        imagePanel.add(titlePanel, BorderLayout.CENTER);
        imagePanel.add(highScorePanel, BorderLayout.CENTER);
        imagePanel.add(controlPanel, BorderLayout.CENTER);
        mainFrame.add(imagePanel, BorderLayout.CENTER);
        mainFrame.setSize(500, 500);
        mainFrame.setResizable(false);
        mainFrame.pack();

    }
    public void addMainMenu(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }
    public void addSudokuTable(SudokuTable sudokuTable){
        this.sudokuTable = sudokuTable;
    }

    public class SwitchScreens implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == returnButton) {
                System.out.println("return was clicked");
                mainFrame.setVisible(false);
                mainMenu.mainFrame.setVisible(true);
            }
        }
    }
}