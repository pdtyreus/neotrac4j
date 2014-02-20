/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.synclab.neo4j.client.ApiError;

/**
 *
 * @author tyreus
 */
public class GraphFormatError implements ApiError{
    
    @JsonProperty
    private final String code;
    
    @JsonProperty
    private final String message;
    
    @JsonProperty
    private final String stackTrace;

    public GraphFormatError(@JsonProperty("code") String code,@JsonProperty("message") String message,@JsonProperty("stackTrace") String stackTrace) {
        this.code = code;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
    
    
}
