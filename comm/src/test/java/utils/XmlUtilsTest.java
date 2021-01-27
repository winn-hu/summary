package utils;

import org.junit.Test;

public class XmlUtilsTest {

    @Test
    public void xml2Json() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>org.example</groupId>\n" +
                "    <artifactId>Test20200121</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "\n" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>net.sf.json-lib</groupId>\n" +
                "            <artifactId>json-lib</artifactId>\n" +
                "            <version>2.4</version>\n" +
                "            <classifier>jdk15</classifier>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.jdom</groupId>\n" +
                "            <artifactId>jdom</artifactId>\n" +
                "            <version>2.0.2</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>xom</groupId>\n" +
                "            <artifactId>xom</artifactId>\n" +
                "            <version>1.2.5</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>commons-beanutils</groupId>\n" +
                "            <artifactId>commons-beanutils</artifactId>\n" +
                "            <version>1.9.4</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.apache.commons</groupId>\n" +
                "            <artifactId>commons-collections4</artifactId>\n" +
                "            <version>4.4</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.apache.commons</groupId>\n" +
                "            <artifactId>commons-lang3</artifactId>\n" +
                "            <version>3.4</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>commons-logging</groupId>\n" +
                "            <artifactId>commons-logging</artifactId>\n" +
                "            <version>1.1.1</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>net.sf.ezmorph</groupId>\n" +
                "            <artifactId>ezmorph</artifactId>\n" +
                "            <version>1.0.6</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.json</groupId>\n" +
                "            <artifactId>json</artifactId>\n" +
                "            <version>20171018</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "\n" +
                "\n" +
                "</project>";
        System.out.println(XmlUtils.xmlToJson(xml));
    }

    @Test
    public void json2xml() {
        String json = "{\"student\": {  \"score\": 80,  \"subject\": \"math\",  \"name\": \"Tom\" }}";
        System.out.println(XmlUtils.jsonToXml(json));
    }

}
