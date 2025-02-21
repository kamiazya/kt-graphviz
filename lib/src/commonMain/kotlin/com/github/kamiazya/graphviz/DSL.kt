package com.github.kamiazya.graphviz

@DslMarker
annotation class DotDslMarker

@DotDslMarker
public interface CreateNodeDsl : HasContext {
    /**
     * Create a node.
     *
     * @param id An ID of the node.
     * @param block A block to create a node.
     */
    fun node(id: String, block: NodeModel.() -> Unit): NodeModel = context.createNode(id).apply(block)
}

@DotDslMarker
public interface GetNodeDsl : HasNodes {
    /**
     * Get a node by ID.
     */
    fun node(id: String): NodeModel? = getNode(id)
}

@DotDslMarker
public interface ApplyNodeAttibuteGroupDsl : HasNodeAttributeGroupModel {
    /**
     * Apply a block to node attributes.
     */
    fun node(block: NodeAttributeGroupModel.() -> Unit) = nodeAttributes.block()
}

@DotDslMarker
public interface NodeDsl : HasContext, HasNodes, CreateNodeDsl, GetNodeDsl, ApplyNodeAttibuteGroupDsl {
    /**
     * Create or get a node by ID and apply a block.
     *
     * If a node with the ID does not exist, create a node with the ID and apply a block.
     * If a node with the ID exists, apply a block to the node.
     *
     * ## Example 1: Create a node with an ID
     * ```kotlin
     * node("a") {
     *    color = "red"
     * }
     * ```
     *
     * ## Example 2: Get a node by an ID
     *
     * ```kotlin
     * node("a") { // Create a node with an ID "a"
     *   color = "blue" // Set a color of the node
     * }
     *
     * node("a") { // Get a node with an ID "a"
     *  shape = "circle" // Set a shape of the node
     * }
     * // => The node with the ID "a" has a color "blue" and a shape "circle".
     * ```
     *
     * @param id An ID of the node.
     * @param block A block to apply.
     * @return A node.
     */
    override fun node(id: String, block: NodeModel.() -> Unit): NodeModel {
        var node = node(id)
        if (node == null) {
            node = context.createNode(id).apply(block)
            addNode(node)
        } else {
            node.block()
        }
        return node
    }
}

@DotDslMarker
public interface CreateEdgeDsl : HasContext {
    /**
     * Create an edge.
     * @param distributions A list of edge distributions.
     * @param block A block to create an edge.
     * @return An edge.
     */
    fun edge(vararg distributions: EdgeDistribution, block: EdgeModel.() -> Unit = {}): EdgeModel {
        return context.createEdge(distributions.toList()).apply(block)
    }

    // operator fun String.minus(other: EdgeDistribution): EdgeModel = this.apply {
    //     return edge(ForwardRefNode(this), other)
    // }

    operator fun EdgeModel.invoke(block: EdgeModel.() -> Unit): EdgeModel = apply(block)

    operator fun EdgeModel.minus(other: EdgeDistribution): EdgeModel = apply {
        addDistribution(other)
    }

    operator fun EdgeModel.minus(other: String): EdgeModel = apply {
        addDistribution(ForwardRefNode(other))
    }

    operator fun String.minus(other: EdgeDistribution): EdgeModel {
        return edge(ForwardRefNode(this), other)
    }

    operator fun EdgeDistribution.minus(other: EdgeDistribution): EdgeModel {
        return edge(this, other)
    }

    // class EdgeDistributionChain(
    //     private val context: ModelContext,
    //     private val targets: MutableList<EdgeDistribution>
    // ) {

    //     operator fun minus(other: EdgeDistribution): EdgeDistributionChain {
    //         targets.add(other)
    //         return this
    //     }

    //     operator fun minus(other: String): EdgeDistributionChain {
    //         return this.minus(ForwardRefNode(other))
    //     }

    //     operator fun invoke(block: EdgeModel.() -> Unit): EdgeModel {
    //         return context.createEdge(targets.toList()).apply(block)
    //     }
    // }

    // operator fun get(id: String): EdgeDistribution = ForwardRefNode(id)

    // public class EdgeDistributionCollectionChain {

    //     operator fun times(other: EdgeDistribution): EdgeDistributionCollectionChain {
    //         add(other)
    //         return this
    //     }

    //     operator fun times(other: String): EdgeDistributionCollectionChain {
    //         return this.times(ForwardRefNode(other))
    //     }

    // }

    // operator fun String.minus(other: EdgeDistribution): EdgeDistributionChain =
    //     EdgeDistributionChain(context, mutableListOf(ForwardRefNode(this), other))

    // operator fun String.minus(other: String): EdgeDistributionChain =
    //     EdgeDistributionChain(context, mutableListOf(ForwardRefNode(this), ForwardRefNode(other)))

    // operator fun EdgeDistribution.minus(other: EdgeDistribution): EdgeDistributionChain =
    //     EdgeDistributionChain(context, mutableListOf(this, other))

    // operator fun String.times(other: EdgeDistribution): EdgeDistributionChain =
    //     EdgeDistributionCollectionChain(mutableListOf(ForwardRefNode(this), other))

    // operator fun String.times(other: String): EdgeDistributionCollectionChain =
    //     EdgeDistributionCollectionChain(mutableListOf(ForwardRefNode(this), ForwardRefNode(other)))

    // operator fun EdgeDistribution.times(other: EdgeDistribution): EdgeDistributionCollectionChain =
    //     EdgeDistributionCollectionChain(mutableListOf(this, other))
    // operator fun EdgeDistribution.minus(other: EdgeDistribution, block: EdgeModel.() -> Unit): EdgeModel =
    //     EdgeChain(context, mutableListOf(this, other)).apply(block)
}

@DotDslMarker
public interface ApplyEdgeAttibuteGroupDsl : HasEdgeAttributeGroupModel {
    /**
     * Apply a block to edge attributes.
     */
    fun edge(block: EdgeAttributeGroupModel.() -> Unit) {
        edgeAttributes.block()
    }
}

@DotDslMarker
public interface RefDsl {
    fun ref(id: String, port: String? = null, compass: Compass? = null): ForwardRefNode = ForwardRefNode(
        id,
        port,
        compass
    )
}

@DotDslMarker
public interface RefsDsl {
    fun refs(vararg refs: NodeRef): EdgeDistribution = refs.toList() as ClusterEdgeTarget
}

// public interface EdgeDsl : HasEdges, CreateEdgeDsl {
//     /**
//      * Create an edge.
//      * @param distributions A list of edge distributions.
//      * @param block A block to create an edge.
//      * @return An edge.
//      */
//     override fun edge(vararg distributions: EdgeDistribution, block: EdgeModel.() -> Unit): EdgeModel {
//         var edge = super<CreateEdgeDsl>.edge(*distributions, block = block)
//         addEdge(edge)
//         return edge
//     }
// }

interface GroupGroupAttributeDsl : HasGraphAttributes {

    /**
     * A group of graph attributes.
     */
    fun graph(block: GraphAttributeGroupModel.() -> Unit) {
        graphAttributes.block()
    }
}

@DotDslMarker
public interface RootGraphDsl : HasContext {

    /**
     * @example
     * ```
     * graph {
     *    node {
     *       id = "a"
     *   }
     * }
     * ```
     */
    fun graph(
        id: String? = null,
        strict: Boolean? = null,
        block: RootGraphModel.() -> Unit
    ): RootGraphModel {
        return context.createGraph(strict = false, id).apply(block)
    }

    /**
     * @example
     * ```
     * digraph {
     *    node {
     *       id = "a"
     *   }
     * }
     * ```
     */
    fun digraph(
        id: String? = null,
        strict: Boolean? = null,
        block: RootGraphModel.() -> Unit
    ): RootGraphModel = context.createDigraph(strict = false, id).apply(block)

    /**
     * @example
     * ```
     * strict digraph {
     *    node {
     *       id = "a"
     *   }
     * }
     * ```
     */
    val strict: StrictGraphBuilder
        get() = StrictGraphBuilder(this)

    public class StrictGraphBuilder(private val dsl: RootGraphDsl) {

        // infix fun digraph(block: RootGraphModel.() -> Unit): RootGraphModel {
        //     return dsl.digraph(strict = true, block = block)
        // }

        // infix fun digraph(id: String): DigraphCreator {
        //     return DigraphCreator(id=id, strict = true, dsl)
        // }

        // infix fun graph(block: RootGraphModel.() -> Unit): RootGraphModel {
        //     return dsl.graph(strict = true, block = block)
        // }

        // infix fun graph(id: String): GraphCreator {
        //     return GraphCreator(id, strict=true, dsl)
        // }

        public class DigraphCreator(
            private val dsl: RootGraphDsl,
            private val id: String? = null,
            private val strict: Boolean? = null,
        ) {

            // infix fun static

            operator fun invoke(block: RootGraphModel.() -> Unit): RootGraphModel {
                return dsl.digraph(id = id, strict = strict, block)
            }
        }

        public class GraphCreator(
            private val dsl: RootGraphDsl,
            private val id: String? = null,
            private val strict: Boolean? = null,
        ) {
            operator fun invoke(block: RootGraphModel.() -> Unit): RootGraphModel {
                return dsl.graph(id = id, strict = strict, block)
            }
        }
    }
}

fun dot(
    comment: String? = null,
    block: DotModel.() -> Unit
): DotModel = ModelContext.default.createDot(comment).apply(block)
