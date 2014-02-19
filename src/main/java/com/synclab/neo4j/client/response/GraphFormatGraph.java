/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.response;

import com.synclab.neo4j.client.response.GraphNode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author tyreus
 */
public class GraphFormatGraph {
    @JsonProperty
    private final List<GraphNode> nodes;
    @JsonProperty
    private final List<GraphRelationship> relationships;

    public GraphFormatGraph(@JsonProperty("nodes") List<GraphNode> nodes, @JsonProperty("relationships") List<GraphRelationship> relationships) {
        this.nodes = nodes;
        this.relationships = relationships;
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public List<GraphRelationship> getRelationships() {
        return relationships;
    }
    
    
}
