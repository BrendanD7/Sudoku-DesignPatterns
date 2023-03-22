import java.util.Iterator;

public abstract class CellIterator implements Iterator<Cell> {
    public abstract boolean hasNext();
    public abstract Cell next();
}
