package com.github.kamiazya.graphviz.model.dsl

import com.github.kamiazya.graphviz.model.DotModel
import com.github.kamiazya.graphviz.model.ModelContext

/**
 * Defines a dot model within the current `ModelContext`.
 *
 * @param comment Optional comment describing the dot model.
 * @param block A block defining the configuration and contents of the dot model using `DotScope`.
 * @return The created `DotModel` instance.
 */
fun ModelContext.dot(
    comment: String? = null,
    block: DotScope.() -> Unit
): DotModel = DotScope(
    createDot(
        context = this,
        comment = comment,
    )
).apply(block)
