package com.github.kamiazya.graphviz.model

/**
 * BaseGraphModel serves as a base interface for graph models, combining functionalities from
 * multiple graph-related interfaces. It supports attributes, nodes, edges, subgraphs, and contexts,
 * along with additional metadata such as comments and IDs.
 *
 * This interface aggregates features such as:
 * - Attribute management
 * - Node and edge management
 * - Subgraph handling
 * - Contextual information and attribute grouping
 * - Optional identification and comments
 */
public interface BaseGraphModel :
    GraphAttributeGroup,
    HasNullableID,
    HasComment,
    HasAttributes,
    HasNodes,
    HasEdges,
    HasSubgraphs,
    HasContext,
    HasAttributeGroups
