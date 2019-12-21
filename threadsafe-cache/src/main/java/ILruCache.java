/**
 * Create by lzm on 2019/12/16
 */
public interface ILruCache {


    String get(String key);

    void set(String key, String val);

    void set(String key, String val, Long timeout);

    void clear();

    /**
     * 提供terminate方法，是为了停用cache时，立刻停止其中的线程池
     */
    void terminate();

    int size();


}
