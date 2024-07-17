package it.unict.appgroup;

public class Channel {

    private final String peerAppName;

    private final double rps;

    public Channel(String peerAppName, double rps) {
        this.peerAppName = peerAppName;
        this.rps = rps;
    }

    public String getPeerAppName() {
        return peerAppName;
    }

    public double getRequestsPerSecond() {
        return rps;
    }
}