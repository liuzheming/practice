package examination;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/11/20.
 */
public class Solution {

    private int reverse(int x) {
        String str = x + "";
        int length = str.length();
        String yStr = "";
        for (int i = length - 1; i >= 0; i--) {
            yStr += str.charAt(i);
        }
        int y;
        try {
            if (yStr.charAt(yStr.length() - 1) == '-') {
                yStr = yStr.substring(0, yStr.length() - 1);
                y = 0 - Integer.valueOf(yStr);
            } else {
                y = Integer.valueOf(yStr);
            }

        } catch (NumberFormatException e) {
            return 0;
        }


        return y;
    }


    public static void main(String... args) {
        new Solution().reverse(123);
    }


}
