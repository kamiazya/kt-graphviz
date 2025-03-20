package com.github.kamiazya.graphviz

import com.github.kamiazya.graphviz.core.MODEL_CONTEXT
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.dsl.DotScope
import com.github.kamiazya.graphviz.model.dsl.dot

var DEFAULT_MODEL_CONTEXT: ModelContext = MODEL_CONTEXT

fun dot(
    comment: String? = null,
    block: DotScope.() -> Unit
) = DEFAULT_MODEL_CONTEXT.dot(
    comment = comment,
    block = block,
)
