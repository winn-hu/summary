package concurrency.threadPattern.singleThreadExcution.meal;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class EatNodleThreadExt extends Thread {
    private final String name;
    private final TablewarePair tablewarePair;

    public EatNodleThreadExt(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while (true){
            eat();
        }
    }

    private void eat() {
        synchronized (tablewarePair){
            System.out.println(String.format("%s takes up %s(left)",name,tablewarePair.getLeftTool()));
            System.out.println(String.format("%s takes up %s(right)",name,tablewarePair.getRightTool()));
            System.out.println(String.format("%s is eating now.",name));
            System.out.println(String.format("%s puts down %s(right)",name,tablewarePair.getRightTool()));
            System.out.println(String.format("%s puts down %s(left)",name,tablewarePair.getLeftTool()));
        }
    }

    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new EatNodleThreadExt("A", new TablewarePair(fork,fork)).start();
        new EatNodleThreadExt("B", new TablewarePair(knife,fork)).start();
    }
}
