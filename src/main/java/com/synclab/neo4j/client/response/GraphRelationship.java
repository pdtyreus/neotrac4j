/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.synclab.neo4j.client.DetachedRelationship;
import java.util.HashMap;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 *
 * @author tyreus
 */
public class GraphRelationship implements DetachedRelationship {

    @JsonProperty
    private final HashMap<String, Object> properties;
    @JsonProperty
    private final String id;
    @JsonProperty
    private final String type;
    @JsonProperty
    private final String startNode;
    @JsonProperty
    private final String endNode;

    public GraphRelationship(@JsonProperty("id") String id,
            @JsonProperty("type") String type,
            @JsonProperty("startNode") String startNode,
            @JsonProperty("endNode") String endNode,
            @JsonProperty("properties") HashMap<String, Object> properties) {
        this.properties = properties;
        this.id = id;
        this.type = type;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public long getId() {
        return Long.parseLong(id);
    }

    public String getType() {
        return type;
    }

    public long getStartNodeId() {
        return Long.parseLong(startNode);
    }

    public long getEndNodeId() {
        return Long.parseLong(endNode);
    }

    public GraphDatabaseService getGraphDatabase() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean hasProperty(String string) {
        return properties.containsKey(string);
    }

    public Object getProperty(String string) {
        return properties.get(string);
    }

    public Object getProperty(String string, Object o) {
        Object ret = properties.get(string);
        return (ret == null ? o : ret);
    }

    public void setProperty(String string, Object o) {
        throw new UnsupportedOperationException("These GraphRelationships are ready only. Use a Cypher statement to update property values");
    }

    public Object removeProperty(String string) {
        throw new UnsupportedOperationException("These GraphRelationships are ready only. Use a Cypher statement to update property values");
    }

    public Iterable<String> getPropertyKeys() {
        return properties.keySet();
    }
}
