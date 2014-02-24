/*
 * Copyright (C) 2014 pdtyreus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.synclab.neo4j.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.synclab.neo4j.client.request.Statement;
import com.synclab.neo4j.client.request.TransactionalRequest;
import com.synclab.neo4j.client.response.GraphFormatResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An Neo4j REST API client backed by Jersey's {@link com.sun.jersey.api.client.Client}. The client
 * can submit single Cypher statements or multiple Cypher statements as a transactional batch
 * and parse the response into detached java POJOs.
 * 
 * @author pdtyreus
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
        baseUri = "http://" + host + ":" + port + "/db/data";
    }

    public DetachedEntityResponse executeSingleQuery(String cypher, Map<String, Object> params) {
        final List<Statement> statements = new ArrayList();
        statements.add(new Statement(cypher, params));
        TransactionalRequest request = new TransactionalRequest(statements);

        WebResource webResource = client.resource(resourceUrl + "/commit");

        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, request);

        GraphFormatResponse r = response.getEntity(GraphFormatResponse.class);

        return r;
    }

    public BatchDetachedEntityResponse executeBatchedQueries(List<Statement> statements) {
        TransactionalRequest request = new TransactionalRequest(statements);

        WebResource webResource = client.resource(resourceUrl + "/commit");

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
