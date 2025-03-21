package com.github.kamiazya.graphviz.model

/**
 * Represents a model that supports attribute grouping for graphs, nodes, and edges.
 *
 * This interface combines the following capabilities:
 * - Graph attribute grouping, as provided by `HasGraphAttributes`.
 * - Node attribute grouping, as defined by `HasNodeAttributeGroupModel`.
 * - Edge attribute grouping, as defined by `HasEdgeAttributeGroupModel`.
 *
 * Implementing this interface allows defining and managing attribute groups
 * for various graph elements, ensuring consistent and organized attribute handling.
 */
public interface HasAttributeGroups :
    HasGraphAttributes,
    HasNodeAttributeGroupModel,
    HasEdgeAttributeGroupModel
