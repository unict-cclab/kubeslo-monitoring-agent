package it.unict.cluster;

import io.fabric8.kubernetes.api.model.Node;
import it.unict.service.TelemetryService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ClusterGraphBuilder {

    private static final Logger log = LoggerFactory.getLogger(ClusterGraphBuilder.class);

    @Inject
    @RestClient
    TelemetryService telemetryService;

    public ClusterGraph buildClusterGraph(List<Node> nodes, String metricsRangeWidth) {
        ClusterGraph clusterGraph = new ClusterGraph();

        nodes.forEach(node -> {
            String clusterNodeName = node.getMetadata().getName();

            Map<String, Double> latencies = new HashMap<>();
            try {
                latencies = telemetryService.getNodeLatencies(clusterNodeName, metricsRangeWidth).await()
                        .indefinitely();
            } catch (Exception e) {
                log.info(e.getMessage());
            }

            clusterGraph.addClusterNode(node, clusterNodeName, latencies);
        });

        return clusterGraph;
    }
}