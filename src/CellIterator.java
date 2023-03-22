/** Abstract class to represent the Cell Iterator
 * Defines methods for the iterator, to check next and retrieve next
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class CellIterator implements Iterator<Cell> {
    /** Abstract method to check if there are more values in the iterator */
    public abstract boolean hasNext();
    /** Abstract method to retrieve the next cell in the iterator */
    public abstract Cell next() throws NoSuchElementException;
}
