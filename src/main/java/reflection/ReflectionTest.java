package reflection;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射demo
 */
public class ReflectionTest {


    private Class<?> clazz;


    /**
     * 加载字节码文件,三种方式
     */
    public void getClassByte() {

        //用类名
        clazz = Employee.class;

        //用对象
        Employee e = new Employee();
        clazz = e.getClass();


        // 使用全路径加载类
        try {
            clazz = Class.forName("reflection.Employee");
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }


    public void createEmployee() {
        /*
         * 使用字节码可以获取本类的 构造方法、成员变量、成员方法
         * 从而可以 创建此类对象并对他们进行操作
         */
        try {
            //使用Construtor来创建对象,可以选中任意构造方法
            Constructor c = clazz.getConstructor(String.class, int.class, String.class, int.class);
            Employee obj = (Employee) c.newInstance("阿童木", 110, "男", 16);
            System.out.println(obj);

            //直接使用类字节码创建对象,使用默认构造方法
            Employee e2 = (Employee) clazz.newInstance();
            System.out.println("=======");
            System.out.println(e2);


        } catch (Exception e1) {
        }
    }


    @Test
    public void testReflection() {


        ReflectionTest test = new ReflectionTest();

        test.getClassByte();  // 加载字节码文件

        test.createEmployee();  // 使用反射创建对象

    }


}
