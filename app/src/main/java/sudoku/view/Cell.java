package sudoku.view;
import sudoku.controller.*;
import sudoku.model.*;
import javax.swing.*;
import java.lang.NumberFormatException;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.*;

public class Cell extends JTextField implements CellInterface, FocusListener{
    int row;
    int col;
    PuzzleController controller;
    KeyAdapter keyAdapter;
    Square square;
    boolean violation;
    int prevStepVal;
    boolean lastChanged;

    public Cell(Square square, int size ){
        super();
        this.square = square;
        this.row = square.getRowID();
        this.col = square.getColID();
        this.setForeground(Color.decode("#2c7de2"));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setFont(new Font("Symbola", Font.PLAIN, 90/size));
        this.setBorder( BorderFactory.createLineBorder(Color.BLACK, 1));
        this.violation = false;
        this.prevStepVal = 0;
        this.lastChanged = false;


        this.keyAdapter = (new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                if (!Character.isDigit(c)) {
                    e.consume(); // Stop the event from propagating.
                }
            }
        });

    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.controller != null){
            this.controller.userEditting(this.row, this.col);
        }
        this.setBackground(Color.decode("#bbdefb"));
    }


    @Override
    public void focusLost(FocusEvent e) {
        int numberEntered;
        String toPrint = this.getText();
        try{
            numberEntered = Integer.parseInt(toPrint);
        }
        catch (NumberFormatException exception){
            numberEntered = 0;
        }
        if (numberEntered == 0) {
            toPrint = "";
        }
        
        this.setBackground(Color.WHITE);
        if(this.controller != null){
            this.prevStepVal = this.square.getValue();
            if(this.controller.userEnteredValue(this.row, this.col, numberEntered)){
                this.setText(toPrint);
                this.lastChanged = true;
            }
        }
        
        //System.out.println("Calling from focus " + toPrint);

    }
    @Override
    public void addController(PuzzleController controller){
        this.controller = controller;
    }
    @Override
    public void eraseValue(){
        this.setText("");
        this.square.setValue(0);
    }
    
    @Override
    public void changeToInvalid(){
        this.setBackground(Color.decode("#f7cfd6"));
        if(this.getEditable()){
            this.setForeground(Color.decode("#d9251d"));
        }
    }
    @Override
    public void changeToRelevance(){
        this.setBackground(Color.decode("#e2ebf3"));

    }


    @Override
    public boolean getEditable(){
        return this.square.getEditable();
    }

    @Override
    public void setDefault() {
        this.setBackground(Color.WHITE);
        if(this.getEditable()){
            this.setForeground(Color.decode("#2c7de2"));
        }
            
    }
    @Override
    public int getPrevStep(){
        return this.prevStepVal;
    }
    @Override 
    public void setVal(int val){
        String toPrint = "";
        if (val == 0) {
            toPrint = "";
        }
        else{
            toPrint = String.valueOf(val);
        }

        this.setText(toPrint);
        this.square.setValue(val);
    }
    @Override
    public boolean getLastChange(){
        return this.lastChanged;

    }
    public void addHighlighter(){
        this.addKeyListener(this.keyAdapter);
        this.addFocusListener(this);
    }
    public void setViolation(boolean violation){
        this.violation = violation;
    }

    public boolean getViolation() {
        return this.violation;
    }



    

    
}
