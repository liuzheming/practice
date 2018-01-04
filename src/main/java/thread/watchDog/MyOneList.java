package thread.watchDog;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/25.
 */
public class MyOneList {

    private List<String> list = new ArrayList<>();

    synchronized public void add(String data) {
        list.add(data);
    }

    synchronized public int size() {
        return list.size();
    }

}
