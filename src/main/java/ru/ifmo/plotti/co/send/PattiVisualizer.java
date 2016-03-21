package ru.ifmo.plotti.co.send;

import com.sun.jersey.api.client.Client;

import javax.ws.rs.core.UriBuilder;

public class PattiVisualizer {
    public static final String HOST = "http://plotti.co";
    public static final String PARAM_ID = "d";

    private final Client client = Client.create();
    private final String key;

    public PattiVisualizer(String key) {
        this.key = key;
    }

    public void plot(PlotValueBuilder values) {
        client.resource(UriBuilder
                .fromUri(HOST)
                .path(key)
                .queryParam(PARAM_ID, values.build())
                .build()).get(String.class);
    }

    public void reset() {
        for (int i = 0; i < 50; i++) {
            plot(new PlotValueBuilder());
        }
    }

}
