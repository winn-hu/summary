package concurrency.cycle;

public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifeCycle<T> taskLifeCycle;
    private final Task<T>  task;
    private Cycle cycle;

    public ObservableThread(Task<T> task) {
        this(new TaskLifeCycle.EmptyTaskLifeCycle<>(), task);
    }

    public ObservableThread(TaskLifeCycle<T> taskLifeCycle, Task<T> task) {
        if(task == null) throw new IllegalArgumentException("The task is required.");
        this.taskLifeCycle = taskLifeCycle;
        this.task = task;
    }

    /**
     * just to change lifeCycle and invoke task.call().
     */
    @Override
    public final void run() {

        try {
            update(Cycle.RUNNING,null,null);
            T result = task.call();
            update(Cycle.DOENE,result,null);
        } catch (Exception e) {
            update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e){
        this.cycle = cycle;
        if (taskLifeCycle == null) return;
        try{
            System.out.println("Life cycle is updated to "+cycle);
            switch (cycle){
                case STARTED:
                    this.taskLifeCycle.onStart(Thread.currentThread());
                    break;
                case RUNNING:
                    this.taskLifeCycle.onRunning(Thread.currentThread());
                    break;
                case DOENE:
                    this.taskLifeCycle.onFinish(Thread.currentThread(),result);
                    break;
                case ERROR:
                    this.taskLifeCycle.onError(Thread.currentThread(), e);
                    break;
            }
        } catch (Exception ex) {
            if (Cycle.ERROR == cycle){
                throw ex;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return cycle;
    }
}
