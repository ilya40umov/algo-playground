package me.ilya40umov.algo.graph;

import me.ilya40umov.algo.graph.model.UndirectedWeightedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class UndirectedWeightedShortestPathTest {

    protected abstract Optional<List<String>> findShortestPath(
            UndirectedWeightedGraph<String> graph,
            String originId,
            String destId
    );

    private UndirectedWeightedGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new UndirectedWeightedGraph<String>()
                .addEdge("A", "B", 5)
                .addEdge("A", "C", 8)
                .addEdge("A", "D", 30)
                .addEdge("B", "C", 7)
                .addEdge("C", "D", 3)
                .addEdge("C", "E", 1)
                .addEdge("E", "D", 1)
                .addEdge("D", "F", 7)
                .addEdge("B", "F", 13)
                .addEdge("G", "K", 2)
                .addEdge("G", "L", 3);
    }

    @Test
    void findShortedPath_shouldReturnEmptyOptional_whenOriginVertexNotFound() {
        assertEquals(
                Optional.empty(),
                findShortestPath(graph, "X", "F")
        );
    }

    @Test
    void findShortedPath_shouldReturnEmptyOptional_whenDestinationVertexNotFound() {
        assertEquals(
                Optional.empty(),
                findShortestPath(graph, "A", "X")
        );
    }

    @Test
    void findShortedPath_shouldReturnEmptyOptional_whenNoPathLeadsToDestinationVertex() {
        assertEquals(
                Optional.empty(),
                findShortestPath(graph, "A", "K")
        );
    }

    @Test
    void findShortedPath_shouldReturnOptionalWithOnlyPath_whenSinglePathLeadsToDestinationVertex() {
        assertEquals(
                Optional.of(Arrays.asList("K", "G", "L")),
                findShortestPath(graph, "K", "L")
        );
    }

    @Test
    void findShortedPath_shouldReturnOptionalWithShortestPath_whenMultiplePathsLeadsToDestinationVertex() {
        assertEquals(
                Optional.of(Arrays.asList("A", "C", "E", "D", "F")),
                findShortestPath(graph, "A", "F")
        );
    }

}
