/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client.graphformat;

import com.synclab.neo4j.client.response.GraphFormatResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tyreus
 */
public class GraphFormatResponseTest {

    @Test
    public void testDeserialize() {
        ObjectMapper mapper = new ObjectMapper();

        InputStream is = GraphFormatResponseTest.class.getResourceAsStream("/graph_format_response.json");

        try {
            GraphFormatResponse response = mapper.readValue(is, GraphFormatResponse.class);

            assertEquals(3, response.getNodes().size());
            assertEquals(2, response.getRelationships().size());

            Object spokes = response.getNodes().get(0).getProperty("spokes");

            assertEquals(3, ((Integer) spokes).intValue());

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