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