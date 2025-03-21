package com.github.kamiazya.graphviz.model

/**
 * HasContext is an interface for models that have a context.
 */
public interface HasContext {
    /**
     * Represents the context associated with a model, providing the necessary
     * environment and functionality for constructing and managing graph models.
     * The `context` is defined by the `ModelContext` interface, which includes
     * methods for creating various graph components such as nodes, edges, and subgraphs.
     */
    val context: ModelContext
}
