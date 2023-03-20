public class Memento {
    private final Cell[][] cellMemento;

    public Memento(Cell[][] cellMemento){
        this.cellMemento = cellMemento;
    }

    public Cell[][] getMemento(){
        return cellMemento;
    }
}
