/** Caretaker to store the initial board memento */
public class BoardCaretaker {
    /** Instance Variable */
    private BoardMemento memento;

    /** Sets the memento in the caretaker
     * @param memento The memento to be stored
     */
    public void setState(BoardMemento memento){
        this.memento = memento;
    }
    
    /** Retrieve the memento from the caretaker
     * @return The memento stored in this caretaker
     */
    public BoardMemento getState(){
        return memento;
    }
}
