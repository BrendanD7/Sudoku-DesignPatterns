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
