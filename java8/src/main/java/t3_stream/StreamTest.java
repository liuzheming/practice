package t3_stream;

import t1_lambda.Employe;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Create by lzm on 2020/10/23
 * <p>
 * Stream是一个集合操作工具，它的作用在于与Java已有的各种数据结构相互配合，增强数据处理的能力
 */
public class StreamTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(StreamTest.class);

    private final List<Employe> list = new ArrayList<>();

    private final Employe e3 = new Employe("王五", 10300, 15);

    @Before
    public void init() {
        Employe e0 = new Employe("王二", 33000, 19);
        Employe e1 = new Employe("张三", 6000, 15);
        Employe e2 = new Employe("李四", 1200, 13);

        Employe e4 = new Employe("赵六", 4400, 17);
        Employe e5 = new Employe("田七", 10000, 12);
        Employe e6 = new Employe("田七", 10000, 12);
        list.add(e0);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
    }

    @Test
    public void testStream() {
        Predicate<Employe> predicateFunc = e -> e.getSalary() > 10000;
        Function<Employe, Employe> upSalary = e -> {
            e.setSalary(e.getSalary() + 5000);
            return e;
        };

        list.stream().map(upSalary)
                .filter(predicateFunc)
                .sorted()
                .limit(10)
                .skip(3)
                .distinct()
                .forEach(System.out::println);

        System.out.println(list.stream().allMatch(predicateFunc));

    }

}
