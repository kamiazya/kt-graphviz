package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.BaseGraphModel
import com.github.kamiazya.graphviz.model.EdgeAttributeGroupModel
import com.github.kamiazya.graphviz.model.EdgeModel
import com.github.kamiazya.graphviz.model.GraphAttributeGroupModel
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.NodeAttributeGroupModel
import com.github.kamiazya.graphviz.model.NodeModel
import com.github.kamiazya.graphviz.model.SubgraphModel

/**
 * BaseGraph is a base class for graph models.
 */
public abstract class BaseGraph(
    override val context: ModelContext,
    override var id: String?,
    override var comment: String? = null,
) : BaseGraphModel, GraphAttributeGroup() {
    override var nodes: List<NodeModel> = emptyList()
    override var edges: List<EdgeModel> = emptyList()
    override var subgraphs: List<SubgraphModel> = emptyList()
    override val graphAttributes: GraphAttributeGroupModel = object : GraphAttributeGroupModel, GraphAttributeGroup() {}
    override val nodeAttributes: NodeAttributeGroupModel = object : NodeAttributeGroupModel, NodeAttributeGroup() {}
    override val edgeAttributes: EdgeAttributeGroupModel = object : EdgeAttributeGroupModel, EdgeAttributeGroup() {}
}
