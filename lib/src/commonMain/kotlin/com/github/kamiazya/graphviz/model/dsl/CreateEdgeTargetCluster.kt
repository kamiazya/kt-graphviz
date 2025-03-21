package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.EdgeTargetCluster
import com.github.kamiazya.graphviz.model.EdgeTargetList
import com.github.kamiazya.graphviz.model.ForwardRefNode
import com.github.kamiazya.graphviz.model.NodeRef

/**
 * Interface for creating a cluster of node references as part of an edge target.
 * Provides infix functions and utilities for defining connections between nodes in a DOT graph structure.
 */
@DotDslMarker
public interface CreateEdgeTargetCluster {
    /**
     * Creates a cluster of node references.
     *
     * @param refs A variable number of NodeRef objects to include in the cluster.
     * @return An EdgeTargetCluster representing the cluster of provided node references.
     */
    fun clusterOf(vararg refs: NodeRef): EdgeTargetCluster = EdgeTargetList(*refs)

    /**
     * Combines a string node reference and a NodeRef into a single edge target cluster.
     *
     * @param other The NodeRef to combine with the string node reference.
     * @return An EdgeTargetCluster containing both the string node reference and the specified NodeRef.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c".
     *
     * ```kotlin
     * ("a" - ("b" and "c"))
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a -> { b, c };
     * ```
     */
    infix fun String.and(other: NodeRef): EdgeTargetCluster = EdgeTargetList(ForwardRefNode.from(this), other)

    /**
     * Combines a NodeRef and a string node reference into a single edge target cluster.
     *
     * @param other The string node reference to combine with the NodeRef.
     * @return An EdgeTargetCluster containing both the NodeRef and the specified string node reference.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c".
     *
     * ```kotlin
     * val b = ref("b")
     *
     * ("a" - (b and "c"))
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a -> { b, c };
     * ```
     */
    infix fun NodeRef.and(other: String): EdgeTargetCluster = EdgeTargetList(this, ForwardRefNode.from(other))

    /**
     * Combines two NodeRef objects into a single edge target cluster.
     *
     * @param other The NodeRef object to combine with the current NodeRef.
     * @return An EdgeTargetCluster containing both NodeRef objects.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c".
     *
     * ```kotlin
     * val b = node("b")
     * val c = node("c")
     *
     * ("a" - (b and c))
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * b;
     * c;
     *
     * a -> { b, c };
     * ```
     */
    infix fun NodeRef.and(other: NodeRef): EdgeTargetCluster = EdgeTargetList(this, other)

    /**
     * Combines an EdgeTargetCluster and a NodeRef into a single edge target cluster.
     *
     * @param other The NodeRef to combine with the EdgeTargetCluster.
     * @return An EdgeTargetCluster containing the elements of the current EdgeTargetCluster and the specified NodeRef.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c" and "d".
     *
     * ```kotlin
     * val b = node("b")
     * val c = node("c")
     * val d = node("d")
     *
     * ("a" - (b and c and d))
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * b;
     * c;
     *
     * a -> { b, c, d };
     * ```
     */
    infix fun EdgeTargetCluster.and(other: NodeRef): EdgeTargetCluster = this and other

    /**
     * Combines an EdgeTargetCluster and a string node reference into a single edge target cluster.
     *
     * @param other The string node reference to combine with the EdgeTargetCluster.
     * @return An EdgeTargetCluster containing the elements of the current EdgeTargetCluster and the specified string
     *         node reference.
     *
     * ## Example
     *
     * Connect a node "a" to nodes "b" and "c" and "d".
     *
     * ```kotlin
     * val b = node("b")
     * val c = node("c")
     *
     * ("a" - (b and c and "d"))
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * b;
     * c;
     *
     * a -> { b, c, d };
     * ```
     */
    infix fun EdgeTargetCluster.and(other: String): EdgeTargetCluster = this and ForwardRefNode.from(other)
}
