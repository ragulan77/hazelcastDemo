package com.example.demo;

import java.util.Random;

import com.example.demo.interfaces.MyService;




import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.streams.Pump;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.EventBusService;

public class Server extends AbstractVerticle{
	Random rand = new Random();
	int  n = rand.nextInt(50000) + 1;
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		final int port = 8181;
		
		HttpServer server = vertx.createHttpServer();
		//each tiome we get a request from client, we call the service tellMeHello
		server.requestHandler(request -> {
			//Pump p = Pump.pump(request, request.response()).setWriteQueueMaxSize(2).start();

			/*
			ServiceDiscovery.create(vertx, discoResult -> {
				
				EventBusService.getProxy(discoResult, MyService.class, ar -> {
	    			if(ar.succeeded())
	    			{
	    				vertx.executeBlocking(future -> {
	    					MyService service = ar.result();
		            		service.tellMeHello(resultService -> {
		            			request.response().end(resultService.result());
		            			future.complete(true);
		            		});
	    		        }, (result) -> {});
	    			}
	    			else
	    			{
	    				System.out.println("event bus get proxy failed !!!");
	    			}
	    		});
			});
			*/
			
			
			vertx.executeBlocking(future -> {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String threadName = Thread.currentThread().getName();
    			request.response().end("Hello budy ! From " + " N°"+n + " Thread : " + threadName);
    			future.complete(true);

	        }, (result) -> {});
			
			
		});
		
		server.listen(port, res -> {
			if(res.succeeded())
			{
				System.out.println("server on port " + port + " is running");
			}
			else
			{
				System.out.println("server on port " + port + " is NOT running !");
			}
		});
		
		startFuture.complete();
	}
}
