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

    public static String xmlToJson(String xml) {
        //ʹ��DOM4j
        SAXReader saxReader = new SAXReader();
        //ʹ��json��xmlתjson����
        JSONObject jsonObject = XML.toJSONObject(xml);
        //��������תΪ�ַ���
        return jsonObject.toString(3);
    }
    //jsonת����xml
    public static String jsonToXml(String json){
        //������
        StringReader input = new StringReader(json);
        //�����
        StringWriter output = new StringWriter();
        //���������ļ�
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
        try {
            //xml�¼���
            //  This is the top level interface for parsing XML Events.  It provides
            //  the ability to peek at the next event and returns configuration
            //  information through the property interface.
            // ���������XML�¼����Ľӿڣ����ṩ�˲鿴��һ���¼���ͨ�����Խ��淵��������Ϣ�Ĺ��ܡ�
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            //���Ǳ�дXML�ĵ��Ķ������档
            //��֤XML����ʽ����Ҫ�˽ӿڵ�ʵ����
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            //����һ��ʵ��ʹ��Ĭ�ϵ������ͻ���
            writer = new PrettyXMLEventWriter(writer);
            //����������������������next������֪��hasnext����false
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
