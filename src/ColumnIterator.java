import java.util.NoSuchElementException;

public class ColumnIterator extends CellIterator {
    private SudokuBoard board;
    private int col, curRow, maxRow;

    public ColumnIterator(int col, SudokuBoard board) {
        this.board = board;
        this.col = col;
        this.curRow = 0;
        this.maxRow = board.getSize();
    }

    /**
     * Returns true if there are more values in the iterator
     */
    @Override
    public boolean hasNext() {
        return curRow < maxRow;
    }


    /**
     * Returns the next cell if it exists
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

    public void reset() {
        curRow = 0;
    }
}
