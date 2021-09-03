package zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取并监听注册的服务器列表
 */
public class Client {

    private static final String CONNECT_STRING = "172.17.23.79:2181,172.17.23.79:2182";
    private static final int TIME_OUT = 15000;

    private ZooKeeper zooKeeper;

    public Client() throws Exception {
        this.zooKeeper = new ZooKeeper(CONNECT_STRING, TIME_OUT, watchedEvent -> {
            //只监听“/clusterServer”的子节点变化
            if(watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged
                    && watchedEvent.getPath().startsWith("/clusterServer")) {
                System.out.println("watcher works.");
                getServers();
            }
        });
    }


    public void getServers() {
        List<String> servers = new ArrayList<>();
        try {
            List<String> children = zooKeeper.getChildren("/clusterServer", true, null);
            for (String child : children) {
                String server = getData("/clusterServer/"+child);
                servers.add(server);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
