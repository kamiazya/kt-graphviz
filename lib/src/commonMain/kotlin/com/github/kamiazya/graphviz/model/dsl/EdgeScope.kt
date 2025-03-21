package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.EdgeModel

/**
 * A specialized scope for working with `EdgeModel` objects in a DSL context.
 *
 * This class facilitates interaction and manipulation of an `EdgeModel` by delegating
 * its behavior directly to the provided `edge` instance. As part of the GraphViz model DSL,
 * it extends the capabilities of the edge definition, allowing access to and customization
 * of edge-related attributes, comments, and edge-specific configurations.
 *
 * @constructor Creates an `EdgeScope` instance by wrapping a given `EdgeModel`.
 * @param edge The `EdgeModel` instance to be managed and delegated by this scope.
 */
public class EdgeScope(
    private val edge: EdgeModel
) : EdgeModel by edge
