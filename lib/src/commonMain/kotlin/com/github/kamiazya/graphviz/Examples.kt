package com.github.kamiazya.graphviz

val a: List<DotModel> = listOf(
    dot {
        comment = "This is a sample graph."

        graph("graphID") {

            node {
                color = "red"
            }
            val a = node("a") {
                comment = "This is a sample node a."

                color = "blue"
            }

            val b = node("b") {
                comment = "This is a sample node b."
            }

            var c = ref("c")

            edge(a, b.port("port_of_a", Compass.N), c) {
                color = "red"
            }

            a - b - c

            // var e2 = ("a" - refs(a, c)) {
            //     color = "red"
            // }

            // var x = (a - b - c - "x") {
            //     color = "red"
            // }
            // println(e1)
            // println(e2)

            // ("a" - b - "c") {
            //     color = "red"
            // }

            // ("a" - b.port("port_of_a", Compass.N) - "c") {
            //     color = "red"
            // }

            // ("a" - b.port("port_of_a", Compass.N) - c) {
            //     color = "red"
            // }

            // ("a" - b - c.port("port_of_c", Compass.N)) {
            //     color = "red"
            // }
        }
    },
    dot {
        comment = "This is a sample graph."

        digraph {
            // val a = node("a")
            // val b = node("b")
            // val c = node("c")

            // a to b to c {
            //     color = "red"
            // }

            // var e = edge(a, b, c) {
            //     color = "red"
            // }
        }
    },
    dot {
        comment = "This is a sample graph."
        // strict digraph {

        //     node("a") {
        //         //
        //     }
        // }
    },
    dot {
        comment = "This is a sample graph."
        // strict digraph("graphID") {
        //     node {
        //         id = "a"
        //     }
        // }
    }
)
