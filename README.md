AskFast API Java
================

Java library to interact with [AskFast](http://www.ask-fast.com).

Overview
--------

This is library to help you create dialogs which interact with the AskFast system. You can download the latest pre-build version here
[jar](https://github.com/askfast/askfast-api-java/blob/master/bin/askfast-api-java-1.0.0.jar?raw=true)

------------

Current version is 0.1.0


Requirements
------------

You can install this library in any Java application or application server. The library depends primarily on jackson libraries. The following listing shows all the libraries that need to be present in an application that uses the AskFast Library:

	* jettison-1.2.jar
	* org.apache.oltu.oauth2.client-0.31.jar
	* org.apache.oltu.oauth2.common-0.31.jar
	* jackson-annotations-2.0.0.jar
	* jackson-core-2.0.0.jar
	* jackson-databind-2.0.0.jar
	* javax.servlet-api-3.0.1.jar

All libraries have been included in the [lib/](https://github.com/askfast/askfast-api-java/tree/master/lib) folder.

Build
-----
Running MAVEN will generate the jar file, which will be placed in the target/ folder.

Using the Maven artifact
------------------------

Examples
--------
You can see an example of the ASK-Fast API in com.askfast.examples.SimpleExampleServlet.java
Create a server and load the servlet: 
* A sample question can be seen with:
	* GET: http://localhost:8080/simple_example
* An outbound call can be initiated with:
	* GET: http://localhost:8080/simple_example?adapterId=[adapterID]&accountId=[accountId]&refreshToken=[refreshToken]&toAddress=[toAddress]