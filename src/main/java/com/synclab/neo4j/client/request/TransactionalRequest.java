/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.request;

import java.util.List;

/**
 *
 * @author tyreus
 */
public class TransactionalRequest {
    private final List<Statement> statements;

    public TransactionalRequest(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }
    
}
