/** Interface class which represents a SudokuBoard object
 *  Used to create SudokuBoards of varying size and difficulty
 */
public interface SudokuBoard{
    /** Creates a SudokuBoard object */
    public void buildBoard();

    /** Get the size of the sudoku board 
     * @return The size of the board
    */
    public int getSize();

    /** Get the value of a specific cell 
     * @param row The row of the cell
     * @param col The col of the cell
    */
    public int getCellValue(int row, int col);

    /** Retrieve a specific cell
     * @param row The row of the cell
     * @param col The col of the cell
     * @return The cell at the given row and col
     */
    public Cell getCell(int row, int col);

    /** Get the size of the sub-grids
     * @return Subgrid size
     */
    public int getCellSize();

    /**
     * Checks if the whole board is valid
     * @return - boolean indicating if the board is valid
     */
    default boolean isBoardValid() {
        // returns false if any row, column, or subgrid is invalid
        for(int i = 0; i < getSize(); i++) {
            RowIterator rowIt = new RowIterator(i, this);
            ColumnIterator colIt = new ColumnIterator(i, this);
            SubgridIterator subIt = new SubgridIterator(i, this);

            if (!isIteratorValid(rowIt) || !isIteratorValid(colIt) || !isIteratorValid(subIt)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given CellIterator is valid.
     * @param iterator - the CellIterator to check
     * @return - boolean indicating the validity of the CellIterator
     */
    private boolean isIteratorValid(CellIterator iterator) {
        boolean[] visited = new boolean[getSize()];

        while (iterator.hasNext()) {
            int value = iterator.next().getValue();
            if (value != 0) {
                if (visited[value-1]) {
                    return false;
                }

                visited[value-1] = true;
            }
        }

        return true;
    }

    /**
     * Checks if the given input is valid
     * @param row - the row of the input to check
     * @param col - the column of the input to check
     * @param input - the input number to check
     * @return - boolean indicating if the input is valid
     */
    default boolean isInputValid(int row, int col, int input) {
        if(input > getSize() || input <= 0){
            return false;
        }
        RowIterator rowIt = new RowIterator(row, this);
        ColumnIterator colIt = new ColumnIterator(col, this);
        // convert row and column numbers to subgrid number
        int subgrid = (col / getCellSize()) + row - (row % getCellSize());
        SubgridIterator subIt = new SubgridIterator(subgrid, this);

        // check if input is already in row, column, or subgrid
        // works in one loop since they're all the same size
        while (rowIt.hasNext()) {
            if (rowIt.next().getValue() == input) return false;
            if (colIt.next().getValue() == input) return false;
            if (subIt.next().getValue() == input) return false;
        }

        // if not, input is valid
        return true;
    }

    /** Method to create a snapshot of the SudokuBoard for the Memento Pattern */
    public void createSnapshot();

    /** Method to restore the state of the SudokuBoard, uses the snapshot from createSnapshot */
    public void restore();
}