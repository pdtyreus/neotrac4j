/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client;

import java.util.List;
import java.util.Set;

/**
 *
 * @author tyreus
 */
public interface BatchDetachedEntityResponse {
    List<Set<DetachedNode>> getNodes(int statementIndex);

    List<Set<DetachedRelationship>> getRelationships(int statementIndex);

    DetachedNode singleNode(int statementIndex);

    DetachedRelationship singleRelationship(int statementIndex);
    
    List<ApiError> getErrors();
}
