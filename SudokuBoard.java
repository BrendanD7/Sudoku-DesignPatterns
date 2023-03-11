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
        Random rand = new Random();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(rand.nextInt(10) < 3){
                    int value = rand.nextInt(size)+1;
                    boolean valid = isInputValid(row, col, value);
                    while(!valid){
                        value = rand.nextInt(size)+1;
                        valid = isInputValid(row, col, value);
                    }
                    board[row][col] = new Cell(value);
                }
                else{
                    board[row][col] = new Cell(0);
                }
            }
        }
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

    public boolean isBoardValid(){
        for(int i = 0; i < size; i++){
            boolean checkRow = isRowValid(i);
            if(!checkRow){
                return false;
            }
            boolean checkCol = isColValid(i);
            if(!checkCol){
                return false;
            }
        }
        if(!isSubGridValid()){
            return false;
        }
        return true;
    }

    public boolean isSubGridValid(){
        for(int i = 0; i < size; i += cellSize){
            for(int j = 0; j < size; j += cellSize){
                boolean[] visited = new boolean[size];
                for(int row = i; row < i + cellSize; row++){
                    for(int col = j; col < j + cellSize; col++){
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
                }
            }
        }
        return true;
    }

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
}
