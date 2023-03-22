/** Represents an Easy SudokuBoard of varying size */
import java.util.*;

public class EasySudokuBoard implements SudokuBoard{
    /** Instance Variables */
    private Cell[] prototypes;
    private Cell[][] board;
    private int size;
    private int cellSize;
    private BoardCaretaker initialState = new BoardCaretaker();

    /** Constructor to build a sudoku board
    * @param size The size of the board 
    */
    public EasySudokuBoard(int size) {
        this.size = size;
        // Board size
        board = new Cell[size][size];
        this.cellSize = (int) Math.sqrt(size);
        // Build the board
        buildBoard();
    }

    /** Builds a sudoku board by completing a board, then removing elements */
    public void buildBoard() {
        // Initialize board to 0s
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = getPrototype(0);
            }
        }
        // Use backtracking to fill board
        fillBoard(0, 0);
        // Remove Elements
        removeElements();
    }

    /** Recurvise method that uses backtracking to fill a SudokuBoard with a valid puzzle
     * @return True once complete, false to backtrack
    */
    private boolean fillBoard(int row, int col) {
        // Base Case, Board Filled
        if (row == size) {
            return true;
        }

        // If we have reached the end of a row, move to the next row
        if (col == size) {
            return fillBoard(row + 1, 0);
        }

        // If the current col is already filled, move to the next col
        if (board[row][col].getValue() != 0) {
            return fillBoard(row, col + 1);
        }

        // Try values from 1 to size in the current cell
        Random rand = new Random();
        for (int i = 1; i <= size; i++) {
            int value = rand.nextInt(size) + 1;
            if (isInputValid(row, col, value)) {
                board[row][col] = getPrototype(value);
                if (fillBoard(row, col + 1)) {
                    return true;
                }
            }
        }
        // If no value worked, reset the cell and backtrack
        board[row][col] = getPrototype(0);
        return false;
    }

    /** Removes elements from the completed Puzzle */
    private void removeElements(){
        ArrayList<Cell> cells = new ArrayList<Cell>(size*size);;
        for(int row = 0; row < size; row++){
            for(int col=0; col < size; col++){
                cells.add(board[row][col]);
            }
        }
        int removeTotal = (int) (size*size * 0.4);
        Collections.shuffle(cells);
        for(int i = 0; i < removeTotal; i++){
            Cell cell = cells.get(i);
            cell.setValue(0);
        }
        createSnapshot();
    }

    /** Get Size of Board
     * @return The size of the SudokuBoard
     */
    public int getSize() {
        return size;
    }
    
    /** Get Size of Sub-Grids
     * @return Sub Grid size
     */
    public int getCellSize() {
        return cellSize;
    }
    
    /** Get Specific cells
     * @param row The row of the cell
     * @param col The col of the cell
     * @return The cell at the given row and col
     */
    public Cell getCell(int row, int col){
        return board[row][col];
    }
    
    /** Get Specific cells value
     * @param row The row of the cell
     * @param col The col of the cell
     * @return The value of the cell at the given row and col
     */
    public int getCellValue(int row, int col){
        return board[row][col].getValue();
    }

    /** Memento method to create a snapshot of the board */
    public void createSnapshot(){
        this.initialState.setState(new BoardMemento(board));
    }

    /** Restores the board based on the snapshot */
    public void restore(){
        // Retrieve the previous state of the board
        Cell[][] previousState = initialState.getState().getMemento();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = previousState[i][j].clone();
            }
        }
    }

    /** Gets a prototype of a Cell with the given value, or creates a new prototype 
     * @param value The value needed for a cell
     * @return A cell that is either a clone, or a new cell
    */
    private Cell getPrototype(int value) {
        if (prototypes == null) {
            prototypes = new Cell[size];
            prototypes[0] = new Cell(0);
        }
        if(value == 0){
            return prototypes[0].clone();
        }
        if (prototypes[value - 1] == null) {
            prototypes[value - 1] = new Cell(value);
        }
        return prototypes[value - 1].clone();
    }
}