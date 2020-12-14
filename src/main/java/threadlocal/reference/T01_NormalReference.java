package threadlocal.reference;

import sun.jvm.hotspot.jdi.IntegerTypeImpl;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Create by lzm on 2020/10/23
 */
public class T01_NormalReference {

    public static void main(String[] args) {

        // 强，
        StringBuffer sb = new StringBuffer();

        /* 软，当GC发现内存不够，直接回收softReference */
        /* 适合做缓存 */
        SoftReference<byte[]> softBytesRef = new SoftReference<>(new byte[1024]);

        // 弱，GC直接回收
        // ThreadLocal中有使用
        WeakReference<Integer> weakRef = new WeakReference<>(new Integer(1));

        // 虚，GC直接回收，比弱引用更弱，跟不存在差不多


    }


}
