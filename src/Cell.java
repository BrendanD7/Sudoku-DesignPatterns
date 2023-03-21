public class Cell implements CellPrototype{
    private int value;

    public Cell(int value) {
        this.value = value;
    }

    public Cell(Cell cell){
        this.value = cell.getValue();
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Cell clone(){
        return new Cell(this);
    }
}