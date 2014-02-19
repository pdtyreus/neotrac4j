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
