import java.util.HashSet;
import java.util.Set;

public class Observable {
    private Set<Observer> observers = new HashSet<>();
    public void subscribe(Observer observer) {
        observers.add(observer);
    }
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        observers.forEach(observer -> observer.update());
    }
}
