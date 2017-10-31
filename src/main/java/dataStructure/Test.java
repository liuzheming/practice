package dataStructure;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/10/16.
 */
public class Test {

    public static void main(String... args) {

//        In in = new In("http://www.ftchinese.com/story/001074671");
//        In in = new In("https://www.lagou.com/jobs/3659682.html");

//        System.out.println(in.readAll());

//        BST<Integer> tree = new BlackRedBST<Integer, Object>();
//        tree.insert(0);
//        tree.insert(1);
//        tree.insert(2);
//        tree.insert(3);
//        tree.insert(4);
//        tree.insert(5);
//        tree.insert(6);
//        tree.insert(7);
//        tree.insert(8);
//
//        tree.remove(5);
//
//        System.out.println(tree.size());
//
//        tree.display();
//
//        System.out.println(tree.findMax());
//        System.out.println(tree.findMin());
//
//        System.out.println(tree.contains(11));
//        System.out.println(tree.contains(0));
//        System.out.println(tree.contains(1));
//        System.out.println(tree.contains(2));
//        System.out.println(tree.contains(3));
//        System.out.println(tree.contains(5));
//
//        System.out.println(tree.isEmpty());
//
//
//        for (int i : tree.range(3, 7)) {
//            System.out.println(i);
//        }


        BlackRedBST<Integer, Object> tree = new BlackRedBST<>();

        tree.put(1, 11);
        tree.put(2, 22);
        tree.put(3, 33);
        tree.put(4, 44);
        tree.put(5, 55);
        tree.put(6, 66);

        System.out.println(tree.get(3));
        System.out.println(tree.select(3));


        System.out.println(tree.keys(2, 4));

    }


}
