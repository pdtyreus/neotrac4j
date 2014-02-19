/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author tyreus
 */
public class GraphFormatData {
    @JsonProperty
    private final GraphFormatGraph graph;

    public GraphFormatData(@JsonProperty("graph") GraphFormatGraph graph) {
        this.graph = graph;
    }

    public GraphFormatGraph getGraph() {
        return graph;
    }
    
    
}
