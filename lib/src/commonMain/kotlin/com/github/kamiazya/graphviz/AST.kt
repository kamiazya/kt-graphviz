package com.github.kamiazya.graphviz

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.AttributeGroupModel
import com.github.kamiazya.graphviz.model.DotModel
import com.github.kamiazya.graphviz.model.EdgeAttributeGroupModel
import com.github.kamiazya.graphviz.model.EdgeDistribution
import com.github.kamiazya.graphviz.model.EdgeModel
import com.github.kamiazya.graphviz.model.EdgeTargetCluster
import com.github.kamiazya.graphviz.model.ForwardRefNode
import com.github.kamiazya.graphviz.model.GraphAttributeGroupModel
import com.github.kamiazya.graphviz.model.NodeAttributeGroupModel
import com.github.kamiazya.graphviz.model.NodeModel
import com.github.kamiazya.graphviz.model.NodeRef
import com.github.kamiazya.graphviz.model.RootGraphModel
import com.github.kamiazya.graphviz.model.SubgraphModel

public sealed interface AST {

    @DslMarker
    annotation class DotASTDslMarker

    public sealed interface AttributesSTMT : AST
    public sealed interface DotSTMT : AST
    public sealed interface GraphSTMT : AST

    @DotASTDslMarker
    class STMTBuiler<STMT : AST>(
        val init: MutableList<STMT>.() -> Unit,
        private val children: MutableList<STMT> = mutableListOf(),
    ) : List<STMT> by children {
        init {
            children.init()
        }
    }

    public abstract class Parents<STMT : AST>(
        init: MutableList<STMT>.() -> Unit,
    ) {
        val children: List<STMT> = STMTBuiler(init)
    }

    @DotASTDslMarker
    public data class DotAST(
        public val comment: CommentAST? = null,
        val init: MutableList<DotSTMT>.() -> Unit = {},
    ) : AST, Parents<DotSTMT>(init)

    @DotASTDslMarker
    public data class CommentAST(
        public var value: String = "",
        public var kind: Kind,
    ) : AST, AttributesSTMT, DotSTMT, GraphSTMT {
        enum class Kind {
            BLOCK, SLASH, MACRO
        }
    }

    @DotASTDslMarker
    public data class AttributeGroupAST(
        public val kind: AttributeGroupAST.Kind,
        val init: MutableList<AttributeAST>.() -> Unit = {},
    ) : AST, GraphSTMT, Parents<AttributeAST>(init) {
        enum class Kind {
            GRAPH, NODE, EDGE
        }
    }

    @DotASTDslMarker
    public data class RootGraphAST(
        public var id: LiteralAST? = null,
        public var directed: Boolean,
        public var strict: Boolean,
        val init: MutableList<GraphSTMT>.() -> Unit = {},
    ) : AST, DotSTMT, Parents<GraphSTMT>(init)

    @DotASTDslMarker
    public data class SubgraphAST(
        public var id: LiteralAST? = null,
        val init: MutableList<GraphSTMT>.() -> Unit = {},
    ) : AST, GraphSTMT, Parents<GraphSTMT>(init)

    public data class AttributeAST(
        public var key: LiteralAST,
        public var value: LiteralAST,
    ) : AST, AttributesSTMT, GraphSTMT

    public data class LiteralAST(
        public var value: String,
        public var quated: Quate = Quate.QUATED,
    ) : AST {
        enum class Quate {
            QUATED, UNQUATED, HTML
        }
    }

    public data class NodeAST(
        public var id: LiteralAST,
        var init: MutableList<AttributesSTMT>.() -> Unit = {},
    ) : AST, GraphSTMT, Parents<AttributesSTMT>(init)

    public interface EdgeDistributionAST : AST

    public data class NodeRefAST(
        public var id: LiteralAST,
        public var port: LiteralAST? = null,
        public var compass: LiteralAST? = null,
    ) : AST, EdgeDistributionAST

    public data class ClusterNodeRefsAST(
        public var refs: List<NodeRefAST>,
    ) : AST, EdgeDistributionAST

    public data class EdgeAST(
        public var targets: List<EdgeDistributionAST>,
        var init: MutableList<AttributesSTMT>.() -> Unit = {},
    ) : AST, GraphSTMT, Parents<AttributesSTMT>(init)

    @Suppress("TooManyFunctions")
    open class ModelToAST {

        fun from(model: DotModel): DotAST = DotAST(
            comment = model.comment?.let {
                CommentAST(value = it, kind = CommentAST.Kind.BLOCK)
            }
        ) {
            model.root?.let {
                addAll(from(it))
            }
        }

        fun from(model: RootGraphModel): Sequence<DotSTMT> = sequence {
            model.comment?.let {
                yield(CommentAST(value = it, kind = CommentAST.Kind.BLOCK))
            }
            yield(
                RootGraphAST(
                    id = model.id?.let { LiteralAST(it) },
                    directed = model.directed,
                    strict = model.strict,
                ) {
                    addAll(
                        sequence {
                            yieldAll(model.attributes.map { from(it) })
                            yieldAll(from(model.nodeAttributes))
                            yieldAll(model.nodes.flatMap { from(it) })
                            yieldAll(from(model.edgeAttributes))
                            yieldAll(model.edges.flatMap { from(it) })
                            yieldAll(from(model.graphAttributes))
                            yieldAll(model.subgraphs.flatMap { from(it) })
                        }
                    )
                }
            )
        }

        fun from(model: AttributeGroupModel): Sequence<GraphSTMT> = sequence {
            yield(
                AttributeGroupAST(
                    kind = when (model) {
                        is GraphAttributeGroupModel -> AttributeGroupAST.Kind.GRAPH
                        is NodeAttributeGroupModel -> AttributeGroupAST.Kind.NODE
                        is EdgeAttributeGroupModel -> AttributeGroupAST.Kind.EDGE
                        else -> throw IllegalArgumentException("Unsupported model: $model")
                    }
                ) {
                    addAll(model.attributes.map { from(it) })
                }
            )
        }

        fun from(model: SubgraphModel): Sequence<GraphSTMT> = sequence {
            model.comment?.let {
                yield(
                    CommentAST(value = it, kind = CommentAST.Kind.BLOCK)
                )
            }
            yield(
                SubgraphAST(
                    id = model.id?.let { LiteralAST(it) },
                ) {
                    addAll(
                        sequence {
                            yieldAll(model.attributes.map { from(it) })
                            yieldAll(from(model.nodeAttributes))
                            yieldAll(model.nodes.flatMap { from(it) })
                            yieldAll(from(model.edgeAttributes))
                            yieldAll(model.edges.flatMap { from(it) })
                            yieldAll(from(model.graphAttributes))
                            yieldAll(model.subgraphs.flatMap { from(it) })
                        }
                    )
                }
            )
        }

        fun from(model: NodeModel): Sequence<GraphSTMT> = sequence {
            model.comment?.let {
                yield(
                    CommentAST(value = it, kind = CommentAST.Kind.BLOCK)
                )
            }
            yield(
                NodeAST(
                    id = LiteralAST(model.id),
                ) {
                    addAll(
                        sequence {
                            yieldAll(model.attributes.map { from(it) })
                        }
                    )
                }
            )
        }

        fun from(model: EdgeModel): Sequence<GraphSTMT> = sequence {
            model.comment?.let {
                yield(
                    CommentAST(value = it, kind = CommentAST.Kind.BLOCK)
                )
            }
            yield(
                EdgeAST(
                    targets = model.targets.map { from(it) },
                ) {
                    addAll(
                        sequence {
                            yieldAll(model.attributes.map { from(it) })
                        }
                    )
                }
            )
        }

        fun from(model: Attribute) = AttributeAST(
            key = LiteralAST(model.key),
            value = LiteralAST(model.value.toString()),
        )

        fun from(model: EdgeDistribution): EdgeDistributionAST = when (model) {
            is EdgeTargetCluster -> from(model)
            is NodeRef -> from(model)
        }

        fun from(model: NodeRef): NodeRefAST = when (model) {
            is ForwardRefNode -> from(model)
            is NodeModel -> NodeRefAST(
                id = LiteralAST(model.id),
            )
        }

        fun from(model: ForwardRefNode): NodeRefAST = NodeRefAST(
            id = LiteralAST(model.id),
            port = model.port?.let { LiteralAST(it) },
            compass = model.compass?.let { LiteralAST(it.toString()) },
        )

        fun from(model: EdgeTargetCluster): ClusterNodeRefsAST = ClusterNodeRefsAST(
            refs = model.map { from(it) }
        )
    }

    companion object {
        fun from(dot: DotModel): DotAST =
            ModelToAST().from(dot)
    }
}
