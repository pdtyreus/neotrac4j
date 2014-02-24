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
package com.synclab.neo4j.client.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A representation of a single Cypher statement.
 * 
 * @author pdtyreus
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
