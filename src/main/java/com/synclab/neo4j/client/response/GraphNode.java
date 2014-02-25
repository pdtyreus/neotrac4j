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
import com.synclab.neo4j.client.DetachedNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;

/**
 * A POJO representation of an element from the <code>nodes</code> JSON array
 * in the graph format response from the neo4j transactional REST endpoint.
 *
 * @author pdtyreus
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

    @Override
    public boolean hasProperty(String string) {
        return properties.containsKey(string);
    }

    @Override
    public Object getProperty(String string) {
        return properties.get(string);
    }

    @Override
    public Object getProperty(String string, Object o) {
        Object ret = properties.get(string);
        return (ret == null ? o : ret);
    }

    @Override
    public Iterable<String> getPropertyKeys() {
        return properties.keySet();
    }

    @Override
    public long getId() {
        return Long.parseLong(id);
    }

    @Override
    public Iterable<Label> getLabels() {
        
        List<Label> labelList= new ArrayList();
        
        for (String l : labels) {
            labelList.add(DynamicLabel.label(l));
        }
        
        return labelList;
    }

    
}
