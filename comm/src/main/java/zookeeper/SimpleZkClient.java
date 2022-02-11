package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * 对于节点的监听watcher操作，只监听一次，可以使用循环监听的方式进行永久监听；
 * 在3.6.x的版本可以使用addWatch方法永久递归监听。
 */
public class SimpleZkClient {

    private static final String CONNECT_STRING = "172.17.23.79:2181,172.17.23.79:2182";

    private static ZooKeeper zooKeeper;

    @Before
    public void init() {
        try {
            //超时时间单位：毫秒
            zooKeeper = new ZooKeeper(CONNECT_STRING, 15000, event -> {
                if (event.getType() == Watcher.Event.EventType.None && event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    System.out.println("Connectted successful.");
                }
            });
            //避免使用ZK时因为连接未完成报错
            while(ZooKeeper.States.CONNECTED != zooKeeper.getState()) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建节点: create
     */
    @Test
    public void create() throws KeeperException, InterruptedException {
        //参数：1，节点路径； 2，要存储的数据； 3，节点的权限； 4，节点的类型
        String nodePath = zooKeeper.create("/lock", "This is lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(nodePath);
    }

    /**
     * 通过创建临时节点，实现服务器之间的独占锁
     */
    @Test
    public void singleLock() {
        try {
            //参数：1，节点路径； 2，要存储的数据； 3，节点的权限； 4，节点的类型
            String nodePath = zooKeeper.create("/lock", "This is Lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            //创建成功，则相当于拥有独占锁，可以进行以下逻辑
            //TODO 业务逻辑
            System.out.println(nodePath);
            //业务逻辑结束后，删除节点，即释放锁资源
            zooKeeper.delete("/lock", -1);
        } catch (Exception e) {
            //创建节点失败，重新调用，直至创建成功
            if (e instanceof KeeperException && "NODEEXISTS".equals(((KeeperException)e).code().name())) {
                System.out.println("Node exists.");
                singleLock();
            }else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过创建临时节点，实现服务器之间的独占锁
     */
    @Test
    public void singleLock2() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/lock", false);
        //如果节点已经存在，等待其它服务器删除节点。即：等待其它服务器释放锁资源
        while(stat != null) {  }

        //参数：1，节点路径； 2，要存储的数据； 3，节点的权限； 4，节点的类型
        String nodePath = zooKeeper.create("/lock", "This is Lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //创建成功，则相当于拥有独占锁，可以进行以下逻辑
        //TODO 业务逻辑
        System.out.println(nodePath);
        //业务逻辑结束后，删除节点，即释放锁资源
        zooKeeper.delete("/lock", -1);
    }

    /**
     * 通过创建临时时序节点，实现服务器之间的时序锁
     */
    @Test
    public void lock() throws KeeperException, InterruptedException {
        //参数：1，节点路径； 2，要存储的数据； 3，节点的权限； 4，节点的类型
        String nodePath = zooKeeper.create("/lock/sublock", "This is sub lock.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(nodePath);
        //创建成功，则相当于拥有独占锁，可以进行以下逻辑
        while(true) {
            List<String> children = zooKeeper.getChildren("/lock", false);
            Collections.sort(children);
            if (nodePath.equals("/lock/"+children.get(0))){
                //TODO 业务逻辑
                System.out.println("TODO  Logic.");
                break;
            }
        }

        //业务逻辑结束后，删除节点，即释放锁资源
        zooKeeper.delete(nodePath, -1);
    }

    /**
     * 获取子节点： ls
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
     * 同步获取节点内容： get
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
     * 异步获取节点内容： get
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
        //更新节点后，version会自动+1。故，返回值为0
        System.out.println(stat.getAversion());
    }

    @Test
    public void setDataThread() throws KeeperException, InterruptedException {
        String path = "/java";
        Stat stat = new Stat();
        //1,先获取节点的当前版本
        zooKeeper.getData(path,false,stat);
        //2，在当前版本的基础上修改节点内容
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
     * 如果要删除的节点有子节点，会报错：KeeperException$NotEmptyException: KeeperErrorCode = Directory not empty for
     * 如果节点不存在，会报错：KeeperException$NoNodeException: KeeperErrorCode = NoNode for
     */
    @Test
    public void delete() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/lock", false);
        children.sort(String::compareTo);
        System.out.println("/lock/"+children.get(0));
        //version = -1 : 匹配所有的版本
        zooKeeper.delete("/lock/"+children.get(0), -1);
    }



}
