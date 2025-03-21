package com.github.kamiazya.graphviz.ast.dsl

import com.github.kamiazya.graphviz.ast.Attribute
import com.github.kamiazya.graphviz.ast.AttributeGroup
import com.github.kamiazya.graphviz.ast.Comment
import com.github.kamiazya.graphviz.ast.Edge
import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.Literal
import com.github.kamiazya.graphviz.ast.Node
import com.github.kamiazya.graphviz.ast.Subgraph

/**
 * A builder class designed for constructing Graph Statements (GraphSTMT) within a DOT graph.
 *
 * The `GraphSTMTBuilder` is specifically used in the construction of the Abstract Syntax Tree (AST)
 * representation of a DOT graph, enabling the definition of nodes, edges, attributes, subgraphs,
 * comments, and attribute groups. It provides a DSL-style approach for organizing and configuring
 * these components.
 *
 * This builder is part of a structured DSL environment, marked by the `@ASTDslMarker` annotation,
 * which ensures isolated scope usage and prevents access to outer contexts.
 *
 * @constructor Creates a `GraphSTMTBuilder` instance, invoking the provided `body` lambda in its
 * DSL context to initialize and add statements to the AST structure.
 *
 * @param body A lambda function allowing for the declaration of graph statements within the builder.
 */
@ASTDslMarker
class GraphSTMTBuilder(body: GraphSTMTBuilder.() -> Unit) : STMTBuilder<GraphSTMT>(), LiteralBuilder {
    init {
        body()
    }

    /**
     * Appends a comment to the list of statements in the graph's structure.
     *
     * @param value The textual content of the comment.
     * @param kind The type of comment, specified by the `Comment.Kind` enum. Defaults to `Comment.Kind.SLASH`.
     */
    fun comment(value: String, kind: Comment.Kind = Comment.Kind.SLASH) {
        stmts += Comment(
            value = value,
            kind = kind
        )
    }

    /**
     * Defines a node in the graph with the specified ID and attributes.
     *
     * This function allows the creation of a node in the graph by specifying a unique
     * identifier and providing a body block to define its attributes. The attributes are
     * configured through the `AttributeSTMTBuilder` and can include custom key-value
     * pairs or comments related to the node.
     *
     * @param id The unique identifier for the node, represented as a `Literal`.
     * @param body A lambda receiver of type `AttributeSTMTBuilder` used to define the attributes of the node.
     */
    fun node(
        id: Literal,
        body: AttributeSTMTBuilder.() -> Unit,
    ) {
        stmts += Node(
            id = id,
            children = AttributeSTMTBuilder(body).stmts
        )
    }

    /**
     * Adds an attribute to the graph's structure, represented as a key-value pair.
     *
     * @param key The key of the attribute, represented as a `Literal`.
     * @param value The value of the attribute, associated with the key, represented as a `Literal`.
     */
    fun attribute(
        key: Literal,
        value: Literal,
    ) {
        stmts += Attribute(
            key = key,
            value = value,
        )
    }

    /**
     * Defines a subgraph within the graph structure.
     *
     * This function allows the creation of a subgraph by specifying an optional identifier
     * and a block of initialization logic to configure its content. The subgraph can
     * contain nodes, edges, attributes, comments, or additional nested subgraphs.
     *
     * @param id An optional identifier for the subgraph, represented as a `Literal`.
     *           This identifier can be used to uniquely name or reference the subgraph.
     * @param init A lambda receiver for `GraphSTMTBuilder` used to define the content
     *             of the subgraph, including nodes, edges, attributes, and more.
     */
    fun subgraph(
        id: Literal?,
        init: GraphSTMTBuilder.() -> Unit,
    ) {
        stmts += Subgraph(
            id = id,
            children = GraphSTMTBuilder(init).stmts,
        )
    }

    /**
     * Defines and adds an `AttributeGroup` to the graph's structure with a specified kind and attributes.
     *
     * This function facilitates the creation of attribute groups in the graph, specifying their type
     * through the `kind` parameter and allowing the customization of their attributes using the provided
     * lambda function `body`.
     *
     * @param kind The category or type of the attribute group, as defined by `AttributeGroup.Kind`.
     *             Specifies whether the group pertains to the graph, nodes, or edges.
     * @param body A lambda receiver of type `AttributeSTMTBuilder`, allowing the definition
     *             of attributes included in the group.
     */
    fun attributeGroup(
        kind: AttributeGroup.Kind,
        body: AttributeSTMTBuilder.() -> Unit,
    ) {
        stmts += AttributeGroup(
            kind = kind,
            children = AttributeSTMTBuilder(body).stmts
        )
    }

    /**
     * Creates an edge in the graph by specifying its target nodes or clusters and its attributes.
     *
     * This function defines an edge using two separate configuration blocks: one for the edge's
     * targets (nodes or clusters it connects) and another for the edge's attributes (key-value
     * pairs or comments specific to the edge). The structure of the edge is added to the list
     * of statements within the graph's syntax tree.
     *
     * @param targets A lambda receiver of type `EdgeDistributionBuilder` used to define the target
     *                nodes or clusters connected by the edge.
     * @param body A lambda receiver of type `AttributeSTMTBuilder` used to define the attributes
     *             specific to the edge.
     */
    fun edge(
        targets: EdgeDistributionBuilder.() -> Unit,
        body: AttributeSTMTBuilder.() -> Unit,
    ) {
        stmts += Edge(
            targets = EdgeDistributionBuilder(targets).stmts,
            children = AttributeSTMTBuilder(body).stmts,
        )
    }
}
