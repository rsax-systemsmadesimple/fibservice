package org.example.fibservice.service;

import java.math.BigInteger;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

/**
 * Fibonacci service and implementation
 * 
 *
 * @author robertsax
 *
 */
@Path("/fibonacci")
@Produces(MediaType.APPLICATION_JSON)
public class FibonacciServiceResource
{
	/** The logger */
	private static final Logger logger = LoggerFactory.getLogger(FibonacciServiceResource.class);

	/**
	 * Service endpoint for producing the fibonacci sequence.
	 *
	 * @param length The length of the resulting sequence. This must be positive
	 * @return The resulting fibonacci sequence or error.
	 */
	@GET
	@Timed
	public FibonacciResult fibonacciService(@QueryParam("n") String n)
	{
		// Done this way to make sure all combinations lead to a consistent 400 error
		if (StringUtils.isBlank(n) || !StringUtils.isNumeric(n))
		{
			throw new BadRequestException("Parameter 'n' must be a positive integer");
		}
		int length = Integer.parseInt(n);

		FibonacciResult result = null;

		// Note - exceptions will propagate
		BigInteger[] fibArray = fibonacci(length);
		result = new FibonacciResult(fibArray);

		return result;
	}

	/**
	 * Create the sequence of fibonacci numbers given the number of sequence
	 * items desired.
	 * 
	 * Note: This solution is significantly less performant than using a long,
	 * but a long cannot represent sequences above ~94.
	 * 
	 *
	 * @param length The length of the resulting sequence
	 * @return The array of fibonacci numbers
	 */
	BigInteger[] fibonacci(int length)
	{
		if (length < 0)
		{
			throw new IllegalArgumentException("Illegal length. Length must be greater than or equal to 0. length=" + length);
		}

		BigInteger[] result;
		try
		{
			result = new BigInteger[length];
			if (length > 0)
			{
				result[0] = BigInteger.valueOf(0);
				if (length > 1)
				{
					result[1] = BigInteger.valueOf(1);

					int fib = 2;
					while (fib < length)
					{
						result[fib] = result[fib - 1].add(result[fib - 2]);
						fib++;
					}
				}
				// throw new RuntimeException("test");
			}
		}
		catch (Exception e)
		{
			// Exception shouldn't have happened. Log the reason
			logger.error("Exception calculating fibonacci. " + e.getMessage());
			logger.debug("Exception details", e);

			throw new RuntimeException("Internal Error. length=" + length);
		}

		return result;
	}

}
