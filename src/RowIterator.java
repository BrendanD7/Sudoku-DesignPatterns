import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowIterator implements Iterator<Cell> {
    private SudokuBoard board;
    private int row, curCol, maxCol;

    public RowIterator(int row, SudokuBoard board) {
        this.board = board;
        this.row = row;
        this.curCol = 0;
        this.maxCol = board.getSize();
    }

    /**
     * Returns true if there are more values in the iterator
     */
    public boolean hasNext() {
        return curCol < maxCol;
    }


    /**
     * Returns the next cell if it exists
     */
    public Cell next() throws NoSuchElementException {
        if (hasNext()){
            Cell next = board.getCell(row, curCol);

            curCol++;

            return next;
        }

        throw new NoSuchElementException();
    }

    public void reset() {
        curCol = 0;
    }
}
