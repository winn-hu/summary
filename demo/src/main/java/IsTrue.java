/**
 * Demo Enumerate
 * @author Winn
 */
public enum IsTrue {
    NO(0),
    YES(1);

    private Integer value;

    private IsTrue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static void main(String[] args) {
        System.out.println(IsTrue.NO instanceof IsTrue);
        System.out.println(IsTrue.NO);
    }
}
