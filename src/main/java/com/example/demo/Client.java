package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpClient;

public class Client extends AbstractVerticle{
	
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		
		HttpClient client = vertx.createHttpClient();

		
		//vertx.setPeriodic(10, id -> {
		for(int i = 0; i < 1000; i++)
		{
			System.out.println(i);
			client.getNow(8181, "localhost", "",response -> {
				response.bodyHandler(body -> {
					System.out.println(body.toString() + " client");
				});
			});
			
		}
			
		//});
		
		//client.close();
		
	}

}
