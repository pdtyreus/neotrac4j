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
package com.synclab.neo4j.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.synclab.neo4j.client.ApiError;

/**
 *
 * @author pdtyreus
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
