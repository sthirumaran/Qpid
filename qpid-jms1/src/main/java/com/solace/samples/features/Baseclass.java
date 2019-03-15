package com.solace.samples.features;

import com.solace.samples.QueueConsumer;
import com.solace.samples.QueueConsumer2;
import com.solace.samples.QueueProducer;

public class Baseclass {
	
	 public static void main(String[] args) throws Exception  {
   
  	
  	
  	
  	
  	Runnable r = new Runnable() {
		public void run() {
			try {
				new QueueConsumer().run();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		
	};

	Thread t = new Thread(r);
	// Lets run Thread in background..
	// Sometimes you need to run thread in background for your Timer application..
	t.start(); // starts thread in background..
	// t.run(); // is going to execute the code in the thread's run method on the current thread..
	System.out.println("Main() Program Exited...\n");
	new QueueProducer().run();
	
}
	 
  	
  }
	 
	


