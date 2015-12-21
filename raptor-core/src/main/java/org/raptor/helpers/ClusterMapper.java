package org.raptor.helpers;

import org.raptor.model.BetaNodeLive;
import org.raptor.model.Ping;
import org.raptor.model.ServerLive;
import org.raptor.model.WorkerNodeLive;

import java.util.HashMap;

/**
 * Created by Anant on 22-12-2015.
 */
public class ClusterMapper {
    private HashMap<String, ServerLive> clusterMap;

    public ClusterMapper() {
        clusterMap = new HashMap<>();
    }

    public HashMap<String, ServerLive>  getUpdatedCluster(Ping ping)
    {
        String serverIP = ping.getServer().getServerIP();

        if (!clusterMap.containsKey(serverIP))
            clusterMap.put(serverIP
                    , new ServerLive(ping.getServer()));

        if (ping.getNode() instanceof BetaNodeLive) {
            BetaNodeLive betaNode = (BetaNodeLive) ping.getNode();
            String betaId = betaNode.getDeploymentId();

            if (!clusterMap.get(serverIP).getBetaNodes().containsKey(betaId))
                clusterMap.get(serverIP).getBetaNodes().put(betaId, betaNode);
        } else {
            WorkerNodeLive workerNodeLive = (WorkerNodeLive) ping.getNode();
            String betaId = workerNodeLive.getParentDeploymentId();
            String workerId = workerNodeLive.getDeploymentId();

            if (clusterMap.get(serverIP).getBetaNodes().containsKey(betaId))
                if (!clusterMap.get(serverIP).getBetaNodes().get(betaId).getLiveNodes().containsKey(workerId))
                    clusterMap.get(serverIP).getBetaNodes().get(betaId).getLiveNodes().put(workerId, workerNodeLive);
        }

        return clusterMap;
    }
}
