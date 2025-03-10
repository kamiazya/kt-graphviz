package com.github.kamiazya.graphviz.model

/**
 * RootGraphModel is an interface for root graph models.
 */
public interface RootGraphModel : BaseGraphModel {

    /**
     * A strict flag.
     */
    var strict: Boolean

    /**
     * A directed flag.
     */
    val directed: Boolean
}
