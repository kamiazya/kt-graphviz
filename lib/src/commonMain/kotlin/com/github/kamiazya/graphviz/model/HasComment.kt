package com.github.kamiazya.graphviz.model

/**
 * HasComment is an interface for models that have a comment.
 */
public interface HasComment {
    /**
     * A comment for the model.
     */
    var comment: String?
}
