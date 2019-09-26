package concurrency.threadPattern.singleThreadExcution.meal;

public class EatNodleThread extends Thread {
    private final String name;
    private final Tableware leftTool;
    private final Tableware rightTool;

    public EatNodleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true){
            eat();
        }
    }

    private void eat() {
        synchronized (leftTool){
            System.out.println(String.format("%s takes up %s(left)",name,leftTool));
            synchronized (rightTool){
                System.out.println(String.format("%s takes up %s(right)",name,rightTool));
                System.out.println(String.format("%s is eating now.",name));
                System.out.println(String.format("%s puts down %s(right)",name,rightTool));
            }
            System.out.println(String.format("%s puts down %s(left)",name,leftTool));
        }
    }

    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new EatNodleThread("A",fork,knife).start();
        new EatNodleThread("B",knife,fork).start();
    }
}
