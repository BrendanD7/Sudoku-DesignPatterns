# Decorator Design Pattern for Sudoku

## What is the Decorator Design Pattern?
The decorator design pattern is a structural design pattern that allows behavior to be added to an individual object dynamically, without impacting the behavior of similar objects. 

### **Benefits to the Decorator Design Pattern:**
- Open-Closed Principle: New behaviors can be added without further modification
- Single Responsibility: Divide behaviors between classes
 - Increased modularity 
 
## How is it used in the Sudoku Program?
In the Sudoku program the decorator is used to extend the functionality of the GUI by allowing for cells to be coloured. 
A base decorator is defined in the CellDecorator class, which defines a method to colour cells.

### **CellDecorator.java**
```java
import javax.swing.*;

/** Interface class which represents a CellDecorator object
 *  Used to Decorate cells of the Sudoku Board
 */
public interface CellDecorator {

    /**
     * Decorates the cell in the given parameter
     * @param cell The cell to be decorated
     */
    void colour(JTextField cell);
}

```

There are two concrete decorators defined to colour certain types of cells, one is used to colour foreground cells, and another colour background cells. These can be seen in the ForegroundCellDecorator.java and BackgroundCellDecorator.java. These classes ovverride the colour method from the base decorator and set the colour of the cells based on what is indicated in the GUI.

### **ForegroundCellDecorator.java**
```java
import javax.swing.*;
import java.awt.*;

/** Represents decorating the foreground of the cell by changing the foreground colour */
public class ForegroundCellDecorator implements CellDecorator {

    /**
     *Instance variable for the colour to decorate foreground
     */
    private Color colour;

    /**
     * Constructor to build the ForegroundCell Decorator
     * @param colour colour preferred for foreground of the cell
     */
    public ForegroundCellDecorator(Color colour) {
        this.colour = colour;
    }

    /**
     * Decorates the specified cell by modifying the foreground colour
     * @param cell The cell to be coloured
     */
    public void colour(JTextField cell) {
        cell.setForeground(colour);
    }
}
```

### **BackgroundCellDecorator.java**
```java
import javax.swing.*;
import java.awt.*;

/** Represents decorating the background of the cell by changing the background colour */
public class BackgroundCellDecorator implements CellDecorator {

    /**
     *Instance variable for the colour to decorate background
     */
    private Color colour;

    /**
     * Constructor to build the BackgroundCell Decorator
     * @param colour colour preferred for background of the cell
     */
    public BackgroundCellDecorator(Color colour) {
        this.colour = colour;
    }

    /**
     * Decorates the specified cell by modifying the background colour
     * @param cell The cell to be coloured
     */
    public void colour(JTextField cell) {
        cell.setBackground(colour);
    }
}

```

### **Usage within the GUI**
```java
 // Decorate user-selected cells
if (cell.isEditable()) {
    BackgroundCellDecorator userBGDecorator = new BackgroundCellDecorator(new Color(211, 245, 187,255));
    ForegroundCellDecorator userFGDecorator = new ForegroundCellDecorator(new Color(66, 135, 26, 255));
    userBGDecorator.colour(cell);
    userFGDecorator.colour(cell);
}
// Decorate software-generated cells
else {
    BackgroundCellDecorator givenBGDecorator = new BackgroundCellDecorator(new Color(171, 230, 133, 255));
    ForegroundCellDecorator givenFGDecorator = new ForegroundCellDecorator(new Color(48, 96, 19, 255));
    givenBGDecorator.colour(cell);
    givenFGDecorator.colour(cell);
}
```
## Why have we used it?
We have applied the Decorator pattern as it allows for the GUI program to have increased functionality, while also dividing the functionality into a new class which could be easily expanded to include other types of decorating to apply to the cells, such as giving them borders, or shadows. This could allow for the GUI to be expanded visually in the future, with concepts such as themes or allowing the user to set their own colour schemes.

# UML
![Decorator UML](../data/Decorator-UML.png)
