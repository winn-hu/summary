package concurrency.cycle;

/**
 * @author Winn
 * Effect is that protect from otherb thread method by Polymorphism[Observable observableThread = new ObservableThread<>(...)]
 * You can add more method if needed.
 */
public interface Observable {

    enum Cycle{
        STARTED,RUNNING,DOENE,ERROR
    }

    Cycle getCycle();

    void start();

    void interrupt();
}
