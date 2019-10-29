package jdK8;

import org.junit.Test;

import java.util.function.*;

public class FunctionalInterface {

    /**
     * Predicate用于判断条件是否满足
     * 函数必须返回一个Boolean  例如： user的ID == 1 && user的name必须以W或w开头
     *
     */
    @Test
    public void testPredicate() {
        Predicate<User> predicate = user -> user.getId() == 1;
        predicate = predicate.and(user -> user.getName().startsWith("W"))
                .or(user -> user.getName().startsWith("w"));
        assert predicate.test(new User(1,"Winn"));
    }

    /**
     *Consumer用于执行逻辑，
     * 函数不能有返回值，只能有一个参数
     *
     */
    @Test
    public void testConsumer() {
        Consumer<User> consumer = user -> {
            user.setId(2);
            user.setName("Tom");
        };
        consumer.andThen(user -> System.out.println(user.getName()))
                .accept(new User());
    }

    /**
     * 获取返回结果，函数不能有参数
     */
    @Test
    public void testSupplier() {
        Supplier supplier = () -> "Supplier Demo";
        assert "Supplier Demo".equals(supplier.get());
    }

    /**
     *Function用于执行逻辑，
     * 函数有返回值，只能有一个参数
     *function.andThen(function2) : 会将function的返回值作为function2的参数
     * compose: 与andThen正好相反
     */
    @Test
    public void testFunction() {
        Function<Object[], User> function0 = (array) -> new User((int)array[0], (String)array[1]);
        Function<User, Integer> function1 = User::getId;
        Function<Integer, String> function2 = x -> "User's id is "+x;

        Function<Object[], String> resultFunction = function1.andThen(function2).compose(function0);
        String msg = resultFunction.apply(new Object[]{3, "David"});

        assert  "User's id is 3".equals(msg);
    }

    /**
     * UnaryOperator ： 特殊的Function，入参和返回值的类型必须相同
     */
    @Test
    public void testUnaryOperator() {
        UnaryOperator<User> operator = user -> {
            user.setName("Nicole");
            return user;
        };
        assert "Nicole".equals(operator.apply(new User(4,"Jane")).getName());
    }

    /**
     *BiFunction 接受两个入参，有返回值
     */
    @Test
    public void testBiFunction() {
        BiFunction<Integer, String, User> bi = User::new;
        User allen = bi.apply(5, "Allen");
        assert allen.getId() == 5 && "Allen".equals(allen.getName());
    }

    /**
     * BinaryOperator ： BiFunction的特例，接两个类型相同的参数，有返回值（类型与入参相同）
     */
    @Test
    public void testBinaryOperator() {
        BinaryOperator<Integer> operator = (x, y) -> x * y;
        assert operator.apply(2, 3) == 6;
    }
}

class User {
    private int id;
    private String name;

    User() {
    }

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
