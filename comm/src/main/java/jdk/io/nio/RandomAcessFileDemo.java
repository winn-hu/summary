package jdk.io.nio;

import java.io.RandomAccessFile;

/**
 *
 * RandomAccessFile������ģʽ:
 *     r	ֻ��
 *     rw	��д
 *     rws	ÿ������д������ͬ����ˢ�µ����̣�ˢ�����ݺ�Ԫ����
 *     rwd	ÿ������д������ͬ����ˢ�µ����̣�ˢ������
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
     * �Ḳ��ԭ������
     * @param name �ļ�ȫ·��
     * @throws Exception
     */
    public static void write(String name,String content) throws Exception {
        RandomAccessFile file = new RandomAccessFile(name, "rw");
        file.write(content.getBytes());
    }

    /**
     * ���ı�ĩβ׷������
     * @param name �ļ�ȫ·��
     * @param content ��Ҫд�������
     * @throws Exception
     */
    public static void append(String name,String content) throws Exception {
        RandomAccessFile file = new RandomAccessFile(name, "rw");
        //���ù��λ�õ���ĩ
        file.seek(file.length());
        file.write(content.getBytes());
    }

    public static void main(String[] args) throws Exception {
        append(NAME,"This is a test...");
    }
}
