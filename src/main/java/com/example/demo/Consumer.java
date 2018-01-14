package com.example.demo;


import com.example.demo.interfaces.MyService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class Consumer {

	public static void main(String[] args) {
	
		

        ClusterManager mgr = new HazelcastClusterManager();
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        
        
        options.setClustered(true).setClusterHost("192.168.222.1");
        //options.getEventBusOptions().setClusterPublicHost("192.168.222.130").setClustered(true);
        //options.getEventBusOptions().setClustered(true);
              
        
        
        Vertx.clusteredVertx(options, res -> {
          if (res.succeeded()) {
        	 System.out.println(" ConsumerVerticle clustering succeeded");
            Vertx vertx = res.result();
                        
            vertx.deployVerticle(new com.example.demo.ConsumerVerticle(), result -> {
            	if(result.succeeded())
            		System.out.println("deployment of ConsumerVerticle succeeded");
            	else
            	{
            		System.out.println("deployment of ConsumerVerticle get wrong");
            	}
            });
            
          } else {
            System.out.println("ConsumerVerticle clustering faild !");
          }
        });
        
	}
	

}
