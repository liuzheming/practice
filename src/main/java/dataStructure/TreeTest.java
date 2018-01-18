package dataStructure;

import dataStructure.mylist.MyArrayList;
import dataStructure.mylist.MyLinkedList2;
import dataStructure.queue.MyCycleQueue;
import dataStructure.sort.MyOrderArray;
import dataStructure.sort.SimpleSort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/9/5.
 */
public class TreeTest {

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
        array.remove(8);
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
    public void testSimpleSort() {

        Long[] arr = new Long[]{1L, 234L, 555L, 333L, 33L, 343L, 121L};

//        SimpleSort.bubbleSort(arr);
//        SimpleSort.insertSort(arr);
        SimpleSort.selectSort(arr);
        //System.out.println(Arrays.asList(arr));  因为这个数组是基本类型数组,所以直接用asList方法会有问题
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void testQueue() {
        MyCycleQueue queue = new MyCycleQueue(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.remove();
        queue.remove();
        queue.insert(6);
        queue.insert(7);
//        queue.insert(8);

        System.out.println(queue.toString());

    }

    @Test
    public void testMyLinkedList() {

        MyLinkedList2<Integer> linkedList = new MyLinkedList2<>();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(16);
        linkedList.add(14);
        linkedList.add(166);
        linkedList.add(14);
        linkedList.set(0,14);
        linkedList.add(1,14);
        linkedList.add(5,14);

        linkedList.remove(0);
        linkedList.remove(1);
        linkedList.remove(2);
//        Iterator<Integer> it = linkedList.iterator();
//        while (it.hasNext()) {
        System.out.println(linkedList);
//        }

    }


    @Test
    public void testMyArrayList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(11);
        list.add(11);

        list.set(0, 1110);
        list.set(1, 1111);
        list.set(2, 1112);

        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);


        System.out.print(list);

    }


}
