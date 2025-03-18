package me.ilya40umov.algo.graph;

import me.ilya40umov.algo.graph.model.UndirectedGraph;
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

public abstract class UndirectedShortestPathSearchTest {

    protected abstract Optional<List<String>> findShortestPath(
            UndirectedGraph<String> graph,
            String originId,
            String destId
    );

    private UndirectedGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new UndirectedGraph<String>()
                .addEdge("A", "B")
                .addEdge("B", "C")
                .addEdge("B", "D")
                .addEdge("D", "E");
    }

    @Test
    void findShortestPath_shouldReturnEmptyOptional_whenNoPathBetweenVerticesExists() {
        assertEquals(
                Optional.empty(),
                findShortestPath(graph, "A", "F")
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
                findShortestPath(graph, fromId, toId)
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
