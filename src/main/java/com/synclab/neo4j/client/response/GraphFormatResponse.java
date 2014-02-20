/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.response;

import com.synclab.neo4j.client.DetachedEntityResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.synclab.neo4j.client.ApiError;
import com.synclab.neo4j.client.BatchDetachedEntityResponse;
import com.synclab.neo4j.client.DetachedNode;
import com.synclab.neo4j.client.DetachedRelationship;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tyreus
 */
public class GraphFormatResponse implements DetachedEntityResponse, BatchDetachedEntityResponse {

    @JsonProperty
    private final List<GraphFormatResult> results;
    @JsonProperty
    private final List<GraphFormatError> errors;

    public GraphFormatResponse(@JsonProperty("results") List<GraphFormatResult> results, @JsonProperty("errors") List<GraphFormatError> errors) {
        this.results = results;
        this.errors = errors;
    }

    @Override
    public List<Set<DetachedNode>> getNodes(int statementIndex) {

        if (results.isEmpty()) {
            return new ArrayList();
        }
        
        GraphFormatResult result = results.get(statementIndex);

        if (result.getData().isEmpty()) {
            return new ArrayList();
        }

        List<Set<DetachedNode>> nodes = new ArrayList();

        for (GraphFormatData data : result.getData()) {
            //each "graph" is like a row
            GraphFormatGraph graph = data.getGraph();
            Set<DetachedNode> rList = new HashSet();
            for (GraphNode node : graph.getNodes()) {
                rList.add(node);
            }
            nodes.add(rList);

        }
        return nodes;

    }

    @Override
    public List<Set<DetachedRelationship>> getRelationships(int statementIndex) {
        
        if (results.isEmpty()) {
            return new ArrayList();
        }
        
        GraphFormatResult result = results.get(statementIndex);

        if (result.getData().isEmpty()) {
            return new ArrayList();
        }

        List<Set<DetachedRelationship>> nodes = new ArrayList();

        for (GraphFormatData data : result.getData()) {
            //each "graph" is like a row
            GraphFormatGraph graph = data.getGraph();
            Set<DetachedRelationship> rList = new HashSet();
            for (GraphRelationship node : graph.getRelationships()) {
                rList.add(node);
            }
            nodes.add(rList);

        }
        return nodes;
    }

    @Override
    public DetachedNode singleNode(int statementIndex) {

        List<Set<DetachedNode>> nodes = getNodes(statementIndex);

        if (nodes.isEmpty()) {
            return null;
        }

        Set<DetachedNode> nodeSet = new HashSet();
        for (Set<DetachedNode> s : nodes) {
            for (DetachedNode n : s) {
                nodeSet.add(n);
            }
        }

        if (nodeSet.size() > 1) {
            throw new RuntimeException("Expected 0 or 1 nodes and " + nodeSet.size() + " returned");
        }

        if (nodeSet.isEmpty()) {
            return null;
        }

        return nodeSet.iterator().next();
    }

    @Override
    public DetachedRelationship singleRelationship(int statementIndex) {
        List<Set<DetachedRelationship>> nodes = getRelationships(statementIndex);

        if (nodes.isEmpty()) {
            return null;
        }

        Set<DetachedRelationship> nodeSet = new HashSet();
        for (Set<DetachedRelationship> s : nodes) {
            for (DetachedRelationship n : s) {
                nodeSet.add(n);
            }
        }

        if (nodeSet.size() > 1) {
            throw new RuntimeException("Expected 0 or 1 relationships and " + nodeSet.size() + " returned");
        }

        if (nodeSet.isEmpty()) {
            return null;
        }

        return nodeSet.iterator().next();
    }

    public List<Set<DetachedNode>> getNodes() {
        return getNodes(0);
    }

    public List<Set<DetachedRelationship>> getRelationships() {
        return getRelationships(0);
    }

    public DetachedNode singleNode() {
        return singleNode(0);
    }

    public DetachedRelationship singleRelationship() {
        return singleRelationship(0);
    }

    public List<ApiError> getErrors() {
        if (errors.isEmpty()) {
            return new ArrayList();
        }
        if (errors.size() > 0) {

            List<ApiError> rList = new ArrayList();

            for (GraphFormatError n : errors) {
                rList.add(n);
            }

            return rList;

        } else {
            return new ArrayList();
        }
    }
}
