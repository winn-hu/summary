package jdk;

/**
 * @XmlAccessorType 类注解，定义Java中需要映射到Xml中的元素
 *      XmlAccessType.FIELD：映射这个类中的所有字段到XML
 *      XmlAccessType.PROPERTY：映射这个类中的属性（get/set方法）到XML
 *      XmlAccessType.PUBLIC_MEMBER：将这个类中的所有public的field或property同时映射到XML（默认）
 *      XmlAccessType.NONE：不映射
 *
 * @XmlRootElement 将类映射为xml全局元素，也就是根元素
 *      name：用于指定生成元素的名字，若不指定，默认使用类名小写作为元素名
 *      namespace：用于指定生成的元素所属的命名空间
 */

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "fpgl")
public class Student {

    private int id;
    private String name;
    private int age;

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
