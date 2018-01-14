package com.example.demo;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class ServerLauncher {
	public static void main(String[] args) {
		ClusterManager mgr = new HazelcastClusterManager();
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        

        //options.setClustered(true).setClusterHost("localhost").setClusterPort(15701);
        //options.getEventBusOptions().setClustered(true);
        options.setClustered(true).setClusterHost("192.168.222.1");
        
        Vertx.clusteredVertx(options, res -> {
          if (res.succeeded()) {
        	 System.out.println(" Server Verticle clustering succeeded");
             Vertx vertx = res.result();
             DeploymentOptions doptions = new DeploymentOptions();
             doptions.setWorker(true).setWorkerPoolName("PoolOfServerWorker").setWorkerPoolSize(15);
             
             
            vertx.deployVerticle(new com.example.demo.Server(), doptions, result -> {
            	if(result.succeeded())
            	{
            		System.out.println("deployment of ServerVerticle succeeded");

            	}
            	else
            	{
            		System.out.println("deployment of ServerVerticle get wrong");
            	}
            });
            
          } else {
            System.out.println("ServerVerticle clustering faild !");
          }
        });
	}
}
