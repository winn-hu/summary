package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *
 * 通过Zookeeper实现服务器之间的时序锁
 *
 */
public class SeqLock {

    private static final String CONNECT_STRING = "172.17.23.79:2181,172.17.23.79:2182";

    private static ZooKeeper zooKeeper;
    private String thispath;

    public SeqLock() throws IOException {
        //超时时间单位：毫秒
        zooKeeper = new ZooKeeper(CONNECT_STRING, 15000, event -> {
            //监听“/lock”的子节点变化。如果有服务器释放锁，判断自己是否获取锁
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
     * 通过创建临时时序节点注册时序锁，并监听服务器列表
     */
    public void lock() throws KeeperException, InterruptedException {
        thispath = zooKeeper.create("/lock/sublock", "This is sub lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(thispath);
        List<String> children = zooKeeper.getChildren("/lock", false);
        //如果只有一个子节点，说明锁资源被当前服务器持有。如果子节点不止一个，说明锁资源已经被其它服务器持有
        if(children.size() == 1) {
            doSomethingAndDelNode();
        }
    }

    private void doSomethingAndDelNode() throws InterruptedException, KeeperException {
        //TODO 业务逻辑
        System.out.println("TODO  Logic.");
        //业务逻辑结束后，删除节点，即释放锁资源
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

