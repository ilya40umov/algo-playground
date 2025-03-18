package me.ilya40umov.algo.graph;

import me.ilya40umov.algo.graph.model.UndirectedWeightedGraph;

import java.util.List;
import java.util.Optional;

class BellmanFordAlgorithmTest extends UndirectedWeightedShortestPathTest {

    @Override
    protected Optional<List<String>> findShortestPath(
            UndirectedWeightedGraph<String> graph,
            String originId,
            String destId
    ) {
        return BellmanFordAlgorithm.findShortestPath(graph, originId, destId);
    }
}