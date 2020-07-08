package jdk;

/**
 * @XmlAccessorType ��ע�⣬����Java����Ҫӳ�䵽Xml�е�Ԫ��
 *      XmlAccessType.FIELD��ӳ��������е������ֶε�XML
 *      XmlAccessType.PROPERTY��ӳ��������е����ԣ�get/set��������XML
 *      XmlAccessType.PUBLIC_MEMBER����������е�����public��field��propertyͬʱӳ�䵽XML��Ĭ�ϣ�
 *      XmlAccessType.NONE����ӳ��
 *
 * @XmlRootElement ����ӳ��Ϊxmlȫ��Ԫ�أ�Ҳ���Ǹ�Ԫ��
 *      name������ָ������Ԫ�ص����֣�����ָ����Ĭ��ʹ������Сд��ΪԪ����
 *      namespace������ָ�����ɵ�Ԫ�������������ռ�
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
