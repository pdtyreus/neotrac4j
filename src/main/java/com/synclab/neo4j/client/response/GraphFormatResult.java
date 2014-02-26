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
 * A POJO representation of an element from <code>results</code> JSON array
 * in the graph format response from the neo4j transactional REST endpoint.
 * 
 * @author pdtyreus
 */
public class GraphFormatResult {
    @JsonProperty
    private final List<String> columns;
    @JsonProperty
    private final List<GraphFormatData> data;

    public GraphFormatResult(@JsonProperty("columns") List<String> columns,@JsonProperty("data") List<GraphFormatData> data) {
        this.columns = columns;
        this.data = data;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<GraphFormatData> getData() {
        return data;
    }
    
    
}
