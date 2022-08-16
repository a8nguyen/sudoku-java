
package sudoku.controller;
import java.util.HashSet;
import sudoku.model.*;

public interface SudokuUserInterface{
        public void eraseValue(int x, int y);
        public void changeRedSquare(HashSet<Square> squareList);
        public void endGame();
        public void colorRelevance(int row, int col);

}
