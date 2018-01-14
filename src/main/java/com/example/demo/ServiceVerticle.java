package com.example.demo;

import com.example.demo.interfaces.MyService;
import com.example.demo.interfaces.MyServiceImpl;








import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import io.vertx.servicediscovery.types.EventBusService;
import io.vertx.serviceproxy.ServiceBinder;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class ServiceVerticle extends AbstractVerticle{
	
	@Override
	  public void start(Future<Void> startFuture) throws Exception {
		ServiceBinder binder = new ServiceBinder(vertx);
		MyService service = new MyServiceImpl();
		binder.setAddress(MyService.SERVICE_ADDRESS).register(MyService.class, service);
		
		
		//service discovery
		//ServiceDiscoveryOptions serviceOptions = new ServiceDiscoveryOptions().setBackendConfiguration(new JsonObject().put("connection", "192.168.222.130:2181"));
		ServiceDiscoveryOptions serviceOptions = new ServiceDiscoveryOptions().setBackendConfiguration(new JsonObject().put("connection", "192.168.222.130:2181"));

		ServiceDiscovery.create(vertx, discovery -> {
			//publish our service in serviceDiscovery
			Record record = EventBusService.createRecord(MyService.SERVICE_NAME, 
					MyService.SERVICE_ADDRESS, 
					MyService.class
			);
			
			
			
			discovery.publish(record, discoResult -> {
				System.out.println("the service MyService was published in the service discovery " + record);
			});
		});
		
		startFuture.complete();
	}
}
