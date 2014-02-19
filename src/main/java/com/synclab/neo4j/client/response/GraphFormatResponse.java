/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tyreus
 */
public class GraphFormatResponse {

    @JsonProperty
    private final List<GraphFormatResult> results;
    @JsonProperty
    private final List<GraphFormatError> errors;

    public GraphFormatResponse(@JsonProperty("results") List<GraphFormatResult> results, @JsonProperty("errors") List<GraphFormatError> errors) {
        this.results = results;
        this.errors = errors;
    }

    public List<GraphFormatError> getErrors() {
        return errors;
    }

    public List<GraphNode> getNodes() {

        if (results.size() > 0) {
            return results.get(0).getData().get(0).getGraph().getNodes();
        } else {
            return new ArrayList();
        }
    }

    public List<GraphRelationship> getRelationships() {
        if (results.size() > 0) {
            return results.get(0).getData().get(0).getGraph().getRelationships();
        } else {
            return new ArrayList();
        }
    }
}
