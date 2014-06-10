package com.messenger.controller;

import org.springframework.stereotype.Component;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.messenger.common.DefaultEmbeddableVerticle;
import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;

/**
 * Created by Administrator on 2014-06-09.
 */
@Component
public class ChatServerVerticle extends DefaultEmbeddableVerticle {
	private SocketIOServer io;

	@Override
	public void start(Vertx vertx) {
		System.out.println("start vertx server!");
		HttpServer httpServer = vertx.createHttpServer();
		io = new DefaultSocketIOServer(vertx, httpServer);
		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			@Override
			public void handle(final SocketIOSocket socket) {
				socket.emit("welecome");

				socket.on("echo", new Handler<JsonObject>() {
					@Override
					public void handle(JsonObject msg) {
						socket.emit("echo", msg);
					}
				});
			}
		});
		httpServer.listen(1234);
	}

	public SocketIOServer getIo() {
		return io;
	}
}
