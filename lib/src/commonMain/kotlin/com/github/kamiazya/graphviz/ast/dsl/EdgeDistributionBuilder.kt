package com.github.kamiazya.graphviz.ast.dsl

import com.github.kamiazya.graphviz.ast.ClusterNodeRefs
import com.github.kamiazya.graphviz.ast.EdgeDistribution
import com.github.kamiazya.graphviz.ast.Literal
import com.github.kamiazya.graphviz.ast.NodeRef

/**
 * A builder class for constructing edge distributions within a DOT graph's Abstract Syntax Tree (AST).
 *
 * `EdgeDistributionBuilder` is used to define target nodes or clusters for edges in a graph. It provides
 * methods to create node references and group them into clusters, facilitating customized edge target definitions.
 * This class extends `STMTBuilder` to manage a list of statements and implements `LiteralBuilder`
 * for creating literals.
 *
 * The `@ASTDslMarker` annotation is used to limit the DSL scope when building edge distribution structures.
 *
 * @param body A lambda function executed during initialization to define the builder's configurations.
 */
@ASTDslMarker
class EdgeDistributionBuilder(
    body: EdgeDistributionBuilder.() -> Unit
) : STMTBuilder<EdgeDistribution>(), LiteralBuilder {
    init {
        body()
    }

    /**
     * Creates a reference to a graph node, optionally specifying its port and compass point.
     *
     * @param id The identifier of the node being referenced. Represents the unique ID of the node.
     * @param port Optional port information for the node. This specifies subcomponents or subdivisions within the node.
     * @param compass Optional compass point for the node, indicating the relative direction (e.g., north, south, etc.)
     *                or positioning of the node.
     */
    fun nodeRefOf(
        id: Literal,
        port: Literal? = null,
        compass: Literal? = null,
    ) = NodeRef(
        id = id,
        port = port,
        compass = compass,
    )

    /**
     * Adds a reference to a graph node in the current builder's statement list. This reference can optionally
     * include specification of a port and a compass point for more precise targeting.
     *
     * @param id The identifier of the node being referenced. Represents the unique ID of the node.
     * @param port Optional port information for the node. This specifies subcomponents or subdivisions within the node.
     * @param compass Optional compass point for the node, indicating the relative direction (e.g., north, south, etc.)
     *                or positioning of the node.
     */
    fun nodeRef(
        id: Literal,
        port: Literal? = null,
        compass: Literal? = null,
    ) {
        stmts += nodeRefOf(id = id, port = port, compass = compass)
    }

    /**
     * Adds a collection of node references associated with a cluster to the current
     * builder's statement list. These references are grouped together as a `ClusterNodeRefs`
     * object, representing clusters in a graph structure.
     *
     * @param refs A variable number of `NodeRef` instances representing the nodes to be
     * included in the cluster.
     */
    fun clusterNodeRefs(
        vararg refs: NodeRef,
    ) {
        stmts += ClusterNodeRefs(refs.toList())
    }
}
