package it.unict.appgroup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.fabric8.kubernetes.api.model.Pod;

public class App {

    private final List<Pod> pods;

    private final List<Channel> channels;

    public App(List<Pod> pods, List<Channel> channels) {
        this.pods = pods;
        this.channels = channels;
    }

    public List<Pod> getPods() {
        return pods;
    }

    public Map<String, Double> getRequestsPerSecond() {
        return channels.stream()
                .collect(Collectors.toMap(Channel::getPeerAppName, Channel::getRequestsPerSecond, (v1, v2) -> v1));
    }
}