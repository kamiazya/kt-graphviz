package com.github.kamiazya.graphviz.model

/**
 * HasComment is an interface for models that have a comment.
 */
public interface HasComment {
    /**
     * Represents an optional comment associated with the model.
     *
     * The comment provides a way to add descriptive or explanatory
     * text to enhance the clarity or understanding of the model's
     * purpose or functionality.
     *
     * This property can be null, indicating no comment is attached.
     */
    var comment: String?
}
