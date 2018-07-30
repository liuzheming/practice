import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

/**
 * Description:
 * <p>
 * Created by lzm on 2018/1/4.
 */
public class MyTest {


    int x;

    public static void main(String... args) {

        int n = 1;
        MyTest test = new MyTest();
        test.change(test);
        System.out.println("outer : n=" + test.x);
    }


    public void change(MyTest i) {
        i.x = 5;
        System.out.println("inter : i=" + i.x);
    }

    @Test
    public void testQueryVideoMethod() {

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("http://portal-api.jcloud.com/api/video/queryVideos?pageNum=1&pageSize=2")
                .addHeader("referer", "portal-api.jcloud.com")
                .build();

        try {
            Response res = client.newCall(request).execute();
            System.out.println(res.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
