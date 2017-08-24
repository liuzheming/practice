package instanceInit;

/**
 * Description: 测试类的各部分实例化顺序
 * 父类静态域 -> 子类静态域 ->父类成员变量 -> 父类构造方法 -> 子类成员变量 -> 构造方法其余部分
 * <p>
 * Created by lzm on 2017/8/17.
 */
class SupClass {

//    public static A staticA = new A();

    public A a = new A();

    public SupClass() {
        System.out.println("sup init...");
    }

    void work() {
        System.out.println("sup work...");
    }

}
