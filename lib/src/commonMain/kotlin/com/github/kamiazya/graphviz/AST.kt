package com.github.kamiazya.graphviz

public sealed interface AST {

    public sealed interface DotSTMT : AST
    public sealed interface GraphSTMT : AST

    public abstract class ParentsAST<STMT : AST>(
        val children: List<STMT> = emptyList(),
    )

    public class DotAST : AST, ParentsAST<DotSTMT>()

    public class CommentAST(
        public val value: String,
        public val kind: Kind,
    ) : AST, DotSTMT, GraphSTMT {
        enum class Kind {
            BLOCK, SLASH, MACRO
        }
    }

    public class GraphAST(
        public val id: LiteralAST?,
        public val directed: Boolean,
        public val strict: Boolean,
    ) : AST, DotSTMT, ParentsAST<GraphSTMT>()

    public class SubgraphAST(
        public val id: LiteralAST?,
    ) : AST, GraphSTMT, ParentsAST<GraphSTMT>()

    public class AttributeAST(
        public val key: LiteralAST,
        public val value: LiteralAST,
    ) : AST, DotSTMT, GraphSTMT

    public class LiteralAST(
        public val value: String,
        public val quated: Quated,
    ) : AST {
        enum class Quated {
            QUATED, UNQUATED, HTML
        }
    }

    public class NodeAST(
        public val id: LiteralAST,
    ) : AST, GraphSTMT

    public class EdgeAST(
        public val targets: List<EdgeDistribution>,
    ) : AST, GraphSTMT
}
