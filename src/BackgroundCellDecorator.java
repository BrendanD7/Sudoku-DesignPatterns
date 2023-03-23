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
     * @param cell
     */
    public void decorate(JTextField cell) {
        cell.setBackground(colour);
    }
}
