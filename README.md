Fibonacci Service Example
=========================

This is an example web service that provides the Fibonacci sequence as a service that I built for an interview sample project. 

I have chosen to use Java and [Dropwizard](http://www.dropwizard.io/0.9.1/docs/) to implement this project in part because it provides an opportunity to understand this technology while simultaneously meeting the interview needs. 

The solution provides output in JSON of the format (for n=5):

	{"output":[0,1,1,2,3]}

It supports very large fibonacci sequences and numbers via BigInteger.

You will need Java 8 and maven installed to build and run this project. Examples below were run on Mac/Linux systems, so you may need to adjust if you are running on a Windows box.

### To Build

	$ mvn install

### To Run

	$ java -jar target/fibservice-0.0.1-SNAPSHOT.jar server config.yml
	
You will now be able to access:

* [Fibonacci Service](http://localhost:8080/fibonacci?n=200)
* [Operational Menu](http://localhost:8081/)


### To Test

	# mvn test

### Technologies
The main technologies utilized in are:

* Java 8
* Dropwizard
	* Jersey
	* Jetty
	* Jackson

