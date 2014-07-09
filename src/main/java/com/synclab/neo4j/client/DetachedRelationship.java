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

/**
 * A implementation of {@link DetachedPropertyContainer} similar to {@link org.neo4j.graphdb.Relationship} except 
 * that this version is completely detached from the database. All properties are
 * set after the initial query response and nothing is lazily loaded. Writing to
 * a DetachedRelationship will not update the database.
 * 
 * @author pdtyreus
 */
public interface DetachedRelationship extends DetachedPropertyContainer {

    public long getEndNodeId();

    public long getId();

    public long getStartNodeId();
    
    public String getType();
}
