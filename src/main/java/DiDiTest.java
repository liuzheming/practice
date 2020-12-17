import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * <p>
 * Created by lzm on 2020/12/16.
 */
@Slf4j
public class DiDiTest {


    public List<Integer> getArr(int[] arr) {

        if (arr == null || arr.length == 0) {
            return null;
        }

        Map<Integer, Integer> numberToCount = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (numberToCount.containsKey(arr[i])) {
                int count = numberToCount.get(arr[i]);
                count = count + 1;
                numberToCount.put(arr[i], count);
            } else {
                numberToCount.put(arr[i], 1);
            }
        }

        List<Integer> targetArr = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : numberToCount.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                targetArr.add(entry.getKey());
            }
        }
        return targetArr;
    }


    public static void main(String... args) {

        int[] arr = new int[]{1, 2, 1, 2, 3, 4, 5, 6, 3, 5, 7};
        DiDiTest diDiTest = new DiDiTest();
        log.info("find reuslt:{}", diDiTest.getArr(arr));

    }


}
