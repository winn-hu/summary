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
 * 参考文档：https://www.w3cschool.cn/jaxb2/jaxb2-8wlz2zoz.html
 */

public class XmlUtils {

    public static void java2Xml(Student student) throws JAXBException {

        //根据class创建JAXB上下文
        JAXBContext context =   JAXBContext.newInstance(Student.class);
        //创建marshaller实例，作用： Java -> Xml
        Marshaller marshaller = context.createMarshaller();
        //设置参数：是否序列化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        //以控制台的形式输出
        marshaller.marshal(student,System.out);
        String path = XmlUtils.class.getClassLoader().getResource("").getPath();
        File file = new File(path+"jdk/student_copy.xml");
        marshaller.marshal(student,file);

    }

    public static void xml2Java() throws JAXBException {
        //根据class创建JAXB上下文
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
