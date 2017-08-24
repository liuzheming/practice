package instanceInit;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/8/17.
 */
public class SubClass extends SupClass {

//    static B staticB = new B();

    B b = new B();

    public SubClass() {
        // super() 或者 this() 都必须放在构造函数的第一行,也就是说,this() 和 super()
        // 不能同时出现在一个构造方法里
        super();
        System.out.println("sub init...");
    }

    void work() {
        System.out.println("sub work...");
    }


    public static void main(String... args) {
        SubClass sub = new SubClass();
    }


}
