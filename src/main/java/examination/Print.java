package examination;


/**
 * 练习
 */
class Print {

    static boolean out(char c) {
        System.out.println(c);
        return true;
    }

    // 考察for循环的执行顺序
    public static void main(String... args) {
        int i = 0;
        for (Print.out('A'); Print.out('B') && (i < 2); Print.out('C')) {
            i++;
            Print.out('D');
        }

        new Print().testStackOverflow("a", "b");

    }

    void waitForSignal() {
        Object obj = new Object();
        synchronized (Thread.currentThread()) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.notify();
        }
    }

    int i;

    void testStackOverflow(String a, String b) {
        System.out.println(++i);
//        short[] arr = new short[]{1, 2};
        testStackOverflow("a", "b");

    }

    void testFinal() {
        byte a = 1, b = 2, c, d;
        final byte x = 120, y = 1;
        // 首先2个byte类型相加，会自动提升为int，计算的结果也是int因此需要强制转化会byte，
        // 而被fianl修饰的变量不会改变，会被JVM优化，两个final修饰的变量相加的时候结果会
        // 根据左边类型进行转化,作为这两个final变量运算的最终结果
        byte n = x + y;
        System.out.println(n);
    }
}