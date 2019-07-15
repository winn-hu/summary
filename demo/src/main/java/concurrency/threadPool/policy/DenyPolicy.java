package concurrency.threadPool.policy;

import concurrency.threadPool.exception.RunnaleDenyExceeption;
import concurrency.threadPool.pool.ThreadPool;

/**
 * The DenyPolicy will be invoked if RunnableQueue is full when Runnable needs to be added to RunnableQueue.
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool pool);

    /**
     * The runnable will be discarded directly.
     */
    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool pool) {
            //do nothing
        }
    }

    class AbortDenyPolciy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool pool) {
            throw new RunnaleDenyExceeption("The runnable "+runnable+" will be abort.");
        }
    }

    /**
     * The runnable will be executed by invoker, it isn't exxcuted by new thread.
     */
    class RunnerDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, ThreadPool pool) {
            if(!pool.isShutdown()){
                System.out.println("Runner deny policy start....");
                runnable.run();
            }
        }
    }
}
