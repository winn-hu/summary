package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *
 * ͨ��Zookeeperʵ�ַ�����֮���ʱ����
 *
 */
public class SeqLock {

    private static final String CONNECT_STRING = "172.17.23.79:2181,172.17.23.79:2182";

    private static ZooKeeper zooKeeper;
    private String thispath;

    public SeqLock() throws IOException {
        //��ʱʱ�䵥λ������
        zooKeeper = new ZooKeeper(CONNECT_STRING, 15000, event -> {
            //������/lock�����ӽڵ�仯������з������ͷ������ж��Լ��Ƿ��ȡ��
            if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged
                    && event.getPath().startsWith("/lock")) {
                try {
                    List<String> children = zooKeeper.getChildren("/lock", false);
                    if (children.size() > 0) {
                        Collections.sort(children);
                        String fistNode = "/lock/"+children.get(0);
                        if (fistNode.equals(thispath)){
                            doSomethingAndDelNode();
                        }
                    }

                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * ͨ��������ʱʱ��ڵ�ע��ʱ�������������������б�
     */
    public void lock() throws KeeperException, InterruptedException {
        thispath = zooKeeper.create("/lock/sublock", "This is sub lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(thispath);
        List<String> children = zooKeeper.getChildren("/lock", false);
        //���ֻ��һ���ӽڵ㣬˵������Դ����ǰ���������С�����ӽڵ㲻ֹһ����˵������Դ�Ѿ�����������������
        if(children.size() == 1) {
            doSomethingAndDelNode();
        }
    }

    private void doSomethingAndDelNode() throws InterruptedException, KeeperException {
        //TODO ҵ���߼�
        System.out.println("TODO  Logic.");
        //ҵ���߼�������ɾ���ڵ㣬���ͷ�����Դ
        zooKeeper.delete(thispath, -1);
    }

    public static void main(String[] args)  {
        try {
            SeqLock lock = new SeqLock();
            lock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

