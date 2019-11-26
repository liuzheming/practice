import edu.princeton.cs.algs4.StdDraw;
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

//    String str = new String("ssss");
    String str = "ssss";
    String str2 = "ss" + "ss";
    String str3 = new String("ssss");
    String str4 = new String("ssss");
    System.out.println(str == str2);
    System.out.println(str == str3);
    System.out.println(str4 == str3);
//    for (int i = 0; i < 10000000000000L; i++) {
//      str += str;
//    }
//    int n = 1;
//    MyTest test = new MyTest();
//    test.change(test);
//    System.out.println("outer : n=" + test.x);
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

  @Test
  public void printTest() {
    int n = 10000;
    StdDraw.setXscale(0, n);
    StdDraw.setYscale(0, n * n);
    StdDraw.setPenRadius(.01);
    for (int i = 1; i <= n; i++) {
      StdDraw.point(i, i);
      StdDraw.point(i, i * i);
      StdDraw.point(i, i * Math.log(i));
    }
  }


  @Test
  public void stringTest() {

  }


}
