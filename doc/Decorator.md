# Decorator Design Pattern for Sudoku

## What is the Decorator Design Pattern?
The decorator design pattern is a structural design pattern that allows new behaviors or functionality to be added to an individual object dynamically, without impacting the behavior of similar objects. This is done by wrapping objects within "Decorators", which add the requested functionality/behaviors. The decorator pattern is useful to add non-required functionality in the future, such as visual differences in the way a GUI looks.

### **Benefits to the Decorator Design Pattern:**
- Open-Closed Principle: New behaviors/functionality can be added without further modification
- Single Responsibility: Divide behaviors between classes
 - Increased modularity 
 
## How is it used in the Sudoku Program?
In the Sudoku program, the decorator is used to extend the functionality of the GUI by allowing for cells to be coloured. 
A base decorator is defined in the CellDecorator interface, which defines a method to colour cells.

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

There are two concrete decorators defined to colour certain types of cells, one is used to colour the cell's foreground, and another colours the cell's background. These can be seen in the ForegroundCellDecorator.java and BackgroundCellDecorator.java. These classes override the colour method from the base decorator and set the colour of the cells based on what is indicated in the GUI.

### **ForegroundCellDecorator.java**
```java
import javax.swing.*;
import java.awt.*;

/** Represents decorating the foreground of the cell by changing the foreground colour */
public class ForegroundCellDecorator implements CellDecorator {

    /**
     *Instance variable for the colour to decorate the foreground
     */
    private Color colour;

    /**
     * Constructor to build the ForegroundCell Decorator
     * @param colour colour preferred for the foreground of the cell
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
     *Instance variable for the colour to decorate the background
     */
    private Color colour;

    /**
     * Constructor to build the BackgroundCell Decorator
     * @param colour colour preferred for the background of the cell
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
We have applied the Decorator pattern as it allows for the GUI program to have increased functionality, while also dividing the functionality into a new class which could be easily expanded to include other types of decorating to apply to the cells, such as giving them borders, or shadows. This could allow for the GUI to be expanded visually in the future, with concepts such as themes or allowing the user to set their own colour schemes. This increased modularity allows the Sudoku Game to be expandable in the future with many new features, which was an overall goal of the design.

# UML
![Decorator UML](../data/Decorator-UML.png)
