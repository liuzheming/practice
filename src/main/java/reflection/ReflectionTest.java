package reflection;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射demo
 */
public class ReflectionTest {


    private Class<?> clazz;

    public static void main(String[] args) {

        //获取字节码文件,三种方式
        //用类名
        Class clazz = Employee.class;

        //用对象
//        Employee e = new Employee();
//        Class clazz3 = e.getClass();

        //类的全路径
        try {
            Class clazz2 = Class.forName("Employee");
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        }


        /*
         * 使用字节码可以获取本类的 构造方法、成员变量、成员方法
         * 从而可以 创建此类对象并对他们进行操作
         */
        try {
            //使用Construtor来创建对象,可以选中任意构造方法
            Constructor c = clazz.getConstructor(String.class, int.class, String.class, int.class);
            Employee obj = (Employee) c.newInstance("阿童木", 110, "男", 16);
            System.out.print(obj);

            //直接使用类字节码创建对象,使用默认构造方法
            Employee e2 = (Employee) clazz.newInstance();
            System.out.print("=======");
            System.out.print(e2);


        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e2) {
        } catch (IllegalAccessException e3) {
        } catch (InvocationTargetException e4) {
        }


    }


    /**
     * 加载字节码文件,三种方式
     */
    @Test
    private void getClassByte() {

        //用类名
        clazz = Employee.class;

        //用对象
        Employee e = new Employee();
        clazz = e.getClass();


        // 使用全路径加载类
        try {
            clazz = Class.forName("Employee");
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }

}
