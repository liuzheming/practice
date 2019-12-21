import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by lzm on 2019/12/16
 * <p>
 * 题目描述：请设计并实现一个小型本地缓存，实现以下功能：
 * （1）基本的set/get功能及良好的对外接口；
 * （2）可设置缓存容量上限；
 * （3）可以设置缓存对象的有效期；
 * （4）实现任意一种缓存淘汰策略；
 * （5）线程安全且保持缓存的一致性；注意:value可能在运行时动态更新，需要考虑缓存对象在更新/过期/淘汰时是否需要持久化。
 * （6）程序重启后重新加载缓存到最近一次退出时的状态；
 * <p>
 * <p>
 * 本地缓存demo：
 * 1. 支持set/get
 * 2. 支持初始化时设定缓存容量
 * 3. 支持设定timeout
 * 4. 支持LRU淘汰策略
 * 5. 确保线程安全
 * 6. 停机前持久化，重启时读取持久化数据
 */
public class LruCacheImpl implements ILruCache {

    /**
     * Cache容量
     */
    private Integer capacity;

    /**
     * 当前缓存中的元素个数
     */
    private Integer size;

    /**
     * 线程池，提供负责清理过期缓存、dump快照文件的线程
     */
    private ScheduledThreadPoolExecutor executor;

    /**
     * 利用hash表支持时间复杂度O(1)的查找
     */
    private HashMap<String, Node> map;

    /**
     * 双向链表头
     */
    private Node head;

    /**
     * 双向链表尾
     */
    private Node tail;

    /**
     * 缓存路径，从配置文件中加载
     */
    private String snapshotPath;

    /**
     * 内部线程池大小
     */
    private static final int THREAD_POOL_SIZE = 2;

    /**
     * dump快照的线程启动延迟时间
     */
    private static final long DUMP_THREAD_INITIAL_DELAY = 500L;

    /**
     * dump快照线程每次执行的间隔时间
     */
    private static final long DUMP_THREAD_PERIOD = 500L;

    /**
     * 清理过期数据线程启动延迟时间
     */
    private static final long CLEAR_THREAD_INITIAL_DELAY = 2L;

    /**
     * 清理过期数据线程每次执行间隔时间
     */
    private static final long CLEAR_DUMP_THREAD_PERIOD = 2L;

    /**
     * 配置文件路径
     */
    private static final String CONFIG_PATH = "config.properties";

    /**
     * 单例对象
     */
    private static LruCacheImpl instance;

    public static LruCacheImpl getInstance() {
        if (instance == null) {
            synchronized (LruCacheImpl.class) {
                if (instance == null) {
                    instance = new LruCacheImpl();
                }
            }
        }
        return instance;
    }

    private LruCacheImpl() {
        this.map = new HashMap<>();
        this.size = 0;
        loadConfig();
        init();
    }

    /**
     * 初始化方法，启动清理过期元素线程和快照线程
     */
    private void init() {
        this.executor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);
        loadSnapshot();
        startCleanThread();
//        startPersistentThread();
    }

    /**
     * 加载配置文件
     */
    private void loadConfig() {
        InputStream in = null;
        Properties p = new Properties();
        try {
            in = LruCacheImpl.class.getClassLoader().getResourceAsStream(CONFIG_PATH);
            p.load(in);
            if (p.getProperty("snapshotPath") == null || p.getProperty("capacity") == null) {
                throw new CacheException("");
            }
            this.snapshotPath = p.getProperty("snapshotPath");
            this.capacity = Integer.parseInt(p.getProperty("capacity"));
        } catch (IOException e) {
            throw new CacheException("加载配置文件失败");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void terminate() {
        this.executor.shutdown();
        instance = null;
    }

    /**
     * 如果存在快照，则init时读取快照文件
     */
    private void loadSnapshot() {
        File file = new File(snapshotPath);
        if (file.exists() && file.isFile()) {
            InputStreamReader reader = null;
            BufferedReader bufferedReader = null;
            try {
                reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                bufferedReader = new BufferedReader(reader);
                String lineTxt;
                StringBuilder sb = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt);
                }
                if (sb.length() > 0) {
                    ObjectMapper mapper = new ObjectMapper();
                    JavaType type = mapper.getTypeFactory().constructParametricType(Map.class, String.class, String.class);
                    Map<String, String> map = mapper.readValue(sb.toString(), type);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        this.set(entry.getKey(), entry.getValue());
                    }
                }
            } catch (IOException e) {
                throw new CacheException("loadSnapshot异常", e);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 转换为Json格式
     */
    private String toJsonStr() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        for (Map.Entry<String, Node> entry : map.entrySet()) {
            node.put(entry.getKey(), entry.getValue().val);
        }
        return node.toString();
    }

    /**
     * 提交定期清理过期元素的任务
     */
    private void startCleanThread() {

        executor.scheduleAtFixedRate(() -> {
            synchronized (LruCacheImpl.class) {
                Node node = head;
                while (node != null) {
                    if (!Node.ALWAYS.equals(node.keepalive)) {
                        if (node.keepalive + node.createTime < System.currentTimeMillis()) {
                            remove(node, true);
                        }
                    }
                    node = node.next;
                }
                dump();
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

//    /**
//     * 数据做持久化处理，修改cache时触发此方法
//     */
//    private void startPersistentThread() {
//        /*
//         提交定期保存缓存快照的任务
//         */
//        executor.scheduleAtFixedRate(this::dump, DUMP_THREAD_INITIAL_DELAY, DUMP_THREAD_PERIOD, TimeUnit.MILLISECONDS);
//    }

    /**
     * dump快照文件
     */
    private void dump() {
        String jsonStr = toJsonStr();
        File file = new File(snapshotPath);
        /* file不存在，创建file */
        if (!file.exists()) {
            try {
                boolean flag = file.createNewFile();
            } catch (IOException e) {
                throw new CacheException("创建快照文件失败", e);
            }
        }

        /* 向file中写入jsonStr */
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(jsonStr);
            fw.flush();
        } catch (IOException e) {
            throw new CacheException("写入数据到文件失败", e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向缓存中添加数据
     *
     * @param key key
     * @param val val
     */
    @Override
    public void set(String key, String val) {
        set(key, val, Node.ALWAYS);
    }

    /**
     * 向缓存中添加数据，并设定缓存失效时间
     *
     * @param key       key
     * @param val       val
     * @param keepalive 缓存生存时间ms
     */
    @Override
    public void set(String key, String val, Long keepalive) {
        synchronized (LruCacheImpl.class) {
            if (key == null) {
                throw new CacheException("param [key] should not be null");
            }
            Node node = map.get(key);
            if (node != null) {
                /* 如果已存在,替换原val,并将node移动到head位置 */
                node.val = val;
                node.keepalive = keepalive;
                remove(node, false);
            } else {
                /* 如果不存在,容量是否已满 */
                if (map.size() >= capacity) {
                    remove(tail, true);
                }
                /* 创建一个node,并将其置于队头,同时放入map中 */
                node = new Node(key, val, keepalive);
                map.put(key, node);
                size++;
            }
            setHead(node);
            dump();
        }
    }


    /**
     * 根据key查询val
     *
     * @param key key
     * @return val
     */
    @Override
    public String get(String key) {
        synchronized (LruCacheImpl.class) {
            Node node = map.get(key);
            if (node != null) {
                remove(node, false);
                setHead(node);
                return node.val;
            }
            return null;
        }
    }

    /**
     * 向双向链表中插入头节点
     *
     * @param node 新的头节点
     */
    private void setHead(Node node) {
        if (head != null) {
            node.next = head;
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = node;
        }
    }

    /**
     * 删除缓存中的元素
     *
     * @param node 被删除的节点
     * @param flag 是否连map里的数据一同删除
     */
    private void remove(Node node, boolean flag) {
        // 如果不是队头
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        // 如果不是队尾
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        node.next = null;
        node.prev = null;
        if (flag) {
            map.remove(node.key);
            size--;
        }
    }


    @Override
    public String toString() {
        String map = "[";
        Node node = head;
        while (node != null) {
            map += "(" + node.key + "-" + node.val + ") ";
            node = node.next;
        }
        map += "]";
        return "LruCache{" +
                "capacity=" + capacity +
                ", map=" + map +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }

    /**
     * 返回当前cache元素个数
     */
    public int size() {
        return size;
    }


    public void clear() {
        synchronized (LruCacheImpl.class) {
            Node current = this.head;
            while (current != null) {
                remove(current, true);
                current = head;
            }
            dump();
        }
    }


    /**
     * 双向链表的Node节点
     */
    private static class Node {

        private Node prev;

        private Node next;

        private String key;

        private String val;

        private final Long createTime;

        private Long keepalive;

        private static Long ALWAYS = -1L;

        private Node(String key, String val, Long keepalive) {
            this.key = key;
            this.val = val;
            this.createTime = System.currentTimeMillis();
            this.keepalive = keepalive;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", val='" + val + '\'' +
                    '}';
        }
    }


    /**
     * 自定义工厂，对LruCache内线程池中的线程进行命名
     */
    class LruCacheThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final static String namePrefix = "LruCache-";

        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            if (t.isDaemon())
                t.setDaemon(true);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

}
