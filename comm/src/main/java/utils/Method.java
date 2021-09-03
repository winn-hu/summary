package utils;

public interface Method {

    default Object invoke(Object ...t) throws Exception {
        System.out.println(t[0]);
        return new Object();
    }
}
