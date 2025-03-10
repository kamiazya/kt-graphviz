package com.github.kamiazya.graphviz.model

/**
 * HasContext is an interface for models that have a context.
 */
public interface HasContext {
    /**
     * A model context.
     */
    val context: ModelContext
}
