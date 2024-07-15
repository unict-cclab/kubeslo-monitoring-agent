package it.unict.cluster;

import io.fabric8.kubernetes.api.model.Node;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClusterNode {

    private final Node node;

    private final String name;

    private final List<Link> links;

    public ClusterNode(Node node, String name, List<Link> links) {
        this.node = node;
        this.name = name;
        this.links = links;
    }

    public Node getNode() {
        return node;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getLatencies() {
        return links.stream().collect(Collectors.toMap(Link::getPeerClusterNodeName, Link::getLatency, (v1, v2) -> v1));
    }
}