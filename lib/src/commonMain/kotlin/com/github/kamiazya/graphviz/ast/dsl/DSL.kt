package com.github.kamiazya.graphviz.ast.dsl

import com.github.kamiazya.graphviz.ast.AST
import com.github.kamiazya.graphviz.ast.Attribute
import com.github.kamiazya.graphviz.ast.AttributeGroup
import com.github.kamiazya.graphviz.ast.AttributesSTMT
import com.github.kamiazya.graphviz.ast.ClusterNodeRefs
import com.github.kamiazya.graphviz.ast.Comment
import com.github.kamiazya.graphviz.ast.Dot
import com.github.kamiazya.graphviz.ast.DotSTMT
import com.github.kamiazya.graphviz.ast.Edge
import com.github.kamiazya.graphviz.ast.EdgeDistribution
import com.github.kamiazya.graphviz.ast.GraphSTMT
import com.github.kamiazya.graphviz.ast.Literal
import com.github.kamiazya.graphviz.ast.Node
import com.github.kamiazya.graphviz.ast.NodeRef
import com.github.kamiazya.graphviz.ast.RootGraph
import com.github.kamiazya.graphviz.ast.Subgraph

@DslMarker
annotation class ASTDslMarker

open class STMTBuilder<T : AST> {
    var stmts: MutableList<T> = mutableListOf()
}

interface LiteralBuilder {
    fun literalOf(
        value: String,
        quated: Literal.Quate = Literal.Quate.QUATED,
    ) = Literal(
        value = value,
        quated = quated,
    )

    fun String.quated(): Literal = literalOf(this, quated = Literal.Quate.QUATED)
    fun String.unquated(): Literal = literalOf(this, quated = Literal.Quate.UNQUATED)
    fun String.html(): Literal = literalOf(this, quated = Literal.Quate.HTML)
}

@ASTDslMarker
class AttributeSTMTBuilder(body: AttributeSTMTBuilder.() -> Unit) : STMTBuilder<AttributesSTMT>(), LiteralBuilder {
    init {
        body()
    }

    fun comment(value: String, kind: Comment.Kind = Comment.Kind.SLASH) {
        stmts += Comment(
            value = value,
            kind = kind
        )
    }

    fun attribute(
        key: Literal,
        value: Literal,
    ) {
        stmts += Attribute(
            key = key,
            value = value,
        )
    }
}

@ASTDslMarker
class EdgeDistributionBuilder(
    body: EdgeDistributionBuilder.() -> Unit
) : STMTBuilder<EdgeDistribution>(), LiteralBuilder {
    init {
        body()
    }

    fun nodeRefOf(
        id: Literal,
        port: Literal? = null,
        compass: Literal? = null,
    ) = NodeRef(
        id = id,
        port = port,
        compass = compass,
    )

    fun nodeRef(
        id: Literal,
        port: Literal? = null,
        compass: Literal? = null,
    ) {
        stmts += nodeRefOf(id = id, port = port, compass = compass)
    }

    fun clusterNodeRefs(
        vararg refs: NodeRef,
    ) {
        stmts += ClusterNodeRefs(refs.toList())
    }
}

@ASTDslMarker
class GraphSTMTBuilder(body: GraphSTMTBuilder.() -> Unit) : STMTBuilder<GraphSTMT>(), LiteralBuilder {
    init {
        body()
    }

    fun comment(value: String, kind: Comment.Kind = Comment.Kind.SLASH) {
        stmts += Comment(
            value = value,
            kind = kind
        )
    }

    fun node(
        id: Literal,
        body: AttributeSTMTBuilder.() -> Unit,
    ) {
        stmts += Node(
            id = id,
            children = AttributeSTMTBuilder(body).stmts
        )
    }

    fun subgraph(
        id: Literal?,
        init: GraphSTMTBuilder.() -> Unit,
    ) {
        stmts += Subgraph(
            id = id,
            children = GraphSTMTBuilder(init).stmts,
        )
    }

    fun attributeGroup(
        kind: AttributeGroup.Kind,
        body: AttributeSTMTBuilder.() -> Unit,
    ) {
        stmts += AttributeGroup(
            kind = kind,
            children = AttributeSTMTBuilder(body).stmts
        )
    }

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

@ASTDslMarker
class DotSTMTBuilder(body: DotSTMTBuilder.() -> Unit) : STMTBuilder<DotSTMT>(), LiteralBuilder {
    init {
        body()
    }

    fun comment(value: String, kind: Comment.Kind = Comment.Kind.SLASH) {
        stmts += Comment(
            value = value,
            kind = kind
        )
    }

    fun rootGraph(
        id: Literal?,
        directed: Boolean,
        strict: Boolean,
        init: GraphSTMTBuilder.() -> Unit,
    ) {
        stmts += RootGraph(
            id = id,
            directed = directed,
            strict = strict,
            children = GraphSTMTBuilder(init).stmts
        )
    }
}

fun dot(
    init: DotSTMTBuilder.() -> Unit,
): Dot = Dot(
    DotSTMTBuilder(init).stmts
)
