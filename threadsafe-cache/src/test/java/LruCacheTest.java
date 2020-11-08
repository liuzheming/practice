import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;


/**
 * Create by lzm on 2019/12/16
 * <p>
 * 本地缓存demo的测试类
 */
@FixMethodOrder(MethodSorters.JVM)
public class LruCacheTest {

    private static Logger LOG = LoggerFactory.getLogger(LruCacheTest.class); // .getLogger(LruCacheTest.class.getName
    // ());

    private LinkedHashMap lhm = new LinkedHashMap();


    /**
     * 测试基本功能set、get方法
     */
    @Test
    public void testSetGet() {
        String key = "www";
        String val = "baidu";
        LruCacheImpl cache = LruCacheImpl.getInstance();
        cache.set(key, val);
        Assert.assertEquals(val, cache.get(key));
        try {
            cache.set(null, "sss", 1000L);
        } catch (CacheException e) {
            System.out.println("set 参数为null，抛出CacheException异常，符合预期");
        }
        cache.terminate();
    }

    /**
     * 测试容量设定
     */
    @Test
    public void testCapacity() {
        LruCacheImpl cache = LruCacheImpl.getInstance();
        cache.set("1", "1", 1000L);
        cache.set("2", "2", 1000L);
        cache.set("3", "3", 1000L);
        cache.set("4", "4", 1000L);
        Assert.assertEquals(3, cache.size());
        Assert.assertNull(cache.get("1"));
        cache.terminate();
    }


    /**
     * 测试缓存失效策略
     */
    @Test
    public void testLRU() {
        LruCacheImpl cache = LruCacheImpl.getInstance();
        cache.set("1", "1");
        cache.set("2", "2");
        cache.set("3", "3");
        cache.get("1");
        cache.get("2");
        cache.set("4", "4");
        Assert.assertNull(cache.get("3"));
        cache.terminate();
    }


    /**
     * 测试缓存生存时间
     */
    @Test
    public void testKeepalive() throws Exception {

        LruCacheImpl cache = LruCacheImpl.getInstance();
        cache.clear();
        // 添加元素，并且设定好失效时间
        cache.set("1", "1", 100L);
        cache.set("2", "2", 100L);
        cache.set("3", "3", 100L);
        // 等待到超时
        Thread.sleep(5000L);
        // 判断cache中元素是否失效
        Assert.assertEquals(0, cache.size());
        cache.terminate();
    }


    @Test
    public void testPersistence() throws Exception {
        LruCacheImpl cache = LruCacheImpl.getInstance();
        cache.set("key1", "val1");
        cache.set("key2", "val2");
        cache.set("key3", "val3");
        Thread.sleep(5000L);
        cache.terminate();
    }

    /**
     * 测试线程安全
     * <p>
     * 启动两个线程，一个线程是不断向cache中写入某个键值对，
     * 另一个线程，不断从cache中读取此键值对。
     * 两个线程并行操作的情况下，读线程获取的数据不出现脏数据，即认为是线程安全
     */
    @Test
    public void testThreadSafe() throws Exception {
        LruCacheImpl cache = LruCacheImpl.getInstance();
        new Thread(() -> {
            System.out.println("线程1-1执行,不断向cache中写入键值对KEY-VAL");
            for (int i = 0; i < 100000; i++) {
                LOG.info("线程1-1执行,不断向cache中写入键值对KEY-VAL");
                cache.set("KEY", "VAL");
            }
        }).start();
        new Thread(() -> {
            System.out.println("线程1-2执行,不断向cache中写入键值对KEY-VAL");
            for (int i = 0; i < 100000; i++) {
                LOG.info("线程1-2执行,不断向cache中写入键值对KEY-VAL");
                cache.set("KEY", "VAL");
            }
        }).start();
        new Thread(() -> {
            System.out.println("线程1-3执行,不断向cache中写入键值对KEY-VAL");
            for (int i = 0; i < 100000; i++) {
                LOG.info("线程1-3执行,不断向cache中写入键值对KEY-VAL");
                cache.set("KEY", "VAL");
            }
        }).start();
        new Thread(() -> {
            System.out.println("线程1-4执行,不断向cache中写入键值对KEY-VAL");
            for (int i = 0; i < 100000; i++) {
                LOG.info("线程1-4执行,不断向cache中写入键值对KEY-VAL");
                cache.set("KEY", "VAL");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                LOG.info("线程2执行中...");
                Assert.assertNotNull(cache.get("KEY"));
                Assert.assertEquals("VAL", cache.get("KEY"));
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                LOG.info("线程3执行中...");
                Assert.assertNotNull(cache.get("KEY"));
                Assert.assertEquals("VAL", cache.get("KEY"));
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                LOG.info("线程4执行中...");
                Assert.assertNotNull(cache.get("KEY"));
                Assert.assertEquals("VAL", cache.get("KEY"));
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                LOG.info("线程5执行中...");
                Assert.assertNotNull(cache.get("KEY"));
                Assert.assertEquals("VAL", cache.get("KEY"));
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                LOG.info("线程6执行中...");
                Assert.assertNotNull(cache.get("KEY"));
                Assert.assertEquals("VAL", cache.get("KEY"));
            }
        }).start();
        Thread.sleep(10000L);
    }

}
