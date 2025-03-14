package com.github.kamiazya.graphviz.ast

public sealed interface AST

public sealed interface AttributesSTMT : AST

public sealed interface DotSTMT : AST

public sealed interface GraphSTMT : AST

public interface Parents<STMT : AST> {
    var children: MutableList<STMT>
}

public data class Dot(
    override var children: MutableList<DotSTMT>,
) : AST, Parents<DotSTMT>

public data class Comment(
    public var value: String,
    public var kind: Kind,
) : AST, AttributesSTMT, DotSTMT, GraphSTMT {
    enum class Kind {
        BLOCK, SLASH, MACRO
    }
}

public data class AttributeGroup(
    public var kind: AttributeGroup.Kind,
    override var children: MutableList<AttributesSTMT>,
) : AST, GraphSTMT, Parents<AttributesSTMT> {
    enum class Kind {
        GRAPH, NODE, EDGE
    }
}

public data class RootGraph(
    public var id: Literal? = null,
    public var directed: Boolean,
    public var strict: Boolean,
    override var children: MutableList<GraphSTMT>,
) : AST, DotSTMT, Parents<GraphSTMT>

public data class Subgraph(
    public var id: Literal? = null,
    override var children: MutableList<GraphSTMT>,
) : AST, GraphSTMT, Parents<GraphSTMT>

public data class Attribute(
    public var key: Literal,
    public var value: Literal,
) : AST, AttributesSTMT, GraphSTMT

public data class Literal(
    public var value: String,
    public var quated: Quate = Quate.QUATED,
) : AST {
    enum class Quate {
        QUATED, UNQUATED, HTML
    }
}

public data class Node(
    public var id: Literal,
    override var children: MutableList<AttributesSTMT>,
) : AST, GraphSTMT, Parents<AttributesSTMT>

public interface EdgeDistribution : AST

public data class NodeRef(
    public var id: Literal,
    public var port: Literal? = null,
    public var compass: Literal? = null,
) : AST, EdgeDistribution

public data class ClusterNodeRefs(
    public var refs: List<NodeRef>,
) : AST, EdgeDistribution

public data class Edge(
    public var targets: List<EdgeDistribution>,
    override var children: MutableList<AttributesSTMT>,
) : AST, GraphSTMT, Parents<AttributesSTMT>
