package t5_data;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Create by lzm on 2020/10/23
 * <p>
 * 原始的DateFormat API，是线程不安全的，解决方案是加锁 or ThreadLocal
 */
public class NewDateAPI {

    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMM-dd");


    @Test
    public void testNewDataAPI() {
        System.out.println(LocalDate.parse("202012-09", df));
    }

}
