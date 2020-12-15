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
    }

    public void onClusterDelNode(String ip) {
        center.removeNode(ip);
    }

    public void addDevice(Device device) {
        idToDevice.put(device.uuid, device);
    }

    public void printDevices() {
        log.info("print devices, cluster:{},devices:{}", ip, idToDevice);
    }

    @Override
    public String toString() {
        return "ClusterNode{" +
                "ip='" + ip + '\'' +
                '}';
    }
}
