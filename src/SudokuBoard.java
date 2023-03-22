public interface SudokuBoard{
    public void buildBoard();
    public int getSize();
    public int getCellValue(int row, int col);
    public Cell getCell(int row, int col);
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
}