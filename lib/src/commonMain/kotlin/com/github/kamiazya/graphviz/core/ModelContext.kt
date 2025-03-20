package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.EdgeDistribution
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.RootGraphModel

val MODEL_CONTEXT = object : ModelContext {
    override fun createDot(
        comment: String?,
        context: ModelContext,
        root: RootGraphModel?,
    ) = Dot(
        context = context,
        comment = comment,
        root = root,
    )

    override fun createDigraph(
        strict: Boolean,
        id: String?,
        comment: String?,
    ) = Digraph(context = this, strict = strict, id = id, comment = comment)

    override fun createGraph(
        strict: Boolean,
        id: String?,
        comment: String?,
    ) = Graph(context = this, strict = strict, id = id, comment = comment)

    override fun createSubgraph(id: String?, comment: String?) = Subgraph(
        context = this,
        id = id,
        comment = comment
    )

    override fun createNode(id: String, comment: String?) = Node(id = id, comment = comment)

    override fun createEdge(targets: List<EdgeDistribution>, comment: String?) = Edge(
        targets = targets,
        comment = comment
    )
}
