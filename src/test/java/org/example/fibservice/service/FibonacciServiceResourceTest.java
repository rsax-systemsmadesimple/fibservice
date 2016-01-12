package org.example.fibservice.service;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

/**
 * Test cases for the Fibonacci Service resource.
 * 
 *
 * @author robertsax
 *
 */
public class FibonacciServiceResourceTest
{

	/**
	 * Test the fibonacci sequence generator for accuracy
	 */
	@Test
	public void testFibonacci()
	{
		FibonacciServiceResource testObj = new FibonacciServiceResource();
		
		// Happy Path
		
		// 0
		BigInteger[] sequence = testObj.fibonacci(0);
		assertTrue(sequence.length == 0);
		
		// 1
		sequence = testObj.fibonacci(1);
		assertTrue(sequence.length == 1);
		assertTrue(sequence[0].equals(BigInteger.valueOf(0)));
		
		// 2
		sequence = testObj.fibonacci(2);
		assertTrue(sequence.length == 2);
		assertTrue(sequence[0].equals(BigInteger.valueOf(0)));
		assertTrue(sequence[1].equals(BigInteger.valueOf(1)));
		
		// > 1
		sequence = testObj.fibonacci(7);
		assertTrue(sequence.length == 7);
		assertTrue(sequence[0].equals(BigInteger.valueOf(0)));
		assertTrue(sequence[1].equals(BigInteger.valueOf(1)));
		assertTrue(sequence[2].equals(BigInteger.valueOf(1)));
		assertTrue(sequence[3].equals(BigInteger.valueOf(2)));
		assertTrue(sequence[4].equals(BigInteger.valueOf(3)));
		assertTrue(sequence[5].equals(BigInteger.valueOf(5)));
		assertTrue(sequence[6].equals(BigInteger.valueOf(8)));
		
		// big
		sequence = testObj.fibonacci(2000);
		assertTrue(sequence.length == 2000);
		

	}

	/**
	 * Test the fibonacci service for accuracy
	 *
	 */
	@Test
	public void testService()
	{
		FibonacciServiceResource testObj = new FibonacciServiceResource();
		
		// Happy Path
		
		// 0
		FibonacciResult fibresult = testObj.fibonacciService("0");
		assertTrue(fibresult.getOutput().length == 0);
		
		// 1
		fibresult = testObj.fibonacciService("1");
		assertTrue(fibresult.getOutput().length == 1);
		assertTrue(fibresult.getOutput()[0].equals(BigInteger.valueOf(0)));
			
		// 2
		fibresult = testObj.fibonacciService("2");
		assertTrue(fibresult.getOutput().length == 2);
		assertTrue(fibresult.getOutput()[0].equals(BigInteger.valueOf(0)));
		assertTrue(fibresult.getOutput()[1].equals(BigInteger.valueOf(1)));
		
		// Errors
		try
		{
			fibresult = testObj.fibonacciService("-1");
			fail();
		}
		catch (Exception e)
		{
			// properly had an error
		}		

		try
		{
			fibresult = testObj.fibonacciService("a");
			fail();
		}
		catch (Exception e)
		{
			// properly had an error
		}		
		
		try
		{
			fibresult = testObj.fibonacciService("");
			fail();
		}
		catch (Exception e)
		{
			// properly had an error
		}	
		
		try
		{
			fibresult = testObj.fibonacciService(null);
			fail();
		}
		catch (Exception e)
		{
			// properly had an error
		}	
		
		try
		{
			fibresult = testObj.fibonacciService("1.1");
			fail();
		}
		catch (Exception e)
		{
			// properly had an error
		}		

	}
	
	/**
	 * Test system performance.
	 * 
	 * Note:
	 *  We do not want this test to take very long as it will be part of every 
	 *  build. As a result we are looking for 100ms or less total run time
	 *  
	 *  We do not want this to be inconsistent unless there is a real issue. As
	 *  a result we are going to be conservative on performance - 1,000 requests
	 *  per second 
	 *  
	 *  We do want to test a reasonably complex fibonacci sequence. 
	 *
	 */
	@Test
	public void testServicePerformance()
	{
		FibonacciServiceResource testObj = new FibonacciServiceResource();
		
		long start = System.currentTimeMillis();
		for (int num = 1; num < 100; num++)
		{
			// Use significant fibonacci number
			testObj.fibonacciService("100");
		}
		
		long totalMillis = System.currentTimeMillis() - start;
		assertTrue(totalMillis < 100);
		
	}
}
