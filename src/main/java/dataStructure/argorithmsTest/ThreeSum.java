package dataStructure.argorithmsTest;

import edu.princeton.cs.algs4.In;

/**
 * Description:  统计一个文件中所有和为0的三整数元组的个数
 * <p>
 * Created by lzm on 2017/10/18.
 */
public class ThreeSum {

    public static void main(String... args) {

        In in = new In("/Users/lzm/Downloads/algs4-data/8kints.txt");
        System.out.println(ThreeSum.count(in.readAllInts()));
//        in.readAllInts("");

    }


    public static int count(int[] arr) {
        int N = arr.length;
        int cnt = 0;
        /*
            1   :   0
            2   :   0
            3   :   1
            4   :   2+1
            5   :
         */
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    System.out.println("[i=" + i + ";j=" + j + ";k=" + k + "]");
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;

    }


}
