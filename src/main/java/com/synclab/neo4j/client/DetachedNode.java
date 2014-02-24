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

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.PropertyContainer;

/**
 * A implementation of {@link org.neo4j.graphdb.PropertyContainer} similar to {@link org.neo4j.graphdb.Node} except 
 * that this version is completely detached from the database. All properties are
 * set after the initial query response and nothing is lazily loaded. Writing to
 * a DetachedNode will not update the database.
 * 
 * @author pdtyreus
 */
public interface DetachedNode extends PropertyContainer {
    public long getId();
    public Iterable<Label> getLabels();
}
