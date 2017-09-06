package dataStructure;

import org.junit.Test;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/9/5.
 */
public class DataStructureTest {


    @Test
    public void testMyArray() {

        MyArray array = new MyArray(10);
        array.insert(1);
        array.insert(2);
        array.insert(3);
        array.insert(4);
        array.insert(5);
        array.delete(3);
        array.update(1,110);
        System.out.println(array.get(2));
        array.display();

    }

}
