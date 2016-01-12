package org.example.fibservice.service;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class holds the data that will be the result of the FibonacciService
 * 
 *
 * @author robertsax
 *
 */
public class FibonacciResult
{
	/** The fibonacci output array */
	private BigInteger[] output;
		
	/**
	 * Constructor for the successful case
	 *
	 * @param theOutput The output sequence
	 */
	public FibonacciResult(BigInteger[] theOutput)
	{
		output = theOutput;
	}
		
	/**
	 * Get the output fibonacci sequence. 
	 * 
	 *
	 * @return The fibonacci sequence. Null if an error ocurred.
	 */
	@JsonProperty
	public BigInteger[] getOutput()
	{
		return output;
	}
	
}
