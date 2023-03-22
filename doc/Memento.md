# Memento Design Pattern for Sudoku

## What is the Memento Design Pattern?
The memento pattern is a behavioral design pattern that allows an object to capture its internal state, and save this state externally. This allows the object to be restored to this state later. This is useful to support undo operations in a program, or restoration to a certain state for the object.

### **Benefits to the Memento Design Pattern:**
- Produce snapshots of the object without violating encapsulation
- Restore states of an object
 
## How is it used in the Sudoku Program?
The memento design pattern is used to store the initial state of the board that the player would see once they start the game. This state does not change unless a new game is started, and is used to reset the board to the initial state when the player clicks the reset selections button. 

When the board is first created the state is stored at the end of the removeElements method, as this is when the final board the user will play using is prepared.  This is done by calling the createSnapshot method in the SudokuBoard class, which creates a new memento of the initial state of the board and stores it in the caretaker class.

Once the reset selections button is clicked, then the restore method from the SudokuBoard class is called, which will restore the board to its initial state by retrieving the memento from the caretaker, and the initial array from the memento. Once retrieved, it is used to restore all the original contents of the board, which include the computer generated values, while removing all player inputs.

### **Memento Class Code**
```java
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
```

### **Caretaker Class Code**

```java
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

```

### **Example usage in EasySudokuBoard**
```java
// Instance Variable
private BoardCaretaker initialState = new BoardCaretaker();

// Called at the end of removeElements
createSnapshot();

// Later in the program
    /** Memento method to create a snapshot of the board */
    public void createSnapshot(){
        this.initialState.setState(new BoardMemento(board));
    }

    /** Restores the board based on the snapshot */
    public void restore(){
        // Retrieve the previous state of the board
        Cell[][] previousState = initialState.getState().getMemento();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = previousState[i][j].clone();
            }
        }
    }

```

## Why have we used it?
The memento was implemented to allow restoration to the intial state of the board without violating encapsulation. Before using the memento pattern the selections were cleared by resetting all of the non-editable values in the GUI with 0s, but the board itself was not changed. Meaning that pressing the submit button again would cause the game to read the board that was last checked for validity. The memento has been used to store an initial, unchangeable reference to the initial state of the board, and allow convenient access to the restore method to use in the reset selection event.

# UML

