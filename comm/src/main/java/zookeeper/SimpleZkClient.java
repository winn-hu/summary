package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * ���ڽڵ�ļ���watcher������ֻ����һ�Σ�����ʹ��ѭ�������ķ�ʽ�������ü�����
 * ��3.6.x�İ汾����ʹ��addWatch�������õݹ������
 */
public class SimpleZkClient {

    private static final String CONNECT_STRING = "172.17.23.79:2181,172.17.23.79:2182";

    private static ZooKeeper zooKeeper;

    @Before
    public void init() {
        try {
            //��ʱʱ�䵥λ������
            zooKeeper = new ZooKeeper(CONNECT_STRING, 15000, event -> {
                if (event.getType() == Watcher.Event.EventType.None && event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    System.out.println("Connectted successful.");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * �����ڵ�: create
     */
    @Test
    public void create() throws KeeperException, InterruptedException {
        //������1���ڵ�·���� 2��Ҫ�洢�����ݣ� 3���ڵ��Ȩ�ޣ� 4���ڵ������
        String nodePath = zooKeeper.create("/java/2183", "This is Java Node 2183.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(nodePath);
    }

    /**
     * ��ȡ�ӽڵ㣺 ls
     */
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", true);
        for (String child : children) {
            System.out.println("child: "+child);
        }
    }

    /**
     *
     * ͬ����ȡ�ڵ����ݣ� get
     */
    @Test
    public void getData() throws KeeperException, InterruptedException {
        String path = "/java";
        byte[] bytes = zooKeeper.getData(path, event -> {
            if (event.getType() == Watcher.Event.EventType.NodeDataChanged && path.equals(event.getPath())) {
                System.out.println("Date changed.");
            }
        }, null);
        System.out.printf("The data of %s is : %s \n",path, new String(bytes));
    }

    /**
     *
     * �첽��ȡ�ڵ����ݣ� get
     */
    @Test
    public void getDataAsync() {
        String path = "/java";
        zooKeeper.getData(path, false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.printf("The data of %s is : %s \n",path, new String(bytes));
            }
        },"1000");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setData() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.setData("/java", "This is from java.".getBytes(), -1);
        //���½ڵ��version���Զ�+1���ʣ�����ֵΪ0
        System.out.println(stat.getAversion());
    }

    @Test
    public void setDataThread() throws KeeperException, InterruptedException {
        String path = "/java";
        Stat stat = new Stat();
        //1,�Ȼ�ȡ�ڵ�ĵ�ǰ�汾
        zooKeeper.getData(path,false,stat);
        //2���ڵ�ǰ�汾�Ļ������޸Ľڵ�����
        zooKeeper.setData(path, "This is from java.".getBytes(), stat.getVersion());
    }

    @Test
    public void exists() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/java", false);
        if (stat == null) {
            System.out.println("Not Exists.");
        }else {
            System.out.println("Exists.");

        }
    }

    /**
     * ���Ҫɾ���Ľڵ����ӽڵ㣬�ᱨ��KeeperException$NotEmptyException: KeeperErrorCode = Directory not empty for
     * ����ڵ㲻���ڣ��ᱨ��KeeperException$NoNodeException: KeeperErrorCode = NoNode for
     */
    @Test
    public void delete() throws KeeperException, InterruptedException {
        //version = -1 : ƥ�����еİ汾
        zooKeeper.delete("/java/2182", -1);
    }

}
