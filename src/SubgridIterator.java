import java.util.NoSuchElementException;

public class SubgridIterator extends CellIterator {
    private SudokuBoard board;
    private int curCol, minCol, maxCol, curRow, minRow, maxRow;
    

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
     */
    @Override
    public boolean hasNext() {
        return hasNextCol() || hasNextRow();
    }

    /**
     * Returns the next cell if it exists
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

    private boolean hasNextCol() {
        return this.curCol < this.maxCol;
    }

    private boolean hasNextRow() {
        return this.curRow < this.maxRow;
    }

    public void reset () {
        this.curCol = this.minCol;
        this.curRow = this.minRow;
    }

}
