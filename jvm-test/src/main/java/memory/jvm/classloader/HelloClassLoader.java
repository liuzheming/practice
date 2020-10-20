package memory.jvm.classloader;

import ch.qos.logback.core.util.FileUtil;
import com.sun.deploy.util.ReflectionUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 *
 * Created by lzm on 2020/10/20.
 */
public class HelloClassLoader extends ClassLoader {

  private static Logger LOGGER = LoggerFactory.getLogger(HelloClassLoader.class);


  public static void main(String... args) throws Exception {

    try {
      Class clazz = new HelloClassLoader().findClass("Hello"); // 加载并初始化hello类
      LOGGER.info("clazz:{}", clazz);
      Object obj = clazz.newInstance();
      Method method = clazz.getMethod("hello");
      if (method != null) {
        method.invoke(obj);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {

    File file = new File("/Users/lzm/Desktop/job/prepare/week1/Hello/Hello.xlass");
    byte[] byteArr = new byte[(int) file.length()];

    try {
      new FileInputStream(file).read(byteArr);
    } catch (Exception e) {
      e.printStackTrace();
    }

    byte[] bytes = new byte[(int) file.length()];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = (byte) ((byte) 255 - byteArr[i]);
    }

    return defineClass(name, bytes, 0, bytes.length);
  }


  private byte[] decode(String str) {
    return java.util.Base64.getDecoder().decode(str);
  }
}
