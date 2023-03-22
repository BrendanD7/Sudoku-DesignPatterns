/** Memento class to store initial state of the board, used for Reset in the GUI */
public class BoardMemento {
    /** Instance Varialbes */
    private final Cell[][] cellMemento;

    /** Constructor to create a board memento, clones the given array into a new one
     * @param cellMemento The 2D array of cells to be stored
     */
    public BoardMemento(Cell[][] cellMemento){
        this.cellMemento = new Cell[cellMemento.length][cellMemento[0].length];
        for (int i = 0; i < cellMemento.length; i++) {
            for (int j = 0; j < cellMemento[i].length; j++) {
                this.cellMemento[i][j] = cellMemento[i][j].clone();
            }
        }
    }

    /** Retrieve the array stored in the memento
     * @return The Cell Array representing the initial state
     */
    public Cell[][] getMemento(){
        return cellMemento;
    }
}
