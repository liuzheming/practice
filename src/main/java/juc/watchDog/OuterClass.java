package juc.watchDog;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/25.
 */
public class OuterClass {

    public class InnerClass {

    }


    public static void main(String... args) {

        OuterClass outer = new OuterClass();
        InnerClass inner = outer.new InnerClass();

    }

}