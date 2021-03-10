package jdk.io.nio;

import java.io.RandomAccessFile;

/**
 *
 * RandomAccessFile的四种模式:
 *     r	只读
 *     rw	读写
 *     rws	每当进行写操作，同步的刷新到磁盘，刷新内容和元数据
 *     rwd	每当进行写操作，同步的刷新到磁盘，刷新内容
 */
public class RandomAcessFileDemo {

    public static final String NAME = "F:\\summary\\comm\\src\\main\\resources\\config.txt";

    public static void readAll(String name) throws Exception {
        RandomAccessFile file = new RandomAccessFile(name, "r");
        byte[] context =new byte[102400];
        file.read(context);
        System.out.println(new String(context));
    }

    public static void readLine(String name) throws Exception {
        RandomAccessFile file = new RandomAccessFile(name, "r");
        int count = 0;
        String line;
        while ((line = file.readLine()) != null) {
            count++;
            System.out.println(line);
        }
        System.out.println(">>>>>>file line count : "+count);
    }

    /**
     * 会覆盖原有内容
     * @param name 文件全路径
     * @throws Exception
     */
    public static void write(String name,String content) throws Exception {
        RandomAccessFile file = new RandomAccessFile(name, "rw");
        file.write(content.getBytes());
    }

    /**
     * 在文本末尾追加内容
     * @param name 文件全路径
     * @param content 需要写入的内容
     * @throws Exception
     */
    public static void append(String name,String content) throws Exception {
        RandomAccessFile file = new RandomAccessFile(name, "rw");
        //设置光标位置到文末
        file.seek(file.length());
        file.write(content.getBytes());
    }

    public static void main(String[] args) throws Exception {
        append(NAME,"This is a test...");
    }
}
