package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.dsl.GraphSTMTBuilder

/**
 * Represents a model specifically designed for edge definitions in a graph structure.
 *
 * This interface combines several capabilities to provide a flexible framework for constructing
 * and managing edges within a graph:
 *
 * - `Model<GraphSTMT>`: Supports the conversion of the edge model to a list of `GraphSTMT` instances
 *   for integration into a graph Abstract Syntax Tree (AST).
 * - `HasComment`: Adds support for attaching an optional comment to the edge model.
 * - `HasAttributes`: Provides the ability to define and manage attributes as key-value pairs
 *   associated with the edge instance.
 * - `HasEdgeDistributions`: Manages the distribution of edges to target nodes within the graph.
 * - `EdgeAttributeGroupModel`: Adds functionality for defining attribute groups specific to edges.
 *
 * Using the `EdgeModel` interface, the edge's attributes, comments, and connections can be
 * programmatically configured to represent edges in a graph definition. It allows constructing
 * edges that accurately describe the relationships within a graph, adhering to the DOT language structure.
 */
public interface EdgeModel :
    Model<GraphSTMT>,
    HasComment,
    HasAttributes,
    HasEdgeDistributions,
    EdgeAttributeGroupModel {

    override fun toAST() = GraphSTMTBuilder {
        comment?.let {
            comment(it)
        }
        edge({
            for (target in targets) {
                load(target.toAST())
            }
        }) {
            for (attr in attributes) {
                load(attr.toAST())
            }
        }
    }.stmts
}
