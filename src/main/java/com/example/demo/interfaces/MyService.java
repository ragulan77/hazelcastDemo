package com.example.demo.interfaces;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;


@ProxyGen
@VertxGen
public interface MyService {
	public static String SERVICE_NAME = "my-service";
	public static String SERVICE_ADDRESS = "demo.myservice";
	
	//factory methods
	/*
	public static MyService createProxy(Vertx vertx)
	{
		return new MyServiceVertxEBProxy(vertx, SERVICE_ADDRESS);
	}
	
	public static MyService createProxy(Vertx vertx, String address)
	{
		return new MyServiceVertxEBProxy(vertx, address);
	}
	*/
	
	@Fluent
	public MyService tellMeHello(Handler<AsyncResult<String>> result);
	
	
}
