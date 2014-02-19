/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synclab.neo4j.client;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.PropertyContainer;

/**
 *
 * @author tyreus
 */
public interface DetachedNode extends PropertyContainer {
    public long getId();
    public Iterable<Label> getLabels();
}
