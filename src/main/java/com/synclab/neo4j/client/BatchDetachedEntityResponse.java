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
package com.synclab.neo4j.client;

import java.util.List;
import java.util.Set;

/**
 * A representation of the response from multiple Cypher statement. This is
 * functionally similar to {@link DetachedEntityResponse} except that there each
 * collection of nodes is indexed by the statement in the order it was
 * submitted. So think of this as a wrapper around multiple tables, each table
 * corresponding to the results from one Cypher statement.
 *
 * @author pdtyreus
 */
public interface BatchDetachedEntityResponse {

    List<List<DetachedNode>> getNodes(int statementIndex);

    List<List<DetachedRelationship>> getRelationships(int statementIndex);

    DetachedNode singleNode(int statementIndex);

    DetachedRelationship singleRelationship(int statementIndex);

    List<ApiError> getErrors();

    Set<DetachedNode> getStartNodesForRelationship(String relationshipType, int statementIndex);

    Set<DetachedNode> getEndNodesForRelationship(String relationshipType, int statementIndex);
    
    Set<DetachedNode> getStartNodesForRelationshipAndEndNode(String relationshipType, DetachedNode endNode, int statementIndex);

    Set<DetachedNode> getEndNodesForRelationshipAndStartNode(String relationshipType, DetachedNode startNode, int statementIndex);
    
    DetachedNode getStartNodeForRelationship(DetachedRelationship relationship, int statementIndex);
    DetachedNode getEndNodeForRelationship(DetachedRelationship relationship, int statementIndex);
    

    /**
     * Returns the number of entries in the results array. This should
     * correspond to the number of statements in the batch query.
     *
     * @return int
     */
    int getResultsSize();
}
