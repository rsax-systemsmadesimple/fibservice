package org.example.fibservice.service;

import java.math.BigInteger;

import com.codahale.metrics.health.HealthCheck;

/**
 * Health Check class for the fibonacci service. 
 * 
 * Ideally this would test resources that the service depends on, but for the
 * purposes of this project, it is similar to a unit test case.
 *
 * @author robertsax
 *
 */
public class FibonacciHealthCheck extends HealthCheck
{

	@Override
	protected Result check() throws Exception
	{
		Result result = Result.healthy();
		
		// Test a common fibonacci sequence
		FibonacciServiceResource testObj = new FibonacciServiceResource();
		FibonacciResult fibresult = testObj.fibonacciService("4");
		if (fibresult.getOutput().length != 4	
			|| !fibresult.getOutput()[0].equals(BigInteger.valueOf(0))
			|| !fibresult.getOutput()[1].equals(BigInteger.valueOf(1))
			|| !fibresult.getOutput()[2].equals(BigInteger.valueOf(1))
			|| !fibresult.getOutput()[3].equals(BigInteger.valueOf(2)))
		{
			result=Result.unhealthy("Fibonacci(4) reported incorrect results");
		}
		
		return result;
	}

}
