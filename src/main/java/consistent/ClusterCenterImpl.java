package consistent;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by lzm on 2020/12/15
 */
@Slf4j
public class ClusterCenterImpl implements ClusterCenter {

    private Map<String, ClusterNode> idToNode = new HashMap<>();

    @Override
    public void addNode(ClusterNode clusterNode) {
        if (clusterNode != null && !StringUtils.isEmpty(clusterNode.ip)) {
            idToNode.put(clusterNode.ip, clusterNode);
        }
        log.info("on node added,ip:{}", clusterNode.ip);
    }

    @Override
    public void removeNode(String ip) {
        if (!StringUtils.isEmpty(ip)) {
            idToNode.remove(ip);
        }
        log.info("on node deleted,ip:{}", ip);
    }

    @Override
    public int getClusterSize() {
        return idToNode.size();
    }

    @Override
    public void clean() {
        idToNode.clear();
    }


}
