import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo simple spider
 * @author Winn
 */
public class SpiderUtil {

    public static Elements getElementsByClassName(String requestUrl, String className){
        Elements elements = new Elements();
        try {
            URL url = new URL(requestUrl);
            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder html = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {
                html.append(temp);
            }
            is.close();
            System.out.println(html.toString());
            //parse html page by Jsoup
            Document doc = Jsoup.parse(html.toString());
            elements = doc.getElementsByClass(className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elements;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        Elements elements = getElementsByClassName("http://j.kyee.com.cn/browse/FYGYL-1760?filter=37461", "aui-page-header-main");
        for (Element element : elements) {
            map.put(element.text(),element.attr("href"));
        }

        map.forEach((key,value) -> System.out.println(key+" : "+value));
    }
}
