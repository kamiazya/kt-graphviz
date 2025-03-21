package com.github.kamiazya.graphviz.core

import com.github.kamiazya.graphviz.model.DotModel
import com.github.kamiazya.graphviz.model.ModelContext
import com.github.kamiazya.graphviz.model.RootGraphModel

/**
 * Represents a Dot instance in a graph model.
 *
 * A Dot is a specific type of graph model implementation that serves as a structured representation
 * of a graph's elements including nodes, edges, and attributes.
 *
 * @property context The context holding the environment for this Dot model.
 * @property comment An optional comment string associated with this Dot instance.
 * @property root The root graph associated with this Dot instance.
 *                It represents the entry point or main graph structure.
 *
 * Implements the `DotModel` interface, which provides core functionality
 * for converting the Dot instance to its Abstract Syntax Tree (AST) representation
 * and handling its graph-related components.
 */
public class Dot(
    override var context: ModelContext,
    override var comment: String? = null,
    override var root: RootGraphModel? = null,
) : DotModel
