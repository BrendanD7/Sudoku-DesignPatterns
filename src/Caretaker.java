public class Caretaker {
    private Memento snapshot;

    public Memento getMemento(){
        return snapshot;
    }

    public void setMemento(Memento memento){
        this.snapshot = memento;
    }
}
