package com.github.kamiazya.graphviz.model

/**
 * EdgeDistribution is a distribution of edges.
 * It can be a edge target like a node or ID of a node, or a list of edge targets.
 */
sealed interface EdgeDistribution : Model
