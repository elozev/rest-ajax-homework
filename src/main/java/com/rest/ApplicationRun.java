package com.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by emil on 11/13/16.
 */
public class ApplicationRun extends ResourceConfig {
    public ApplicationRun(){
        register(JacksonFeature.class);
    }
}
