import java.util.NoSuchElementException;

public class SubgridIterator extends CellIterator {
    /** Instance Variables */
    private SudokuBoard board;
    private int curCol, minCol, maxCol, curRow, minRow, maxRow;
    

    /** Constructor to create the sub grid iterator
     * @param subgrid The subgrid to be iterated through
     * @param board The Board to be iterated through
     */
    public SubgridIterator(int subgrid, SudokuBoard board) {
        this.board = board;

        int cellSize = board.getCellSize();
        this.minCol = cellSize * (subgrid % cellSize);
        this.maxCol = this.minCol + cellSize;
        this.minRow = cellSize * (subgrid / cellSize);
        this.maxRow = this.minRow + cellSize - 1;
        
        this.curCol = this.minCol;
        this.curRow = this.minRow;
    }

    /**
     * Returns true if there are more values in the iterator
     * @return True if there are more values in the iterator, false if there are none
     */
    @Override
    public boolean hasNext() {
        return hasNextCol() || hasNextRow();
    }

    /**
     * Returns the next cell if it exists
     * @return The next cell in the iterator
     */
    @Override
    public Cell next() throws NoSuchElementException {
        // check if we need to increment the row
        if (hasNextRow() && !hasNextCol()) {
            curRow++;
            curCol = minCol;
        }

        // get the next cell
        if (hasNextCol()) {
            Cell next = board.getCell(curRow, curCol);

            curCol++;
            return next;
        }

        throw new NoSuchElementException();
    }

    /** Boolean method to check if there are more columns
     * @return True if there are more columns, false if there are not
     */
    private boolean hasNextCol() {
        return this.curCol < this.maxCol;
    }

    /** Boolean method to check if there are any more rows
     * @return True if there are more rows, false if there are not
     */
    private boolean hasNextRow() {
        return this.curRow < this.maxRow;
    }

    /** Resets the iterator */
    public void reset () {
        this.curCol = this.minCol;
        this.curRow = this.minRow;
    }

}
