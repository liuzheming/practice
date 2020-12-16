package loadbalance;


import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * Created by lzm on 2020/12/16.
 */
public class WeightPolling {

    private List<Server> serversWithWeight = new ArrayList<>();

    private int idx;

    public Server getServer() {
        if (idx == serversWithWeight.size()) {
            idx = 0;
        }
        return serversWithWeight.get(idx++);
    }


    public void addServer(int weight, Server server) {
        for (int i = 0; i < weight; i++) {
            serversWithWeight.add(server);
        }
    }


    public static void main(String... args) {

        // 注册3个server,权重为1:2:1
        WeightPolling weightPolling = new WeightPolling();

        // 循环调用WeightPolling,判断返回各个server的次数是否符合预期
        Server server = new Server();
        server.name = "server1";
        Server server2 = new Server();
        server2.name = "server2";
        Server server3 = new Server();
        server3.name = "server3";

        weightPolling.addServer(1, server);
        weightPolling.addServer(2, server2);
        weightPolling.addServer(1, server3);

        for (int i = 0; i < 12; i++) {
            System.out.println(weightPolling.getServer());
        }


    }


}

class Server {

    public String name;

    @Override
    public String toString() {
        return "Server{" +
                "name='" + name + '\'' +
                '}';
    }
}