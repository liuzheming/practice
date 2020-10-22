package t2_defaultfunction;

/**
 * Create by lzm on 2020/10/23
 */
public interface MyInterface {

    default String getName() {
        return "my name";
    }

}
