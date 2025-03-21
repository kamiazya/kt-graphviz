package com.github.kamiazya.graphviz

import com.github.kamiazya.graphviz.core.MODEL_CONTEXT
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.dsl.DotScope
import com.github.kamiazya.graphviz.model.dsl.dot

/**
 * A global variable that provides the default context for `ModelContext`.
 *
 * `DEFAULT_MODEL_CONTEXT` serves as a predefined instance of `ModelContext` that can
 * be used as the default environment for creating and managing DOT graph models.
 * It simplifies the instantiation process by offering a ready-to-use context,
 * reducing the need for explicit context creation in various graph-related operations.
 *
 * Typically used in methods where a `ModelContext` is required but not explicitly provided.
 * Enables consistent and standardized behavior across graph model operations.
 */
var DEFAULT_MODEL_CONTEXT: ModelContext = MODEL_CONTEXT

/**
 * Defines a DOT language representation within the default model context.
 *
 * @param comment An optional comment describing the DOT model.
 * @param block A lambda block that allows configuration and contents definition of the DOT model using `DotScope`.
 */
fun dot(
    comment: String? = null,
    block: DotScope.() -> Unit
) = DEFAULT_MODEL_CONTEXT.dot(
    comment = comment,
    block = block,
)
