package com.example.demo.interfaces;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class MyServiceImpl implements MyService {

	Random rand = new Random();

	int  n = rand.nextInt(50000) + 1;
	
	@Override
	public MyService tellMeHello(Handler<AsyncResult<String>> result) {
		String threadName = Thread.currentThread().getName();
		
		InetAddress addr;
	    try {
			addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
			
			try {
				Thread.sleep(450);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			result.handle(Future.succeededFuture("Hello budy ! From " + hostname + " N°"+n + " Thread : " + threadName));
		} catch (UnknownHostException e) {
			result.handle(Future.succeededFuture("Hello budy ! From don't know N°"+n + " Thread : " + threadName));
			return this;
		}
	    
		return this;
	}

}
