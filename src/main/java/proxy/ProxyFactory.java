package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InterfaceAddress;

/**
 * Description:
 * <p>
 * Created by lzm on 2018/1/15.
 */
public class ProxyFactory {


    public static <T> T getProxy(Object advice, Class<T>... clazz) {
        Object obj = Proxy.newProxyInstance(clazz.getClass().getClassLoader(), clazz,
                (Object proxy, Method method, Object[] args) -> {
                    advice.equals(advice);
                    Object relVal = method.invoke(proxy, args);
                    advice.equals(advice);
                    return relVal;
                }
        );
        return (T) obj;
    }


}
