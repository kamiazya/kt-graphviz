package com.github.kamiazya.graphviz

public sealed interface AST {

    public sealed interface CommonSTMT : AST
    public sealed interface DotSTMT : AST, CommonSTMT
    public sealed interface GraphSTMT : AST, CommonSTMT

    public abstract class Parents<STMT : AST>(
        val children: List<STMT> = emptyList(),
    )

    public data class Dot(
        public val id: Literal?,
        public val strict: Boolean,
    ) : AST, DotSTMT, Parents<DotSTMT>()

    public data class Comment(
        public val value: String,
        public val kind: Kind,
    ) : AST, CommonSTMT {
        enum class Kind {
            BLOCK, SLASH, MACRO
        }
    }

    public data class Graph(
        public val id: Literal? = null,
        public val directed: Boolean,
        public val strict: Boolean,
    ) : AST, DotSTMT, Parents<GraphSTMT>()

    public data class Subgraph(
        public val id: Literal? = null,
    ) : AST, GraphSTMT, Parents<GraphSTMT>()

    public data class Attribute(
        public val key: Literal,
        public val value: Literal,
    ) : AST, CommonSTMT

    public data class Literal(
        public val value: String,
        public val quated: Quated,
    ) : AST {
        enum class Quated {
            QUATED, UNQUATED, HTML
        }
    }

    public data class Node(
        public val id: Literal,
    ) : AST, GraphSTMT, Parents<CommonSTMT>()

    public data class Edge(
        public val targets: List<EdgeDistribution>,
    ) : AST, GraphSTMT, Parents<CommonSTMT>()
}
