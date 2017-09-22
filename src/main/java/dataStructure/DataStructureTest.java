package dataStructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/9/5.
 */
public class DataStructureTest {

    private int length = 100;

    private MyOrderArray array = new MyOrderArray(length);

    @Before
    public void fillArray() {
        for (int i = length - 1; i >= 0; i--) {
            array.insert(i);
        }
    }


    @Test
    public void testMyArray() {


        // 测试是否正确排序
        for (int i = 0; i < array.size(); i++) {
            Assert.assertEquals(array.get(i), Long.valueOf(i));
        }

        // 测试删除
        array.delete(8);
//        Assert.assertEquals(array.size(), length - 1);

        for (int i = 0; i < 20; i++) {
            Long n = (long) (Math.random() * 100);
            System.out.println(n);
            Assert.assertEquals(array.get(array.search(n)), n);
        }

        // 打印
        array.display();
    }

    @Test
    public void testBobbleSort() {

        long[] arr = new long[]{1, 234, 555, 333, 33, 343, 121};

//        SimpleSort.bubbleSort(arr);
        SimpleSort.bubbleSortDesc(arr);
        //System.out.println(Arrays.asList(arr));  因为这个数组是基本类型数组,所以直接用asList方法会有问题

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
