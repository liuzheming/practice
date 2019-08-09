package http.v2;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 不足与改善： 这个服务器可以提供一定的功能，但仍然十分简单，还可以添加以下的一些特性：
 *
 * （1）服务器管理界面
 *
 * （2）支持CGI程序和Java Servlet API
 *
 * （3）支持其他请求方法
 *
 * （4）常见Web日志文件格式的日志文件
 *
 * （5）支持多文档根目录，这样各用户可以有自己的网站
 *
 * 最后，花点时间考虑一下可以采用什么方法来优化此服务器。如果真的希望使用JHTTP运行高流量的网站，还可以做一些事情来加速此服务器。
 * 第一点也是最重要的一点就是使用即时编译器（JIT），如HotSpot。JIT可以将程序的性能提升大约一个数量级。 第二件事就是实现智能缓存。记住接受的请求，将最频繁的请求文件的数据存储在Hashtable中，使之保存在内存中。
 * 使用低优先级的线程更新此缓存。 --------------------- 作者：vinoYang 来源：CSDN 原文：https://blog.csdn.net/yanghua_kobe/article/details/7296156
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class JHTTP extends Thread {

  private File documentRootDirectory;
  private String indexFileName = "index.html";
  private ServerSocket server;
  private int numThreads = 50;

  public JHTTP(File documentRootDirectory, int port, String indexFileName) throws IOException {
    if (!documentRootDirectory.isDirectory()) {
      throw new IOException(documentRootDirectory + " does not exist as a directory ");
    }
    this.documentRootDirectory = documentRootDirectory;
    this.indexFileName = indexFileName;
    this.server = new ServerSocket(port);
  }

  private JHTTP(File documentRootDirectory, int port) throws IOException {
    this(documentRootDirectory, port, "index.html");
  }

  public void run() {
    for (int i = 0; i < numThreads; i++) {
      Thread t = new Thread(new RequestProcessor(documentRootDirectory, indexFileName));
      t.start();
    }

    System.out.println("Accepting connection on port "
        + server.getLocalPort());
    System.out.println("Document Root: " + documentRootDirectory);
    while (true) {
      try {
        Socket request = server.accept();
        RequestProcessor.processRequest(request);
      } catch (IOException e) {
        // TODO: handle exception
      }
    }
  }


  /**
   * @param args
   */
  public static void main(String[] args) {
    File docroot;
    try {
      docroot = new File(args[0]);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Usage: java JHTTP docroot port indexfile");
      return;
    }

    int port;
    try {
      port = Integer.parseInt(args[1]);
      if (port < 0 || port > 65535) {
        port = 80;
      }
    } catch (Exception e) {
      port = 80;
    }

    try {
      JHTTP webserver = new JHTTP(docroot, port);
      webserver.start();
    } catch (IOException e) {
      System.out.println("Server could not start because of an " + e.getClass());
      System.out.println(e);
    }

  }

}
