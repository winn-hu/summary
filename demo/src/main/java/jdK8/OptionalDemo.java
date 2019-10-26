package jdK8;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
        //isPresent���� �� �ж��Ƿ�Ϊ��
        System.out.println(Optional.empty().isPresent());

        //ifPresent���� �� �����Ϊ�գ���ִ�к���
        Optional.of("one").ifPresent(System.out::println);

        //orElse���� �� ���Ϊ�գ�����"-"�������Ϊ�գ�����value
        System.out.println(Optional.empty().orElse("-"));
        System.out.println(Optional.of("one").orElse("-"));

        //orElseGet���� �� ���Ϊ�գ�ִ�к��������������з���ֵ���������Ϊ�գ�����value
        System.out.println(Optional.of("one").orElseGet(() -> "-"));
        System.out.println(Optional.empty().orElseGet(() -> "-"));

        //�ж�name�Ƿ�Ϊ��
        boolean present = Optional.of(new Person()).map(Person::getName).isPresent();
        System.out.println(present);
    }
}
