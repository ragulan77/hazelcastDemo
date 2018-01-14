package com.example.demo;

import com.example.demo.interfaces.MyService;
import com.example.demo.interfaces.MyServiceImpl;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import io.vertx.servicediscovery.types.EventBusService;

public class ConsumerVerticle extends AbstractVerticle{

	@Override
	  public void start(Future<Void> startFuture) throws Exception {
		//service discovery
		ServiceDiscoveryOptions serviceOptions = new ServiceDiscoveryOptions().setBackendConfiguration(new JsonObject().put("connection", "192.168.222.130:2181"));
		
		
		
		ServiceDiscovery.create(vertx, discoResult -> {
			
				vertx.setPeriodic(500, id -> {
					EventBusService.getProxy(discoResult, MyService.class, ar -> {
		    			if(ar.succeeded())
		    			{
		    				MyService service = ar.result();
		    				System.out.println(service.toString());
		            		service.tellMeHello(resultService -> {
		            			System.out.println("service return to me " + resultService);
		            		});
		    			}
		    			else
		    			{
		    				System.out.println("event bus get proxy failed !!!");
		    			}
		    		});
				});
				
			});
			
	    	startFuture.complete();

	  }
	
	
	
}
