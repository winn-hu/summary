import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLEncodeDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("www.baiddu.com?name=Winn&age=12 ", "utf-8");
        System.out.println(encode);
    }
}
