package com.github.kamiazya.graphviz.ast

/**
 * Represents a group of attributes categorized by their type within a DOT graph Abstract Syntax Tree (AST).
 *
 * The `AttributeGroup` class implements the `AST`, `GraphSTMT`, and `Parents` interfaces, indicating that it:
 * - Acts as an element of the AST used to represent the structure of a DOT graph.
 * - Serves as a graph statement contributing to the definition of graph nodes, edges, or attributes.
 * - Behaves as a parent container managing a collection of child `AttributesSTMT` elements.
 *
 * This class provides the ability to group attributes by their kind, which is defined using the `Kind` enum.
 * The `kind` property specifies the type of attributes the group represents, such as graph, node, or edge attributes.
 *
 * @property kind Represents the type of attributes the group contains (e.g., GRAPH, NODE, EDGE).
 * @property children A mutable list of `AttributesSTMT` elements that belong to this attribute group.
 */
data class AttributeGroup(
    var kind: Kind,
    override var children: MutableList<AttributesSTMT>,
) : AST, GraphSTMT, Parents<AttributesSTMT> {
    /**
     * Represents the types of attribute groups in a DOT graph.
     *
     * This sealed class defines the possible kinds of attribute groups based on their scope or association.
     * It is used to categorize attributes as being associated with the graph, nodes, or edges.
     *
     * - GRAPH: Attributes associated with the entire graph.
     * - NODE: Attributes associated with graph nodes.
     * - EDGE: Attributes associated with graph edges.
     *
     * This classification is used in the Abstract Syntax Tree (AST) representation of DOT graphs
     * to organize attributes effectively and enforce their usage context.
     */
    sealed class Kind {
        /**
         * Represents the kind of attributes associated with the entire graph in a DOT representation.
         *
         * The `GRAPH` kind is used to specify global attributes that apply to the entire graph
         * within the Abstract Syntax Tree (AST) for DOT graphs. These attributes encompass metadata or
         * styling information that applies universally to the graph and is not limited to specific nodes or edges.
         *
         * This object serves as a constant in the `Kind` sealed class hierarchy, allowing the categorization
         * of attributes by their scope of application.
         */
        data object GRAPH : Kind()

        /**
         * Represents the kind of attributes associated with graph nodes in a DOT representation.
         *
         * The `NODE` kind is used to specify attributes that apply to nodes within the Abstract Syntax Tree (AST)
         * for DOT graphs. These attributes can include node-specific styles, metadata, or other characteristics
         * that define the visual or logical properties of a node in the graph.
         *
         * This object serves as a constant in the `Kind` sealed class hierarchy, enabling the categorization
         * of attributes by their scope of application to nodes.
         */
        data object NODE : Kind()

        /**
         * Represents the `edge` kind used for defining and categorizing
         * edge-specific attributes or functionality within the graph model system.
         *
         * This object is part of the `Kind` classification, specifically utilized
         * in contexts where edges of a graph, their attributes, or associated
         * data are referenced.
         */
        data object EDGE : Kind()
    }
}
