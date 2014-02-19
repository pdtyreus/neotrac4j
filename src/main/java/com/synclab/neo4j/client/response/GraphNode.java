/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.synclab.neo4j.client.DetachedNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;

/**
 * A read-only PropertyContainer for deserializing "graph" format responses.
 *
 * @author tyreus
 */
public class GraphNode implements DetachedNode {

    @JsonProperty
    private final HashMap<String, Object> properties;
    @JsonProperty
    private final String id;
    @JsonProperty
    private final List<String> labels;

    public GraphNode(@JsonProperty("id") String id, @JsonProperty("labels") List<String> labels, @JsonProperty("properties") HashMap<String, Object> properties) {
        this.properties = properties;
        this.id = id;
        this.labels = labels;
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
        throw new UnsupportedOperationException("These GraphNodes are ready only. Use a Cypher statement to update property values");
    }

    public Object removeProperty(String string) {
        throw new UnsupportedOperationException("These GraphNodes are ready only. Use a Cypher statement to update property values");
    }

    public Iterable<String> getPropertyKeys() {
        return properties.keySet();
    }

    public long getId() {
        return Long.parseLong(id);
    }

    public Iterable<Label> getLabels() {
        
        List<Label> labelList= new ArrayList();
        
        for (String l : labels) {
            labelList.add(DynamicLabel.label(l));
        }
        
        return labelList;
    }

    
}
