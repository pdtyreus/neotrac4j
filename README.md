neotrac4j
=========

*Update Jan 2017*

There are now much better options for connecting to Neo4j from Java. I would recommend using one of the following options for any project:

 * [Neo4j Java Driver](https://github.com/neo4j/neo4j-java-driver)
 * [Neo4j OGM](https://github.com/neo4j/neo4j-ogm)
 * [Spring Data Neo4j](https://projects.spring.io/spring-data-neo4j/)

Java Transactional REST API Client for Neo4j
--------------------------------------------

[Neo4j](http://neo4j.org) is a high performance graph database that supports a proprietary query 
language and ACID transactions. It was originally born as an embedded Java
database with a native Java API for reading and writing data. As time went on, the
Neo4j team introduced a descriptive query language called Cypher and a REST API. The REST
API allows for several modes of operation, but the one of particular interest to me
is the endpoint that accepts Cypher queries directly.

# What is NeoTRAC4J?

NeoTRAC4J is a clean-room implementation of a java client for the Neo4J REST API. It is designed
specifically for neo4j 2.0+ and Cypher.

## Features

 * Designed specifically for the client/server use case (i.e. Neo4j runs on a separate instance from your application).
 * Uses the [transactional API endpoint](http://docs.neo4j.org/chunked/milestone/rest-api-transactional.html).
 * Submit multiple Cypher statements and they will be automatically batched and handled in a single transaction.
 * Parses the response using the new [graph response format](http://docs.neo4j.org/chunked/milestone/rest-api-transactional.html).
 * Jackson and Jersey client for JSON handling.

## What is NeoTRAC4J not?

NeoTRAC4J is not a drop in replacement for [GraphDatabaseService](http://docs.neo4j.org/chunked/milestone/tutorials-java-embedded-setup.html). It
does not behave like the native Java API for updating nodes and following traversals. For 
something like that, see the [Java REST Binding](https://github.com/neo4j/java-rest-binding) project.

Specifically, every node and relationship returned from the API is completely *detached* from the database.
You must construct your Cypher queries to return all the data you plan to read. Also, you
cannot update detached nodes directly. You must create a Cypher statement to update properties
or relationships on a detached node.

# Usage

Retrieve a single detached node:

```java
TransactionalApiClient client = new TransactionalApiClient("localhost",7474);
String queryString = "MATCH (u:User)-[:HAS_DEVICE]->(d:Device {uuid: {uuid}}) RETURN u";
Map parameters = new HashMap<>();
parameters.put("uuid", "12345");
DetachedNode node = client.executeSingleQuery(queryString, parameters).singleNode();
```

Retrieve multiple detached nodes:

```java
TransactionalApiClient client = new TransactionalApiClient("localhost",7474);
String queryString = "MATCH (u:User {uuid: {uuid}})-[:HAS_DEVICE]->(d:Device) RETURN d";
Map parameters = new HashMap<>();
parameters.put("uuid", "12345");
List<List<DetachedNode>> nodes = client.executeSingleQuery(queryString, parameters).getNodes();
//flatten results
List<DetachedNode> results = new ArrayList();
for (List<DetachedNode> row : nodes) {
  for (DetachedNode node : row) {
    results.add(node);
  }
}
```

Create and read in a batch transaction:

```java
List<Statement> statements = new ArrayList();
String uuid = UUID.randomUUID().toString();
Map<String, Object> map = new HashMap();
map.put("uuid", uuid);
statements.add(new Statement("MERGE (u:User {uuid: {uuid}}) RETURN u", map));
Map<String, Object> parameters = new HashMap<>();
parameters.put("email", "someone@somewhere.com");
parameters.put("uuid", uuid);
statements.add(new Statement("MERGE (n:EmailAddress {email: {email}}) RETURN n", parameters));
statements.add(new Statement("MATCH (u:User {uuid: {uuid}}), (e:EmailAddress {email: {email}}) MERGE (u)-[:HAS_EMAIL_ADDRESS]->(e)", parameters));

//execute the batch in one transaction
BatchDetachedEntityResponse response = client.executeBatchedQueries(statements);

//check for errors
if (!response.getErrors().isEmpty()) {
  throw new RuntimeException(response.getErrors().get(0).getMessage());
}

//get the first node returned from the first statement in the batch
//this is still a tad ugly
DetachedNode node = response.getNodes().get(0).iterator().next();
```
