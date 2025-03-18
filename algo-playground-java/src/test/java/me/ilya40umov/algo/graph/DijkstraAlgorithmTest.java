package me.ilya40umov.algo.graph;

import me.ilya40umov.algo.graph.model.UndirectedWeightedGraph;

import java.util.List;
import java.util.Optional;

class DijkstraAlgorithmTest extends UndirectedWeightedShortestPathTest {

    @Override
    protected Optional<List<String>> findShortestPath(
            UndirectedWeightedGraph<String> graph,
            String originId,
            String destId
    ) {
        return DijkstraAlgorithm.findShortestPath(graph, originId, destId);
    }
}