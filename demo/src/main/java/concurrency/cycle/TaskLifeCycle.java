package concurrency.cycle;

public interface TaskLifeCycle<T> {

    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread, T result);

    void onError(Thread thread, Exception e);

    class EmptyTaskLifeCycle<T> implements TaskLifeCycle{

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, Object result) {
            System.out.println("The result is "+result);
        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
