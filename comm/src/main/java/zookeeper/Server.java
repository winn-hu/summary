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
        //避免使用ZK时因为连接未完成报错
        while(ZooKeeper.States.CONNECTED != zooKeeper.getState()) {
            Thread.sleep(1000);
        }
    }

    /**
     * 创建临时序列节点（服务器关闭时节点会自动删除）
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
        //保持程序运行，防止程序停止运行导致节点自动删除
        while (true) {

        }
    }
}
