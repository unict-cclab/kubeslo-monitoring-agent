package it.unict.service;

import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import io.smallrye.mutiny.Uni;

@RegisterRestClient(configKey = "telemetry")
public interface TelemetryService {

    @GET
    @Path("/metrics/nodes/latencies")
    Uni<Map<String, Double>> getNodeLatencies(@QueryParam("node") String node,
            @QueryParam("range-width") String rangeWidth);
}