public interface SudokuBoard{
    public void buildBoard();
    public int getSize();
    public int getCellValue(int row, int col);
    public Cell getCell(int row, int col);
    public int getCellSize();

    /** Checks if whole board is valid */
    default boolean isBoardValid() {
        // returns false if any row, column, or subgrid is invalid
        for(int i = 0; i < getSize(); i++) {
            if (!isRowValid(i) || !isColValid(i) || !isSubGridValid(i)) {
                return false;
            }
        }
        return true;
    }

    /** Checks if given row is valid */
    private boolean isRowValid(int row){
        RowIterator rowIt = new RowIterator(row, this);
        boolean[] visited = new boolean[getSize()];

        while (rowIt.hasNext()) {
            int value = rowIt.next().getValue();
            if (value != 0) {
                if (visited[value-1]) {
                    return false;
                }

                visited[value-1] = true;
            }
        }

        return true;
    }
    
    /** Checks if given column is valid */
    private boolean isColValid(int col){
        ColumnIterator colIt = new ColumnIterator(col, this);
        boolean[] visited = new boolean[getSize()];

        while (colIt.hasNext()) {
            int value = colIt.next().getValue();
            if (value != 0) {
                if (visited[value-1]) {
                    return false;
                }

                visited[value-1] = true;
            }
        }
        
        return true;
    }

    /** Checks if given subgrid is valid */
    private boolean isSubGridValid(int subgrid) {
        SubgridIterator subgridIt = new SubgridIterator(subgrid, this);
        boolean[] visited = new boolean[getSize()];

        while (subgridIt.hasNext()) {
            int value = subgridIt.next().getValue();
            if (value != 0) {
                if (visited[value-1]) {
                    return false;
                }

                visited[value-1] = true;
            }
        }

        return true;
    }

    /** Check if given input is valid */
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