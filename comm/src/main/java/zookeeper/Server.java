package zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class Server {

    private static final String CONNECT_STRING = "172.17.23.79:2181,172.17.23.79:2182";
    private static final int TIME_OUT = 15000;

    private ZooKeeper zooKeeper;

    public Server() throws IOException, InterruptedException {
        this.zooKeeper = new ZooKeeper(CONNECT_STRING, TIME_OUT, watchedEvent -> {

        });
        //����ʹ��ZKʱ��Ϊ����δ��ɱ���
        while(ZooKeeper.States.CONNECTED != zooKeeper.getState()) {
            Thread.sleep(1000);
        }
    }

    /**
     * ������ʱ���нڵ㣨�������ر�ʱ�ڵ���Զ�ɾ����
     * @param serverName
     * @return
     * @throws Exception
     */
    public String regsterServer(String serverName) throws Exception {
        String result = zooKeeper.create("/clusterServer/server", serverName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(serverName+" server start....");
        return result;
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.regsterServer(args[0]);
        //���ֳ������У���ֹ����ֹͣ���е��½ڵ��Զ�ɾ��
        while (true) {

        }
    }
}
