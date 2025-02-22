package com.github.kamiazya.graphviz

/**
 * Dot is a class for dot models.
 */
public class Dot(
    override var comment: String? = null,
) : DotModel {
    override var graph: RootGraphModel? = null
    override val context: ModelContext = ModelContext.default
}

/**
 * BaseGraph is a base class for graph models.
 */
public abstract class BaseGraph(
    override val context: ModelContext = ModelContext.default,
    override var id: String?,
    override var comment: String? = null,
) : GraphAttributeGroup(), BaseGraphModel {
    override var nodes: List<NodeModel> = emptyList()
    override var edges: List<EdgeModel> = emptyList()
    override var subgraphs: List<SubgraphModel> = emptyList()
    override val graphAttributes: GraphAttributeGroupModel = GraphAttributeGroup()
    override val nodeAttributes: NodeAttributeGroupModel = NodeAttributeGroup()
    override val edgeAttributes: EdgeAttributeGroupModel = EdgeAttributeGroup()
}

public abstract class AttributeGroup : AttributeGroupModel {
    override var attributes: List<Attribute> = emptyList()
}

public open class NodeAttributeGroup : AttributeGroup(), NodeAttributeGroupModel {
    override var shape: String? by AttributeValueOf()
    override var color: String? by AttributeValueOf()
}

public open class EdgeAttributeGroup : AttributeGroup(), EdgeAttributeGroupModel {
    override var color: String? by AttributeValueOf()
}

public open class GraphAttributeGroup : AttributeGroup(), GraphAttributeGroupModel {
    override var color: String? by AttributeValueOf()
}

/**
 * RootGraph is a class for root graph models.
 */
public abstract class RootGraph(
    override val directed: Boolean,
    override var strict: Boolean,
    id: String?,
    comment: String? = null,
) : RootGraphModel, BaseGraph(
    id = id,
    comment = comment,
)

/**
 * Graph is a class for graph models.
 */
public class Graph(
    strict: Boolean = false,
    id: String?,
    comment: String? = null,
) : RootGraph(
    directed = false,
    strict = strict,
    id = id,
    comment = comment,
)

/**
 * Digraph is a class for digraph models.
 */
public class Digraph(
    strict: Boolean = false,
    id: String?,
    comment: String? = null,
) : RootGraph(
    directed = true,
    strict = strict,
    id = id,
    comment = comment,
)

/**
 * Node is a class for node models.
 */
class Node(
    override var id: String,
    override var comment: String? = null,
) : NodeAttributeGroup(), NodeModel

/**
 * Edge is a class for edge models.
 *
 * @param targets List of [EdgeDistribution] objects.
 * @param comment Comment string.
 *
 * @throws IllegalArgumentException If the number of targets is less than 2.
 */
class Edge(
    override var targets: List<EdgeDistribution>,
    override var comment: String? = null,
) : EdgeAttributeGroup(), EdgeModel {
    constructor(vararg targets: EdgeDistribution, comment: String? = null) : this(targets.toList(), comment)

    init {
        require(targets.size >= 2) { "Edge must have at least two targets." }
    }
}

/**
 * Subgraph is a class for subgraph models.
 */
class Subgraph(
    override var id: String? = null,
    override var comment: String? = null,
) : BaseGraph(
    id = id,
    comment = comment,
),
    SubgraphModel {
    override var attributes: List<Attribute> = emptyList()
    override var nodes: List<NodeModel> = emptyList()
    override var edges: List<EdgeModel> = emptyList()
    override var subgraphs: List<SubgraphModel> = emptyList()
}
