package concurrency;

public class CPUCount {

    public static void main(String[] args) {
        int num = Runtime.getRuntime().availableProcessors();
        System.out.println(num);
    }
}
