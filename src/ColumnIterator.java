/** Represents a Column Iterator, used to iterate through columns of the SudokuBoard class' Cell array */
import java.util.NoSuchElementException;

public class ColumnIterator extends CellIterator {
    /** Instance Variables */
    private SudokuBoard board;
    private int col, curRow, maxRow;

    /** Column Iterator Constructor
     * @param col The column to be iterated
     * @param board The board to be iterated
     */
    public ColumnIterator(int col, SudokuBoard board) {
        this.board = board;
        this.col = col;
        this.curRow = 0;
        this.maxRow = board.getSize();
    }

    /**
     * Boolean method to check if  there are more elements in the iterator
     * @return True if there are more values in the row, false if not
     */
    @Override
    public boolean hasNext() {
        return curRow < maxRow;
    }


    /**
     * Retrieves the next cell in the iterator if it exists
     * @return The next cell in the iterator
     */
    @Override
    public Cell next() throws NoSuchElementException {
        if (hasNext()){
            Cell next = board.getCell(curRow, col);

            curRow++;

            return next;
        }

        throw new NoSuchElementException();
    }

    /** Reset the iterator */
    public void reset() {
        curRow = 0;
    }
}
