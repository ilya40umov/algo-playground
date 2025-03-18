package me.ilya40umov.algo.graph;

import java.util.List;
import java.util.Optional;

class BreadthFirstSearchTest extends UndirectedShortestPathSearchTest {

    @Override
    protected Optional<List<String>> findShortestPath(
            UndirectedGraph<String> graph,
            String fromId,
            String toId
    ) {
        return BreadthFirstSearch.findShortestPath(graph, fromId, toId);
    }
}