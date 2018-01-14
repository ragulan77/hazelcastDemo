package com.example.demo;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class ClientLauncher {
	public static void main(String[] args) {

		DeploymentOptions doptions = new DeploymentOptions();
        //doptions.setWorker(true).setWorkerPoolName("PoolOfCllientWorker").setWorkerPoolSize(1);
        
        Vertx.vertx().deployVerticle(new com.example.demo.Client(), doptions, result -> {
        	if(result.succeeded())
        	{
        		System.out.println("deployment of ClientVerticle succeeded");

        	}
        	else
        	{
        		System.out.println("deployment of ClientVerticle get wrong");
        	}
        });
	}
}
