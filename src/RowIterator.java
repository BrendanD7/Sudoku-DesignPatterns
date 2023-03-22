/** Represents a row iterator to use in the SudokuBoard classes for iterating through the Cell array */
import java.util.NoSuchElementException;

public class RowIterator extends CellIterator {
    /** Instance Variables */
    private SudokuBoard board;
    private int row, curCol, maxCol;

    /** Constructor for the row iterator
     * @param row The row to be iterated
     * @param board The SudokuBoard to be iterated
     */
    public RowIterator(int row, SudokuBoard board) {
        this.board = board;
        this.row = row;
        this.curCol = 0;
        this.maxCol = board.getSize();
    }

    /**
     * Returns true if there are more values in the iterator
     * @return True or false indicating if there are more values in the iterator
     */
    @Override
    public boolean hasNext() {
        return curCol < maxCol;
    }


    /**
     * Returns the next cell if it exists
     * @return The next cell in the iterator, if there are any
     */
    @Override
    public Cell next() throws NoSuchElementException {
        if (hasNext()){
            Cell next = board.getCell(row, curCol);

            curCol++;

            return next;
        }

        throw new NoSuchElementException();
    }

    /** Reset the iterator */
    public void reset() {
        curCol = 0;
    }
}
