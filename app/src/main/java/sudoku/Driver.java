package sudoku;
import java.util.ArrayList;
import sudoku.view.*;
import sudoku.controller.*;
import sudoku.model.*;
import java.io.*;

public class Driver /*implements java.io.Serializable*/ {

  /*  public static HighScores loadFromFile(String filename){
        HighScores highScores = null;
        highScores.score = 0;

        try
        {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            highScores = (HighScores)ois.readObject();
        }
        catch (IOException e){
            System.out.println(e);
        }

        catch (ClassNotFoundException e){
            System.out.println(e);
        }
        return highScores;
    }*/

    public static void main(String[] args){


    //view: all the frame
	MainMenu menu = new MainMenu();
    HighScores highScores = new HighScores();
    Difficulty difficulty = new Difficulty();


    //gameController:
    String gameDifficulty = difficulty.getDifficulty();
    PuzzleController gameController = new PuzzleController(gameDifficulty);
    System.out.println("The difficulty is: " + gameDifficulty);

    SudokuTable sudokuTable = new SudokuTable(gameController.getBoard());
    //sudokuTable.setFrameVisible(true);


        //linking frames together
    menu.addSudokuTable(sudokuTable);
    menu.addHighScores(highScores);
    menu.addDifficulty(difficulty);
    highScores.addMainMenu(menu);
    difficulty.addMainMenu(menu);
    sudokuTable.addMainMenu(menu);
    gameController.addUserInterface(sudokuTable);
    gameController.startGame();


   /* int score = highScores.score;
    String filename = "scores.txt";

    try {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(highScores);
        oos.flush();
        oos.close();
        System.out.println("Object has been serialized");
    }
    catch (IOException e)
    {
        System.out.println(e);
    }*/


    }
}

/* TODO So what we are missing
    [] implement Hint
    [] implemen clearBoard
*/