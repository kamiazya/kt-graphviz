package com.github.kamiazya.graphviz.model

/**
 * BaseGraphModel is an interface for base of graph models.
 */
public interface BaseGraphModel :
    Model,
    HasNullableID,
    HasComment,
    HasAttributes,
    HasNodes,
    HasEdges,
    HasSubgraphs,
    HasContext,
    HasAttributeGroups
