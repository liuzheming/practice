package consistent;


import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * Create by lzm on 2020/12/15
 */
@Slf4j
public class ClusterNode {

    public Integer hash;

    public ClusterCenter center;

    public Map<String, Device> idToDevice = new HashMap<>();

    public String ip;

    public ClusterNode(String ip) {
        this.ip = ip;
        // TODO 真实场景使用什么值来计算hash的 ？
        this.hash = getHash(ip);
        center = new ClusterCenterImpl(ip);
        center.addNode(this);
        registerToZk();
        loadDevice();
    }

    private void registerToZk() {
        Collection<ClusterNode> coll = ZK.addNode(this);
        for (ClusterNode node : coll) {
            center.addNode(node);
        }
    }

    public void unregisterFromZk() {
        center.clean();
        ZK.delNode(this);
    }

    private void loadDevice() {
        for (Device device : Main.DEVICE_LIST) {
            ClusterNode node = resolveRoutingByDeviceId(device);
            if (node != null && !node.ip.equals(this.ip)) {
                idToDevice.remove(device.uuid);
            } else {
                idToDevice.put(device.uuid, device);
            }
        }
    }

    public void onClusterAddNode(ClusterNode node) {
        center.addNode(node);
        loadDevice();
    }

    public void onClusterDelNode(ClusterNode node) {
        center.removeNode(node);
        loadDevice();
    }

    public void addDevice(Device device) {
        idToDevice.put(device.uuid, device);
    }

    public void printDevices() {
        log.info("print devices, cluster:{},devices:{}", ip, idToDevice);
    }

    private ClusterNode resolveRoutingByDeviceId(Device device) {

        // 计算每个设备的hash值，与node的hash值进行对比，设备应当归属于hash值比它大的第一个节点
        int hash = getHash(device.uuid);

        SortedMap<Integer, ClusterNode> tailMap = center.getAllNodes().tailMap(hash);
        // 返回计算得到的node节点
        if (tailMap.size() == 0) {
            return center.getAllNodes().get(center.getAllNodes().firstKey());
        }
        return tailMap.get(tailMap.firstKey());
    }

    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }

        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    @Override
    public String toString() {
        return "ClusterNode{" +
                "ip='" + ip + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
