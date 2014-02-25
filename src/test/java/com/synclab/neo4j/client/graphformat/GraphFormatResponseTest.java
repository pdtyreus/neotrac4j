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
package com.synclab.neo4j.client.graphformat;

import com.synclab.neo4j.client.response.GraphFormatResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synclab.neo4j.client.DetachedNode;
import java.awt.Label;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pdtyreus
 */
public class GraphFormatResponseTest {

    @Test
    public void testDeserialize() {
        ObjectMapper mapper = new ObjectMapper();

        InputStream is = GraphFormatResponseTest.class.getResourceAsStream("/graph_format_response.json");

        try {
            GraphFormatResponse response = mapper.readValue(is, GraphFormatResponse.class);

            assertEquals(3, response.getNodes().get(0).size());
            assertEquals(2, response.getRelationships().get(0).size());

            DetachedNode firstNode = response.getNodes().get(0).iterator().next();
            assertNotNull(firstNode);
            assertNotNull(firstNode.getLabels());
            assertNotNull(firstNode.getPropertyKeys());
            

        } catch (IOException e) {
            fail("unable to parse graph format: " + e.getMessage());
        }

        is = GraphFormatResponseTest.class.getResourceAsStream("/graph_format_response_error.json");

        try {
            GraphFormatResponse response = mapper.readValue(is, GraphFormatResponse.class);

            assertEquals(0, response.getNodes().size());
            assertEquals(0, response.getRelationships().size());

            assertEquals(1, response.getErrors().size());

            assertEquals("Neo.ClientError.Statement.InvalidSyntax", response.getErrors().get(0).getCode());

        } catch (IOException e) {
            fail("unable to parse graph format: " + e.getMessage());
        }
    }
}