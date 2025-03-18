package me.ilya40umov.algo.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadthFirstSearchTest {

    private UndirectedGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new UndirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("D", "E");
    }

    @Test
    void findShortestPath_shouldReturnEmptyOptional_whenNoPathBetweenVerticesExists() {
        assertEquals(
                Optional.empty(),
                BreadthFirstSearch.findShortestPath(graph, "A", "F")
        );
    }


    @ParameterizedTest
    @MethodSource("provideShortedPathInputs")
    void findShortestPath_shouldReturnShortestPath_whenPathBetweenVerticesExists(
            List<String> path,
            String fromId,
            String toId
    ) {
        assertEquals(
                Optional.of(path),
                BreadthFirstSearch.findShortestPath(graph, fromId, toId)
        );
    }

    private static Stream<Arguments> provideShortedPathInputs() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList("A", "B"),
                        "A",
                        "B"
                ),
                Arguments.of(
                        Arrays.asList("A", "B", "D"),
                        "A",
                        "D"
                ),
                Arguments.of(
                        Arrays.asList("A", "B", "D", "E"),
                        "A",
                        "E"
                )
        );
    }

}