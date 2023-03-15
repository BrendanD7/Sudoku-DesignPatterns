import java.util.Random;

public class SudokuBoard {
    private Cell[][] board;
    private int size;
    private int cellSize;

    /** Constructor to build a sudoku board */
    public SudokuBoard(int size) {
        // Board size
        board = new Cell[size][size];
        // Size for loops
        this.size = size;
        this.cellSize = (int) Math.sqrt(size);
        // Build the board
        buildBoard();
    }

    /** Builds a sudoku board */
    private void buildBoard() {
        // Initialize board to 0s
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new Cell(0);
            }
        }
        // Use backtracking to fill board
        fillBoard(0, 0);
        // Remove Elements
        removeElements();
    }

    /** Uses backtracking to fill the board */
    private boolean fillBoard(int row, int col) {
        // If we have filled the entire board, we are done
        if (row == size) {
            return true;
        }
        // If we have reached the end of a row, move to the next row
        if (col == size) {
            return fillBoard(row + 1, 0);
        }
        // If the current cell is already filled, move to the next cell
        if (board[row][col].getValue() != 0) {
            return fillBoard(row, col + 1);
        }
        // Try values from 1 to size in the current cell
        Random rand = new Random();
        for (int i = 1; i <= size; i++) {
            int value = rand.nextInt(size) + 1;
            if (isInputValid(row, col, value)) {
                board[row][col] = new Cell(value);
                if (fillBoard(row, col + 1)) {
                    return true;
                }
            }
        }
        // If no value worked, reset the cell and backtrack
        board[row][col] = new Cell(0);
        return false;
    }

    /* Prints the board */
    public void printBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col].getValue() + " ");
                if ((col + 1) % cellSize == 0 && col != size - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((row + 1) % cellSize == 0 && row != size - 1) {
                for (int i = 0; i < size + cellSize - 1; i++) {
                    System.out.print("- ");
                }
                System.out.println();
            }
        }
    }
    
    /** Checks if given row is valid */
    public boolean isRowValid(int row){
        boolean[] visited = new boolean[size];
        for(int col = 0; col < size; col++){
            int value = board[row][col].getValue();
            if(value != 0){
                if(visited[value-1]){
                    return false;
                }
                else{
                    visited[value-1] = true;
                }
            }
        }
        return true;
    }
    
    /** Checks if given column is valid */
    public boolean isColValid(int col){
        boolean[] visited = new boolean[size];
        for(int row = 0; row < size; row++){
            int value = board[row][col].getValue();
            if(value != 0){
                if(visited[value-1]){
                    return false;
                }
                else{
                    visited[value-1] = true;
                }
            }
        }
        return true;
    }
    
    public boolean isBoardValid() {
        for(int i = 0; i < size; i++) {
            if (!isRowValid(i) || !isColValid(i)) {
                return false;
            }
        }
        return isSubGridValid();
    }
    
    /** Checks if all subgrids are valid */
    public boolean isSubGridValid() {
        for (int i = 0; i < size; i += cellSize) {
            for (int j = 0; j < size; j += cellSize) {
                boolean[] visited = new boolean[size];
                for (int row = i; row < i + cellSize; row++) {
                    for (int col = j; col < j + cellSize; col++) {
                        int value = board[row][col].getValue();
                        if (value != 0) {
                            if (visited[value - 1]) {
                                return false;
                            } else {
                                visited[value - 1] = true;
                            }
                        }
                    }
                }
                if (!checkVisited(visited)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /** Helpe method for isSubGridValid */
    private boolean checkVisited(boolean[] visited) {
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
    
    /** Check if given input is valid */
    public boolean isInputValid(int row, int col, int input) {
        // Check if input is already in row or column
        for (int i = 0; i < size; i++) {
            if (board[row][i] != null && board[row][i].getValue() == input) {
                return false;
            }
            if (board[i][col] != null && board[i][col].getValue() == input) {
                return false;
            }
        }
    
        // Check if input is already in subgrid
        int subgridRow = (row / cellSize) * cellSize;
        int subgridCol = (col / cellSize) * cellSize;
        for (int i = subgridRow; i < subgridRow + cellSize; i++) {
            for (int j = subgridCol; j < subgridCol + cellSize; j++) {
                if (board[i][j] != null && board[i][j].getValue() == input) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Removes elements from the completed Puzzle */
    public void removeElements(){
        Random rand = new Random();
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                int random = rand.nextInt(10);
                if(random < 7){board[row][col].setValue(0);}
            }
        }
    }

    /** Get Size of Board */
    public int getSize() {
        return size;
    }
    
    /** Get Size of Sub-Grids */
    public int getCellSize() {
        return cellSize;
    }
    
    /** Get Specific cells */
    public Cell getCell(int row, int col){
        return board[row][col];
    }
    
    //* Get Specific Cells Values */
    public int getCellValue(int row, int col){
        return board[row][col].getValue();
    }
}