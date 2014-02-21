/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.synclab.neo4j.client.request.Statement;
import com.synclab.neo4j.client.request.TransactionalRequest;
import com.synclab.neo4j.client.response.GraphFormatResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tyreus
 */
public class TransactionalApiClient {

    private static final String TRANSACTIONAL_ENDPOINT = "/db/data/transaction";
    private final Client client;
    private final String resourceUrl;
    private final String baseUri;
    private static final Logger logger = LoggerFactory.getLogger(TransactionalApiClient.class);

    public TransactionalApiClient(String host, int port) {
        client = Client.create();
        //client.addFilter(new LoggingFilter(System.out));
        resourceUrl = "http://" + host + ":" + port + TRANSACTIONAL_ENDPOINT;
        baseUri =  "http://" + host + ":" + port + "/db/data";
    }

    public DetachedEntityResponse executeSingleQuery(String cypher, Map<String, Object> params) {
        final List<Statement> statements = new ArrayList();
        statements.add(new Statement(cypher, params));
        TransactionalRequest request = new TransactionalRequest(statements);

        WebResource webResource = client.resource(resourceUrl+"/commit");

        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, request);

        GraphFormatResponse r = response.getEntity(GraphFormatResponse.class);

        return r;
    }
    
    public BatchDetachedEntityResponse executeBatchedQueries(List<Statement> statements) {
        TransactionalRequest request = new TransactionalRequest(statements);

        WebResource webResource = client.resource(resourceUrl+"/commit");

        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, request);

        GraphFormatResponse r = response.getEntity(GraphFormatResponse.class);

        return r;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public Client getRestClient() {
        return client;
    }
}
