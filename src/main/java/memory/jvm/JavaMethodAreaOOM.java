package memory.jvm;

import java.lang.reflect.Method;
import java.nio.file.attribute.AclEntryPermission;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * Description: 测试方法区OOM,使用CGLib使方法区出现内存溢出异常
 *
 * JDK 8 开始,将方法区移到了直接内存中
 *
 * VM Args: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 *
 * Created by lzm on 2019/12/3.
 */
public class JavaMethodAreaOOM {

  public static void main(String... args) {

    // 动态增强的过程中会不断创建class,并存入MethodArea中
    while (true) {
      Enhancer enhancer = new Enhancer();
      enhancer.setSuperclass(OOMObject.class);
      enhancer.setUseCache(false);
      enhancer.setCallback(new MethodInterceptor() {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable {
          return proxy.invokeSuper(obj, args);
        }
      });
      enhancer.create();
    }
  }


  static class OOMObject {

  }
}
