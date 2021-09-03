package jdk.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

    public static final String NAME = "F:\\summary\\comm\\src\\main\\resources\\config.txt";

    public static void read() {
        RandomAccessFile file = null;
        FileChannel channel = null;
        try {
            file = new RandomAccessFile(NAME, "r");
            //��ȡ�ļ��ܵ�
            channel = file.getChannel();
            //���仺��ռ�
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //���ݶ��뻺��
            while (channel.read(buffer) != -1) {
                //Buffer��дģʽ��ɶ�ģʽ�������У�
                buffer.flip();
                //���
                System.out.println(new String(buffer.array()));
            }
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) channel.close();
                if (file != null) file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        read();
    }
}
