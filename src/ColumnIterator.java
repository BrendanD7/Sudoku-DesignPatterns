import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColumnIterator implements Iterator<Cell> {
    private SudokuBoard board;
    private int col, curRow, maxRow;

    public ColumnIterator(int col, SudokuBoard board) {
        this.board = board;
        this.col = col;
        this.curRow = 0;
        this.maxRow = board.getSize();
    }

    public boolean hasNext() {
        return curRow < maxRow;
    }


    /**
     * Returns the next cell if it exists
     */
    public Cell next() throws NoSuchElementException {
        if (hasNext()){
            Cell next = board.getCell(curRow, col);

            curRow++;

            return next;
        }

        throw new NoSuchElementException();
    }

    public void reset() {
        curRow = 0;
    }
}
