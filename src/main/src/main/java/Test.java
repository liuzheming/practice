/**
 * Description:
 * <p>
 * Created by lzm on 2018/1/4.
 */
public class Test {


    int x;

    public static void main(String... args) {

        int n = 1;
        Test test = new Test();
        test.change(test);
        System.out.println("outer : n=" + test.x);
    }


    public void change(Test i) {
        i.x = 5;
        System.out.println("inter : i=" + i.x);
    }


}
