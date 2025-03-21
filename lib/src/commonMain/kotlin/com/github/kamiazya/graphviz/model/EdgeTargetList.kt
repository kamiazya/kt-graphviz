package com.github.kamiazya.graphviz.model

import com.github.kamiazya.graphviz.ast.dsl.EdgeDistributionBuilder

/**
 * EdgeTargetList is a concrete implementation representing a mutable list of edge targets in a graph model.
 *
 * The class serves as a data structure for managing a collection of `NodeRef` instances,
 * adhering to the `EdgeTargetCluster` interface to enable integration into graph structures.
 * By leveraging delegation to a `MutableList<NodeRef>`, it inherits the full functionality
 * of a mutable list.
 *
 * Key Features:
 * - Implements `EdgeTargetCluster` to provide edge distribution functionalities for graph models.
 * - Delegates operations to a mutable list of `NodeRef` instances.
 * - Supports construction with a vararg list of `NodeRef` or an existing mutable list of `NodeRef`.
 *
 * Primary Functionality:
 * - Integration with Abstract Syntax Tree (AST) generation through the `toAST` method.
 * - Provides the ability to load node references into edge distributions for graph representation.
 *
 * @constructor Initializes the EdgeTargetList with a specified list of targets or variadic arguments.
 * @param targets A mutable list of `NodeRef` instances representing edge targets.
 */
class EdgeTargetList(
    private val targets: MutableList<NodeRef>
) : EdgeTargetCluster, MutableList<NodeRef> by targets {
    constructor(vararg targets: NodeRef) : this(targets.toMutableList())

    override fun toAST() = EdgeDistributionBuilder {
        for (target in targets) {
            load(target.toAST())
        }
    }.stmts
}
