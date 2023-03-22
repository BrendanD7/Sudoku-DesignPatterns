public class BoardMemento {
    private final Cell[][] cellMemento;

    public BoardMemento(Cell[][] cellMemento){
        this.cellMemento = new Cell[cellMemento.length][cellMemento[0].length];
        for (int i = 0; i < cellMemento.length; i++) {
            for (int j = 0; j < cellMemento[i].length; j++) {
                this.cellMemento[i][j] = cellMemento[i][j].clone();
            }
        }
    }

    public Cell[][] getMemento(){
        return cellMemento;
    }
}
