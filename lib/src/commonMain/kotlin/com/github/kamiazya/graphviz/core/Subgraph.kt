package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.EdgeModel
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.NodeModel
import com.github.kamiazya.graphviz.model.SubgraphModel

/**
 * Subgraph is a class for subgraph models.
 */
class Subgraph(
    context: ModelContext,
    override var id: String? = null,
    override var comment: String? = null,
) : BaseGraph(
    context = context,
    id = id,
    comment = comment,
),
    SubgraphModel {
    override var attributes: List<Attribute> = emptyList()
    override var nodes: List<NodeModel> = emptyList()
    override var edges: List<EdgeModel> = emptyList()
    override var subgraphs: List<SubgraphModel> = emptyList()
}
