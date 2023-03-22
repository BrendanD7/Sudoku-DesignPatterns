import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class CellIterator implements Iterator<Cell> {
    public abstract boolean hasNext();
    public abstract Cell next() throws NoSuchElementException;
}
