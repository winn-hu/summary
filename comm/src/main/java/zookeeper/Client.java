package zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取并监听注册的服务器列表
 */
public class Client {

    private static final String CONNECT_STRING = "172.17.23.79:2181,172.17.23.79:2182";
    private static final int TIME_OUT = 15000;

    private ZooKeeper zooKeeper;

    public Client() throws IOException {
        this.zooKeeper = new ZooKeeper(CONNECT_STRING, TIME_OUT, watchedEvent -> {
            System.out.println("watcher works.");
            try {
                getServers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void getServers() throws Exception {
        List<String> servers = new ArrayList<>();
        List<String> children = zooKeeper.getChildren("/clusterServer", true, null);
        for (String child : children) {
            String server = getData("/clusterServer/"+child);
            servers.add(server);
        }
        System.out.println(servers);
    }

    public String getData(String path) throws Exception {
        byte[] data = zooKeeper.getData(path, true, null);
        return new String(data);
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.getServers();
        while (true) {

        }
    }
}
