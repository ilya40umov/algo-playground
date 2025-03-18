package me.ilya40umov.algo.graph;

import me.ilya40umov.algo.graph.model.UndirectedGraph;

import java.util.List;
import java.util.Optional;

class BreadthFirstSearchTest extends UndirectedShortestPathSearchTest {

    @Override
    protected Optional<List<String>> findShortestPath(
            UndirectedGraph<String> graph,
            String originId,
            String destId
    ) {
        return BreadthFirstSearch.findShortestPath(graph, originId, destId);
    }
}