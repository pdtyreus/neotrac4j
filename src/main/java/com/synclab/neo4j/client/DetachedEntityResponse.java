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

/**
 * A representation of the response from a single Cypher statement. The response
 * consists of two collections, nodes and relationships, each represented as a List
 * of a Set of detached entities. The entries in the Set can be thought of as values for
 * each column in a given row in a in table and the entries in the list can be
 * thought of as the rows.
 * <p>
 * There are convenience methods for getting a single DetachedNode or single
 * DetachedRelationship.
 * 
 * @author pdtyreus
 */
public interface DetachedEntityResponse {

    List<List<DetachedNode>> getNodes();

    List<List<DetachedRelationship>> getRelationships();

    DetachedNode singleNode();

    DetachedRelationship singleRelationship();
    
    List<ApiError> getErrors();
    
}
