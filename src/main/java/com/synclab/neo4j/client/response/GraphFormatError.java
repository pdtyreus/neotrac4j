/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author tyreus
 */
public class GraphFormatError {
    
    @JsonProperty
    private final String code;
    
    @JsonProperty
    private final String message;

    public GraphFormatError(@JsonProperty("code") String code,@JsonProperty("message") String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
    
    
}
