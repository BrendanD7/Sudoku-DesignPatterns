public class SudokuCellFactory implements CellFactory{
    public Cell createCell(int value){
        return new Cell(value);
    }
}
