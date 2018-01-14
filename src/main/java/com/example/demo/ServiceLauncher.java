package com.example.demo;


import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class ServiceLauncher {
	public static void main(String[] args) {

        ClusterManager mgr = new HazelcastClusterManager();
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        

        //options.setClustered(true).setClusterHost("localhost").setClusterPort(15701);
        //options.getEventBusOptions().setClustered(true);
        options.setClustered(true).setClusterHost("192.168.222.1");
        
        Vertx.clusteredVertx(options, res -> {
          if (res.succeeded()) {
        	 System.out.println(" ServiceVerticle clustering succeeded");
             Vertx vertx = res.result();
                   
             //TODO
             // ADDED FOR WORKER
             DeploymentOptions doptions = new DeploymentOptions();
             doptions.setWorker(true).setWorkerPoolName("PoolOfServiceWorker").setWorkerPoolSize(5);
             
             
             
			vertx.deployVerticle(new com.example.demo.ServiceVerticle(), doptions, result -> {
				if(result.succeeded())
				{
					System.out.println("deployment of ServiceVerticle succeeded");
			
				}
				else
				{
					System.out.println("deployment of ServiceVerticle get wrong");
				}
			});
            
          } else {
            System.out.println("ServiceVerticle clustering faild !");
          }
        });
        
	}
}
