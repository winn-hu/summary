package jdK8;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
        //isPresent（） ： 判断是否为空
        System.out.println(Optional.empty().isPresent());

        //ifPresent（） ： 如果不为空，则执行函数
        Optional.of("one").ifPresent(System.out::println);

        //orElse（） ： 如果为空，返回"-"；如果不为空，返回value
        System.out.println(Optional.empty().orElse("-"));
        System.out.println(Optional.of("one").orElse("-"));

        //orElseGet（） ： 如果为空，执行函数（函数必须有返回值）；如果不为空，返回value
        System.out.println(Optional.of("one").orElseGet(() -> "-"));
        System.out.println(Optional.empty().orElseGet(() -> "-"));

        //判断name是否为空
        boolean present = Optional.of(new Person()).map(Person::getName).isPresent();
        System.out.println(present);
    }
}
