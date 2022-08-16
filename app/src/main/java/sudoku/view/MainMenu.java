package sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.*;
import sudoku.controller.*;

public class MainMenu extends JFrame {

	public static int frameSize = 500;

	protected JFrame mainFrame;
	private JButton playButton, difficultyButton, highScoresButton;
	private JLabel title;
	protected SudokuTable sudokuTable;
	protected HighScores highScores;
	protected Difficulty difficulty;

	public MainMenu() {
		// Creating mainframe and panel
		mainFrame = new JFrame("Sudoku");
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
		

		// switch screen instane
		SwitchScreens switchingScreen = new SwitchScreens();

		// Initializing attributes
		title = new JLabel("Sudoku!");
		title.setOpaque(false);
		playButton = new JButton("Play");
		playButton.addActionListener(switchingScreen);
		difficultyButton = new JButton("Difficulty");
		difficultyButton.addActionListener(switchingScreen);
		highScoresButton = new JButton("High Scores");
		highScoresButton.addActionListener(switchingScreen);

		// Editing buttons and title
		title.setFont(new Font("Chiller", Font.BOLD, 100));
		title.setOpaque(false);

		// Adding attributes to panel
		titlePanel.add(title);
		controlPanel.add(playButton);
		controlPanel.add(difficultyButton);
		controlPanel.add(highScoresButton);
		// titlePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		// controlPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

		// moving panel

		// Adding panel to mainframe and making mainframe visible
		// imagePanel.add(topPanel);
		imagePanel.add(titlePanel, BorderLayout.CENTER);
		imagePanel.add(controlPanel, BorderLayout.CENTER);
		mainFrame.add(imagePanel, BorderLayout.CENTER);
		mainFrame.setSize(400, 400);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	public void addSudokuTable(SudokuTable sudokuTable){
		this.sudokuTable = sudokuTable;
	}
	public void addHighScores(HighScores highScores){
		this.highScores = highScores;
	}
	public void addDifficulty(Difficulty difficulty){
		this.difficulty = difficulty;
	}

	public class SwitchScreens implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == playButton) {
				System.out.println("play was clicked");
				mainFrame.setVisible(false);
				PuzzleController gameController = new PuzzleController("EASY");
				sudokuTable.mainFrame.setVisible(true);
			}
			if (event.getSource() == highScoresButton) {
				System.out.println("highscores was clicked");
				mainFrame.setVisible(false);
				highScores.mainFrame.setVisible(true);
			}
			if (event.getSource() == difficultyButton) {
				System.out.println("difficulty was clicked");
				mainFrame.setVisible(false);
				difficulty.mainFrame.setVisible(true);
			}
		}
	}
}

