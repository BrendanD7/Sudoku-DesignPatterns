/** Represents a cell within a SudokuBoard */
public class Cell implements CellPrototype{
    /** Instance variables */
    private int value;

    /** Cell Constructor, creates a Cell object
     * @param value The value to be held in the cell
     */
    public Cell(int value) {
        this.value = value;
    }

    /** Constructor to create a cell given another cell, used for clone
     * @param cell The cell to be copied
     */
    public Cell(Cell cell){
        this.value = cell.getValue();
    }
    
    /** Retrieve the value of the cell
     * @return The value of the cell
     */
    public int getValue() {
        return value;
    }

    /** Set the value of a cell
     * @param value The value to be set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /** Prototype method to clone a cell */
    public Cell clone(){
        return new Cell(this);
    }
}