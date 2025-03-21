package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.EdgeDistribution
import com.github.kamiazya.graphviz.model.EdgeModel
import com.github.kamiazya.graphviz.model.ForwardRefNode
import com.github.kamiazya.graphviz.model.HasContext

/**
 * Interface for constructing and managing edges within a DOT DSL context.
 *
 * This interface provides methods for creating edges between nodes and manipulating
 * their attributes, comments, and distributions. It supports a DSL syntax for
 * intuitive edge creation and customization.
 */
@DotDslMarker
public interface CreateEdge : HasContext {
    /**
     * Creates an edge using the given edge distributions and applies the provided configuration block.
     *
     * @param first The first edge distribution defining the edge's starting point.
     * @param second The second edge distribution defining the edge's ending point.
     * @param others Additional edge distributions that further define the edge.
     * @param comment An optional comment for the edge.
     * @param block A lambda expression applied to configure the edge within the provided scope.
     * @return The constructed EdgeModel representing the edge.
     *
     * ## Example
     *
     * If you want to create an edge from a node "a" to a node "b" and set a color of the edge to "red",
     * you can use the following code.
     *
     * ```kotlin
     * edge("a", "b") {
     *   color = "red"
     * }
     * ```
     *
     * And you can also create an edge from a node "a" to a node "b" and a node "c"
     * and set a color of the edge to "red".
     *
     * ```kotlin
     * edge("a", "b", "c") {
     *   color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a -> b [
     *   color="red";
     * ];
     *
     * a -> b -> c [
     *   color="red";
     * ];
     *
     *
     * If you already have a created node, you can use the following code.
     *
     * ```kotlin
     * val a = node("a")
     * val b = node("b")
     *
     * edge(a, b) {
     *  color = "red"
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a -> b [
     *  color="red";
     * ];
     * ```
     */
    fun edge(
        first: EdgeDistribution,
        second: EdgeDistribution,
        vararg others: EdgeDistribution,
        comment: String? = null,
        block: EdgeScope.() -> Unit = {}
    ): EdgeModel = EdgeScope(context.createEdge(listOf(first, second, *others), comment = comment)).apply(block)

    /**
     * Invokes a block of code within the context of an `EdgeScope` to configure the `EdgeModel`.
     *
     * @param block A lambda expression providing configurations for the `EdgeModel` within the `EdgeScope`.
     * @return The configured `EdgeModel`.
     *
     * ## Example
     *
     * If you want to set a color of an edge in graph, you can use the following code.
     *
     * ```kotlin
     * graph {
     *   ("a" - "b") {
     *    color = "red"
     *  }
     * }
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * graph {
     *   a -- b [
     *     color="red";
     *   ];
     * };
     * ```
     */
    operator fun EdgeModel.invoke(block: EdgeScope.() -> Unit): EdgeModel = EdgeScope(this).apply(block)

    /**
     * Subtracts the provided edge distribution from the existing edge model and applies the changes to the current instance.
     *
     * @param other The edge distribution to subtract from the edge model.
     * @return The updated edge model after applying the subtraction.
     *
     * ## Example
     *
     * If you want to add an edge distribution to an edge,
     * you can use the following code.
     *
     * ```kotlin
     * val a = node("a")
     * val b = node("b")
     * val c = node("c")
     *
     * val e1 = edge(a, b) - c
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a;
     * b;
     * c;
     *
     * a -> b -> c;
     * ```
     */
    operator fun EdgeModel.minus(other: EdgeDistribution): EdgeModel = apply {
        addDistribution(other)
    }

    /**
     * Subtracts the provided string representation of an edge distribution from the current `EdgeModel`
     * and applies the changes to the current instance.
     *
     * @param other The string representation of an edge distribution to subtract from the edge model.
     * @return The updated `EdgeModel` after applying the subtraction.
     *
     * ## Example
     *
     * If you want to add an edge distribution to an edge,
     * you can use the following code.
     *
     * ```kotlin
     * val a = node("a")
     * val b = node("b")
     *
     * val e1 = edge(a, b) - "c"
     * ```
     *
     *
     * This above code's "c" is a forward reference of node.
     * So, the above code is equivalent to the following kotlin code.
     *
     * ```kotlin
     * val a = node("a")
     * val b = node("b")
     *
     * val e1 = edge(a, b) - ref("c")
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a;
     * b;
     *
     * a -> b -> c;
     * ```
     */
    operator fun EdgeModel.minus(other: String): EdgeModel = apply {
        addDistribution(ForwardRefNode.from(other))
    }

    /**
     * Subtracts the provided edge distribution from a string representation of a node or edge,
     * creating an `EdgeModel` with the given configuration.
     *
     * @param other The edge distribution to subtract from the string representation of the node or edge.
     * @return The created `EdgeModel` representing the edge.
     *
     * ## Example
     *
     * If you want to create an edge with a forward reference of a node,
     *
     * ```kotlin
     * val b = node("b") {
     *  color = "red"
     * }
     * "a" - b
     * ```
     *
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * b [
     *   color="red";
     * ];
     *
     * a -> b;
     * ```
     */
    operator fun String.minus(other: EdgeDistribution): EdgeModel {
        return edge(ForwardRefNode.from(this), other)
    }

    /**
     * Subtracts the provided edge distribution from the current edge distribution and creates a new `EdgeModel`.
     *
     * @param other The `EdgeDistribution` to subtract from the current instance.
     * @return The resulting `EdgeModel` after applying the subtraction.
     *
     * ## Example
     *
     * If you want to create an edge with a forward reference of a node,
     *
     * ```kotlin
     * val a = node("a") {
     *   color = "red"
     * }
     * val b = node("b")
     *
     * a - b
     * ```
     *
     * This above code is equivalent to the following DOT code.
     *
     * ```dot
     * a [
     *   color="red";
     * ];
     * b;
     *
     * a -> b;
     * ```
     */
    operator fun EdgeDistribution.minus(other: EdgeDistribution): EdgeModel {
        return edge(this, other)
    }
}