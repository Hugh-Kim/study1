package com.messenger.common;

import org.vertx.java.core.Vertx;

/**
 * Created by Administrator on 2014-06-09.
 */
public interface EmbeddableVerticle {
    void start(Vertx vertx);

    String host();

    int port();
}
