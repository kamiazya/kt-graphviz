package com.github.kamiazya.graphviz.example

import com.github.kamiazya.graphviz.ast.AST
import com.github.kamiazya.graphviz.ast.dsl.dot

var asts: List<AST> = listOf(
    dot {
        comment("AA")

        rootGraph(
            strict = true,
            directed = true,
            id = "hoge".unquoted()
        ) {
            node("node1".unquoted()) {
                attribute("color".unquoted(), "red".quoted())
            }

            edge({
                nodeRef("a1".unquoted())
                nodeRef("a2".unquoted())

                clusterNodeRefs(
                    nodeRefOf("b".unquoted()),
                    nodeRefOf("c".unquoted())
                )
            }) {
                attribute("color".unquoted(), "blue".quoted())
            }

            subgraph(id = null) {
                node("node2".quoted()) {}
            }
        }
    }
)
