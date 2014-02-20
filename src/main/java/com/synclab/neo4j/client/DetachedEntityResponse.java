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
public interface DetachedEntityResponse {

    List<Set<DetachedNode>> getNodes();

    List<Set<DetachedRelationship>> getRelationships();

    DetachedNode singleNode();

    DetachedRelationship singleRelationship();
    
    List<ApiError> getErrors();
    
}
