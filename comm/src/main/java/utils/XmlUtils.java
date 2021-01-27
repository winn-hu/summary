package utils;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import jdk.Student;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import java.io.*;

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

    public static String xmlToJson(String xml) {
        //使用DOM4j
        SAXReader saxReader = new SAXReader();
        //使用json的xml转json方法
        JSONObject jsonObject = XML.toJSONObject(xml);
        //设置缩进转为字符串
        return jsonObject.toString(3);
    }
    //json转换成xml
    public static String jsonToXml(String json){
        //输入流
        StringReader input = new StringReader(json);
        //输出流
        StringWriter output = new StringWriter();
        //构建配置文件
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
        try {
            //xml事件读
            //  This is the top level interface for parsing XML Events.  It provides
            //  the ability to peek at the next event and returns configuration
            //  information through the property interface.
            // 这是最解析XML事件最顶层的接口，它提供了查看下一个事件并通过属性界面返回配置信息的功能。
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            //这是编写XML文档的顶级界面。
            //验证XML的形式不需要此接口的实例。
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            //创建一个实例使用默认的缩进和换行
            writer = new PrettyXMLEventWriter(writer);
            //添加整个流到输出流，调用next方法，知道hasnext返回false
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }

}
