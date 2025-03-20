package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.DotModel
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.RootGraphModel

/**
 * Dot is a class for dot models.
 */
public class Dot(
    override var context: ModelContext,
    override var comment: String? = null,
    override var root: RootGraphModel? = null,
) : DotModel
