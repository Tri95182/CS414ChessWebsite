package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ConfigRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);
    private String serverName;
    private ArrayList<String> features;

    public ConfigRequest() {
        this.requestType = "config";
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    @Override
    public void buildResponse() {
        serverName = "t09 Hub Dawgs";
        features = new ArrayList<>();
        features.add("config");
        log.trace("buildResponse -> {}", this);
    }

    @Override
    public void buildResponse(spark.Request httpRequest) {
    }

    public String getServerName() {
        return serverName;
    }

    public boolean validFeature(String feature) {
        return features.contains(feature);
    }
}