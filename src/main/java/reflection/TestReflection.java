package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * lzm on 16/10/29.
 */
public class TestReflection {

    public static void main(String[] args) {

        //获取字节码文件,三种方式
        //用类名
        Class clazz = Employee.class;

        //用对象
        //        Employee e = new Employee();
        //        Class clazz3 = e.getClass();

        //类的全路径
        try {
            Class clazz2 = Class.forName("reflection.Employee");
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        }

        /*
         * 使用字节码可以获取本类的 构造方法、成员变量、成员方法
         * 从而可以 创建此类对象并对他们进行操作
         */
        try {
            //使用Construtor来创建对象,可以选中任意构造方法
            //获取指定的构造方法，需要传入构造方法的参数类型
            Constructor c = clazz.getConstructor(String.class, int.class, String.class, int.class);
            Employee obj = (Employee) c.newInstance("阿童木", 110, "男", 16);
            System.out.println(obj);

            //直接使用类字节码创建对象,使用默认的无参构造方法
            Employee e2 = (Employee) clazz.newInstance();
            System.out.println("=======");
            System.out.println(e2);

            //获取并操作对象的成员变量
            //如果获取私有的成员变量，则需使用getDeclaredField(),并把此field设置为可获取
            Field field = clazz.getDeclaredField("name");//getField("name");
            field.setAccessible(true);
            System.out.println(field.get(e2));

            //获取并调用对象的成员方法
            //普通成员方法调用时需要传入目标对象
            Method method = clazz.getMethod("setName", String.class);
            method.invoke(e2, "擎天柱");
            System.out.println(e2);
            //static方法传入目标对象是直接传入null即可
            Method method2 = clazz.getMethod("showName", String.class);
            method2.invoke(null, "aasssssssssa");

            //使用反射调用类中参数为数组的方法在调用时需要注意：
            //如果简单的传入一个object[]，则jvm将会把它拆开查看，若其中元素超过1个，则运行时报参数个数异常的错误
            //解决办法：1、在object[]外在包装一个数组
            //        2、把object[]强制类型转换成为Object对象
            Method main = clazz.getMethod("main", String[].class);
            main.invoke(null, new Object
                    []{new String[]{"111", "222"}});

        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }
}