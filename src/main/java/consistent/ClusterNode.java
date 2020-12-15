package consistent;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by lzm on 2020/12/15
 */
@Slf4j
public class ClusterNode {

    public ClusterCenter center;

    public Map<String, Device> idToDevice = new HashMap<>();

    public String ip;

    public ClusterNode(String ip) {
        this.ip = ip;
        center = new ClusterCenterImpl();
        ZK.addNode(this);
        loadDevice();
    }

    private void loadDevice() {
        for (Device device : Main.DEVICE_LIST) {
            idToDevice.put(device.uuid, device);
        }
    }

    public void onClusterAddNode(ClusterNode node) {
        center.addNode(node);
        reloadDevices();
    }

    private void reloadDevices() {
        for (Device device : Main.DEVICE_LIST) {
            ClusterNode node = resolveRoutingByDeviceId(device);
            if (node != null && !node.ip.equals(this.ip)) {
                idToDevice.remove(device.uuid);
            } else {
                idToDevice.put(device.uuid, device);
            }
        }
    }

    public void onClusterDelNode(String ip) {
        center.removeNode(ip);
        reloadDevices();
    }

    public void addDevice(Device device) {
        idToDevice.put(device.uuid, device);
    }

    public void printDevices() {
        log.info("print devices, cluster:{},devices:{}", ip, idToDevice);
    }

    private ClusterNode resolveRoutingByDeviceId(Device device) {

        // 计算出每个node在hash环上的对应点

        // 遍历设备列表

        //     计算每个设备的hash值，与node的hash值进行对比，设备应当归属于hash值比它大的第一个节点


        // 返回计算得到的node节点
        return null;
    }

    @Override
    public String toString() {
        return "ClusterNode{" +
                "ip='" + ip + '\'' +
                '}';
    }
}
