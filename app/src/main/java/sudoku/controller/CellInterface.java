package sudoku.controller;

public interface CellInterface {
    public void eraseValue();
    public void addController(PuzzleController controller);
    public void changeToInvalid();
    public void changeToRelevance();
    public boolean getEditable();
    public void setDefault();
    public boolean getViolation();
    public void setViolation(boolean violation);
    public int getPrevStep();
    public void setVal(int val);
    public boolean getLastChange();
    
}
