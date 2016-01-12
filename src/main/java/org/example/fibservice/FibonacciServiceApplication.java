package org.example.fibservice;

import org.example.fibservice.service.FibonacciHealthCheck;
import org.example.fibservice.service.FibonacciServiceResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Dropwizard application that initializes the service
 * 
 *
 * @author robertsax
 *
 */
public class FibonacciServiceApplication extends Application<FibonacciServiceConfiguration>
{

	/**
	 * Main entry point for the application. Will start Jetty and launch the 
	 * application.
	 *
	 * @param args The command line arguments
	 * @throws Exception Thrown if the run fails
	 */
	public static void main(String[] args) throws Exception
	{
		new FibonacciServiceApplication().run(args);
	}

	@Override
	public void run(FibonacciServiceConfiguration config, Environment env) throws Exception
	{
		// Add the service
		final FibonacciServiceResource fibService = new FibonacciServiceResource();
		env.jersey().register(fibService);
		
		// Add the health check
		final FibonacciHealthCheck fibHealth = new FibonacciHealthCheck();
		env.healthChecks().register("Fibonacci", fibHealth);
				
	}
	
	
}
