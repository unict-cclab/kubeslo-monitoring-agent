package it.unict.appgroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.fabric8.kubernetes.api.model.Pod;

public class AppGroupGraph {
    private final List<App> apps;

    AppGroupGraph() {
        apps = new ArrayList<>();
    }

    public List<App> getApps() {
        return apps;
    }

    public void addApp(List<Pod> pods, Map<String, Double> rps) {
        List<Channel> channels = rps.entrySet().stream()
                .map((entry) -> new Channel(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        apps.add(new App(pods, channels));
    }
}