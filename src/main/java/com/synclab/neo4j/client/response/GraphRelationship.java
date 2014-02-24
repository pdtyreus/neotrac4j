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
import com.synclab.neo4j.client.DetachedRelationship;
import java.util.HashMap;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * A read-only implementation {@link org.neo4j.graphdb.PropertyContainer} for deserializing "graph" format responses.
 * 
 * @author pdtyreus
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
