package com.github.kamiazya.graphviz

import com.github.kamiazya.graphviz.model.Attribute
import com.github.kamiazya.graphviz.model.AttributeGroupModel
import com.github.kamiazya.graphviz.model.AttributeValueOf
import com.github.kamiazya.graphviz.model.BaseGraphModel
import com.github.kamiazya.graphviz.model.DotModel
import com.github.kamiazya.graphviz.model.EdgeAttributeGroupModel
import com.github.kamiazya.graphviz.model.EdgeDistribution
import com.github.kamiazya.graphviz.model.EdgeModel
import com.github.kamiazya.graphviz.model.GraphAttributeGroupModel
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.NodeAttributeGroupModel
import com.github.kamiazya.graphviz.model.NodeModel
import com.github.kamiazya.graphviz.model.RootGraphModel
import com.github.kamiazya.graphviz.model.SubgraphModel
import com.github.kamiazya.graphviz.model.dsl.DotScope
import com.github.kamiazya.graphviz.model.dsl.dot

/**
 * Dot is a class for dot models.
 */
public class Dot(
    override var context: ModelContext,
    override var comment: String? = null,
    override var root: RootGraphModel? = null,
) : DotModel

/**
 * BaseGraph is a base class for graph models.
 */
public abstract class BaseGraph(
    override val context: ModelContext,
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
    context: ModelContext,
    override val directed: Boolean,
    override var strict: Boolean,
    id: String?,
    comment: String? = null,
) : RootGraphModel, BaseGraph(
    context = context,
    id = id,
    comment = comment,
)

/**
 * Graph is a class for graph models.
 */
public class Graph(
    context: ModelContext,
    strict: Boolean = false,
    id: String?,
    comment: String? = null,
) : RootGraph(
    context = context,
    directed = false,
    strict = strict,
    id = id,
    comment = comment,
)

/**
 * Digraph is a class for digraph models.
 */
public class Digraph(
    context: ModelContext,
    strict: Boolean = false,
    id: String?,
    comment: String? = null,
) : RootGraph(
    context = context,
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

interface DefaultModelContext : ModelContext {
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

var DEFAULT_MODEL_CONTEXT: ModelContext = object : DefaultModelContext {}

fun dot(
    comment: String? = null,
    block: DotScope.() -> Unit
) = DEFAULT_MODEL_CONTEXT.dot(
    comment = comment,
    block = block,
)
