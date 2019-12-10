package memory.leak;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description: 模拟内存泄漏
 *
 * Created by lzm on 2019/11/27.
 */
final public class ClassLoaderLeakException {


  static volatile boolean running = true;

  public static void main(String... args) throws Exception {

    Thread thread = new LongRunningThread();

    try {
      thread.start();
      System.out.println("Running, press any key to stop.");
      System.in.read();
    } finally {
      running = false;
      thread.join();
    }

  }


  static final class LongRunningThread extends Thread {

    @Override
    public void run() {
      while (running) {
        try {
          loadAndDiscard();
        } catch (Throwable ex) {
          ex.printStackTrace();
        }
        try {
          Thread.sleep(1000);
        } catch (Throwable ex) {
          ex.printStackTrace();
          running = false;
        }
      }
    }
  }

  static final class ChildOnlyClassLoader extends ClassLoader {

    ChildOnlyClassLoader() {
      super(ClassLoaderLeakException.class.getClassLoader());
    }

    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
      if (LoadedInChildClassLoader.class.getName().equals(name)) {
        return super.loadClass(name, resolve);
      }
      try {
        Path path = Paths.get(LoadedInChildClassLoader.class.getName() + ".class");
        byte[] classBytes = Files.readAllBytes(path);
        Class<?> c = defineClass(name, classBytes, 0, classBytes.length);
        if (resolve) {
          resolveClass(c);
        }
        return c;
      } catch (IOException e) {
        throw new ClassNotFoundException("Could not load " + name, e);
      }
    }
  }

  static void loadAndDiscard() throws Exception {

    ClassLoader childClassLoader = new ChildOnlyClassLoader();
    Class<?> childClass = Class.forName(
        LoadedInChildClassLoader.class.getName(), true, childClassLoader);
    childClass.newInstance();
  }

  static final public class LoadedInChildClassLoader {

    static final byte[] moreBytesToLeak = new byte[1024 * 1024 * 10];

    private static ThreadLocal<LoadedInChildClassLoader> threadLocal = new ThreadLocal<>();

    public LoadedInChildClassLoader() {
      threadLocal.set(this);
    }

  }


}

