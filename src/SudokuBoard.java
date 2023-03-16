public interface SudokuBoard{
    public void buildBoard();
    public int getSize();
    public int getCellValue(int row, int col);
    public boolean isBoardValid();
    public Cell getCell(int row, int col);
}