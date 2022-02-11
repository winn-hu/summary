package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
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
            //����ʹ��ZKʱ��Ϊ����δ��ɱ���
            while(ZooKeeper.States.CONNECTED != zooKeeper.getState()) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * �����ڵ�: create
     */
    @Test
    public void create() throws KeeperException, InterruptedException {
        //������1���ڵ�·���� 2��Ҫ�洢�����ݣ� 3���ڵ��Ȩ�ޣ� 4���ڵ������
        String nodePath = zooKeeper.create("/lock", "This is lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(nodePath);
    }

    /**
     * ͨ��������ʱ�ڵ㣬ʵ�ַ�����֮��Ķ�ռ��
     */
    @Test
    public void singleLock() {
        try {
            //������1���ڵ�·���� 2��Ҫ�洢�����ݣ� 3���ڵ��Ȩ�ޣ� 4���ڵ������
            String nodePath = zooKeeper.create("/lock", "This is Lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            //�����ɹ������൱��ӵ�ж�ռ�������Խ��������߼�
            //TODO ҵ���߼�
            System.out.println(nodePath);
            //ҵ���߼�������ɾ���ڵ㣬���ͷ�����Դ
            zooKeeper.delete("/lock", -1);
        } catch (Exception e) {
            //�����ڵ�ʧ�ܣ����µ��ã�ֱ�������ɹ�
            if (e instanceof KeeperException && "NODEEXISTS".equals(((KeeperException)e).code().name())) {
                System.out.println("Node exists.");
                singleLock();
            }else {
                e.printStackTrace();
            }
        }
    }

    /**
     * ͨ��������ʱ�ڵ㣬ʵ�ַ�����֮��Ķ�ռ��
     */
    @Test
    public void singleLock2() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/lock", false);
        //����ڵ��Ѿ����ڣ��ȴ�����������ɾ���ڵ㡣�����ȴ������������ͷ�����Դ
        while(stat != null) {  }

        //������1���ڵ�·���� 2��Ҫ�洢�����ݣ� 3���ڵ��Ȩ�ޣ� 4���ڵ������
        String nodePath = zooKeeper.create("/lock", "This is Lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //�����ɹ������൱��ӵ�ж�ռ�������Խ��������߼�
        //TODO ҵ���߼�
        System.out.println(nodePath);
        //ҵ���߼�������ɾ���ڵ㣬���ͷ�����Դ
        zooKeeper.delete("/lock", -1);
    }

    /**
     * ͨ��������ʱʱ��ڵ㣬ʵ�ַ�����֮���ʱ����
     */
    @Test
    public void lock() throws KeeperException, InterruptedException {
        //������1���ڵ�·���� 2��Ҫ�洢�����ݣ� 3���ڵ��Ȩ�ޣ� 4���ڵ������
        String nodePath = zooKeeper.create("/lock/sublock", "This is sub lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(nodePath);
        //�����ɹ������൱��ӵ�ж�ռ�������Խ��������߼�
        while(true) {
            List<String> children = zooKeeper.getChildren("/lock", false);
            Collections.sort(children);
            if (nodePath.equals("/lock/"+children.get(0))){
                //TODO ҵ���߼�
                System.out.println("TODO  Logic.");
                break;
            }
        }

        //ҵ���߼�������ɾ���ڵ㣬���ͷ�����Դ
        zooKeeper.delete(nodePath, -1);
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
        zooKeeper.getData(path, false, (i, s, o, bytes, stat) -> System.out.printf("The data of %s is : %s \n",path, new String(bytes)),"1000");

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
        List<String> children = zooKeeper.getChildren("/lock", false);
        children.sort(String::compareTo);
        System.out.println("/lock/"+children.get(0));
        //version = -1 : ƥ�����еİ汾
        zooKeeper.delete("/lock/"+children.get(0), -1);
    }



}
