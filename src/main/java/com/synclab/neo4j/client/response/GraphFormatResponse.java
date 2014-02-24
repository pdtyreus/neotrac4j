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
 * @author pdtyreus
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
