package t1_lambda;

import t1_lambda.comparator.ICompareStrategy;
import t1_lambda.comparator.MyFunc;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Description: Lambda表达式，解决的问题是，使用匿名内部类时语法太复杂的问题
 * <p>
 * Created by lzm on 2020/10/22.
 */
public class LambdaTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(LambdaTest.class);

    private final List<Employe> list = new ArrayList<>();

    private final Employe e3 = new Employe("王五", 10300, 15);

    @Before
    public void init() {
        Employe e0 = new Employe("王二", 33000, 19);
        Employe e1 = new Employe("张三", 1000, 15);
        Employe e2 = new Employe("李四", 1200, 13);

        Employe e4 = new Employe("赵六", 10400, 17);
        Employe e5 = new Employe("田七", 10000, 13);
        Employe e6 = new Employe("田七", 10000, 12);
        list.add(e0);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
    }


    /**
     * version1: 匿名内部类
     */
    @Test
    public void testNoLambda() {

        ICompareStrategy comparator = new ICompareStrategy() {
            @Override
            public boolean compare(Employe e1, Employe e2) {
                return e1.getName().compareTo(e2.getName()) < 0;
            }
        };

        for (Employe e : list) {
            if (comparator.compare(e, e3)) {
                LOGGER.info("println e:{}", e);
            }
        }

    }

    /**
     * version2: Lambda表达式
     * <p>
     * Lambda表达式是一个匿名方法片段，与传统的参数相比：
     * 1. 其方法签名中只要求有参数列表
     * 2. 参数列表中的type使用类型推断得到，不需要显示声明
     * 3. 返回值在方法签名中被忽略
     * 4. 方法可以像一个参数那样被传递
     */
    @Test
    public void testLambda() {

        ICompareStrategy strategy = (e1, e2) -> e1.getName().compareTo(e2.getName()) < 0;

        for (Employe e : list) {
            if (strategy.compare(e, e3)) {
                LOGGER.info("println e:{}", e);
            }
        }

    }


    /**
     * eg: 匿名函数传递
     */
    @Test
    public void testTreeSet() {
        Employe e0 = new Employe("王二", 33000, 19);
        Employe e1 = new Employe("张三", 1000, 15);
        Employe e2 = new Employe("李四", 1200, 13);
        TreeSet<Employe> treeSet = new TreeSet<>((x1, x2) -> e1.getName().compareTo(e2.getName()));
        treeSet.add(e0);
        treeSet.add(e1);
        treeSet.add(e2);
        treeSet.add(e3);
    }


    /**
     * 方法可以像一个变量一样的被引用
     */
    @Test
    public void testFunctionParam() {
        MyFunc<String> myFunc = String::toUpperCase;
        LOGGER.info(parseStr(myFunc, "abcde"));

        Predicate<String> predicateFunc = e -> e.endsWith("test");
        LOGGER.info("checkParam:{}", checkParam(predicateFunc, "hellotest", System.out::println));
        LOGGER.info("checkParam:{}", checkParam(predicateFunc, "helloTest", System.out::println));
    }

    private boolean checkParam(Predicate<String> checkFunc, String param, Consumer<String> consumer) {
        consumer.accept("param:" + param);
        return checkFunc.test(param);
    }


    private String parseStr(MyFunc<String> myFunc, String source) {
        return myFunc.function(source);
    }

}
