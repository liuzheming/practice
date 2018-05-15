package proxy;

import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.List;

/**
 * Description: 测试jdk工具动态生成代理对象
 * <p>
 * Created by lzm on 2018/1/15.
 */

public class ProxyTest {


    public static void main(String... args) {


        List list = ProxyFactory.getProxy(args, List.class);


        // 取得代理类的信息
        Class<?> clazzProxy = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);

        // 打印构造方法和参数
        Constructor[] constructors = clazzProxy.getConstructors();
        for (Constructor cons : constructors) {
            printExecutable(cons);
        }

        System.out.println("-----------------------------");
        // 打印成员方法和参数
        Method[] methods = clazzProxy.getMethods();
        for (Method method : methods) {
            printExecutable(method);
        }

        System.out.println("-----------------------------");
        try {
            String obj = (String) clazzProxy.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void printExecutable(Executable executable) {
        StringBuilder strBuilder = new StringBuilder(executable.getName());
        strBuilder.append("(");
        Parameter[] params = executable.getParameters();
        for (Parameter para : params) {
            strBuilder.append(para).append(",");
        }
        if (params.length != 0) {
            strBuilder = strBuilder.deleteCharAt(strBuilder.length() - 1);
        }
        strBuilder.append(")");
        System.out.println(strBuilder.toString());
    }

}
