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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author pdtyreus
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
