package com.github.kamiazya.graphviz.model

/**
 * EdgeAttributeGroup is an interface for defining attributes related to edges in a graph model.
 *
 * This interface provides a common structure to represent specific attributes of an edge,
 * such as its color. Implementations can extend this interface to define additional
 * attributes as needed.
 */
public interface EdgeAttributeGroup {
    var color: String?
}
