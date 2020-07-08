package jdk;

import sun.security.util.Resources;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.Objects;

/**
 * �ο��ĵ���https://www.w3cschool.cn/jaxb2/jaxb2-8wlz2zoz.html
 */

public class XmlUtils {

    public static void java2Xml(Student student) throws JAXBException {

        //����class����JAXB������
        JAXBContext context =   JAXBContext.newInstance(Student.class);
        //����marshallerʵ�������ã� Java -> Xml
        Marshaller marshaller = context.createMarshaller();
        //���ò������Ƿ����л����
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        //�Կ���̨����ʽ���
        marshaller.marshal(student,System.out);
        String path = XmlUtils.class.getClassLoader().getResource("").getPath();
        File file = new File(path+"jdk/student_copy.xml");
        marshaller.marshal(student,file);

    }

    public static void xml2Java() throws JAXBException {
        //����class����JAXB������
        JAXBContext context =   JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream stream = XmlUtils.class.getClassLoader().getResourceAsStream("jdk/Student.xml");
        Student student = (Student)unmarshaller.unmarshal(stream);
        System.out.println(student);
    }

    public static void main(String[] args) {
        try {
            //java2Xml(new Student(1001, "Winn", 24));
            xml2Java();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
