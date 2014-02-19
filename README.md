neotrac4j
=========

Java Transactional REST API Client for Neo4j
--------------------------------------------

[Neo4j](http://neo4j.org) is a high performance graph database that supports a proprietary query 
language and ACID transactions. It was originally born as an embedded Java
database with a native Java API for reading and writing data. As time went on, the
Neo4j team introduced a descriptive query language called Cypher and a REST API. The REST
API allows for several modes of operation, but the one of particular interest to me
is the endpoint that accepts Cypher queries directly.

Version 2.0 of Neo4j introduced the concept of labels and moved Cypher into position
as preferred way of communicating with Neo4j.

The Java API still seems to be the most high-performance way to read and write from
Neo4j for embedded databases, but once you move to a client/server model, this becomes 
less practical. 

My biggest complaint with Neo4j right now is that there does not seem to be many 
well-documented best practices for interacting with the database in client/server mode.
In fact there are far too many different ways to do things, many of which lead
rapidly into performance rabbit holes.

I evaluated the following tools for interacting with the database, in order of most high level to most low level:

 * [Spring Data Neo4j](http://projects.spring.io/spring-data-neo4j/) - As of the time of
this writing, SDN4J does not support Neo4j 2.0.
 * [Java REST Bindings](https://github.com/neo4j/java-rest-binding) - This project
tries to wrap the REST API in order to make it look like the original Java API for 
embedded use. Unfortunately I found performance to be quite poor over even a local
network and transaction semantics were quite unclear.
 * [RestAPIFacade](https://github.com/neo4j/java-rest-binding) - This seems to be 
what's under the hood of the Java REST Bindings and allows for access to batch transactions. However,
I found myself writing a lot of boilerplate to deserialize the responses.
 * [REST API](http://docs.neo4j.org/chunked/milestone/rest-api-nodes.html) - The 
standard REST API supports a transactional endpoint and the ability to submit Cypher
queries directly. However, the response format is quite verbose and not intuitive to parse.

### Project Goals

What I've set out to build is a simple client for submitting Cypher queries to the
transactional REST API endpoint and parsing the response back into POJOs.

I rely on Jackson for JSON parsing and use the new 
[graph response format](http://docs.neo4j.org/chunked/milestone/rest-api-transactional.html)
of the transactional REST API.
