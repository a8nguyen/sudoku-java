package sudoku.view;
import javax.swing.*;
import sudoku.model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.*;
import java.util.ArrayList;


public class PuzzlePanel extends JPanel{

    private JPanel[][] matrixPanel;
    private Cell [][] cellMatrix;
    private int dimension;
    private int width;


    public PuzzlePanel(int dim, int width)
    {

        this.width = width;
        this.dimension = dim;
        int dim_squared = this.dimension * this.dimension;
        this.matrixPanel = new JPanel[dim][dim];
        this.cellMatrix = new Cell[dim][dim];
        this.setLayout(new GridLayout(dim, dim));
        this.setPreferredSize(new Dimension ((this.width*8/10), (this.width*8/10)));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        for(int row = 0; row < dim; row++){
            for(int col=0; col < dim; col ++){
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension((width*8/10)/dim_squared, (width*8/10)/dim_squared));
                panel.setLayout(new GridLayout(dim, dim));
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                this.matrixPanel[row][col] = panel;
            }
        }
        for(int row = 0; row < dim; row++){
            for(int col=0; col < dim; col ++){
                this.add(this.matrixPanel[row][col], BorderLayout.CENTER);
            }
        }
    }
    public void add(ArrayList<Square> squares, int rowPos, int colPos){
        int dim_squared = this.dimension * this.dimension;
        
        for (int order=0; order < dim_squared; order++ ){

            Square square = squares.get(order);
            Cell cellTextField = new Cell(square, this.dimension);
            square.addCellInterface(cellTextField);

            if ( square.getValue() != 0){
                cellTextField.setForeground(Color.DARK_GRAY);
                cellTextField.setEditable(false);
                cellTextField.setText(Integer.toString(square.getValue()));
            }
            else{

                cellTextField.addHighlighter();
            }
            this.cellMatrix[rowPos][colPos] = cellTextField;
            this.matrixPanel[rowPos][colPos].add(cellTextField);

        }
    }

}
