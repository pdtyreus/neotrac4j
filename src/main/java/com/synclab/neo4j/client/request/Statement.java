/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tyreus
 */
public class Statement {
    private final String statement;
    private final Map<String,Object> parameters;
    private final List<String> resultDataContents;

    public Statement(String statement, Map<String, Object> parameters) {
        this.statement = statement;
        this.parameters = parameters;
        resultDataContents = new ArrayList();
        resultDataContents.add("graph");
        //resultDataContents.add("row");
    }

    public String getStatement() {
        return statement;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public List<String> getResultDataContents() {
        return resultDataContents;
    }
    
    
}
