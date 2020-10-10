package object;

import org.openjdk.jol.info.ClassLayout;

/**
 * Create by lzm on 2020/10/10
 */
public class Header {


    public static void main(String... args) {
        Camera m = new Camera();
        System.out.println(ClassLayout.parseInstance(m).toPrintable());
    }

    static class Camera {

        private String id;

        private String name;

    }


}
