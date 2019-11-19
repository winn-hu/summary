package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Channel（通道）：Channel是一个对象，可以通过它进行异步的读取和写入数据(双向的流)
 * Channel的读写必须通过Buffer对象
 *
 * 在NIO中，所有的数据都是用Buffer处理的，它是NIO读写数据的中转池
 * 当向 Buffer 写入数据时，Buffer 会记录下写了多少数据（Step 3）。一旦要读取数据，需要通过 flip() 方法将 Buffer 从写模式切换到读模式(Step 4)。
 *
 *
 *
 * 使用 Buffer 读写数据一般遵循以下四个步骤：
 * 1.写入数据到 Buffer；
 * 2.调用 flip() 方法；
 * 3.从 Buffer 中读取数据；
 * 4.调用 clear() 方法或者 compact() 方法。
 *
 */
public class IOUtils {

    public static void copyFile(String src, String dest) {
        try {
            //1. create channel
            FileInputStream fi = new FileInputStream(src);
            FileChannel inChannel = fi.getChannel();

            FileOutputStream fo = new FileOutputStream(dest);
            FileChannel outChannel = fo.getChannel();

            //2. create buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //3. read
            while(inChannel.read(buffer) != -1) {
                //4. reset buffer : limit=position, position=0
                buffer.flip();
                //5.write
                outChannel.write(buffer);
                //6.clear
                buffer.clear();
            }
            //7. close
            inChannel.close();
            outChannel.close();
            fi.close();
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
