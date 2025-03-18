package me.ilya40umov.algo.graph;

import me.ilya40umov.algo.graph.model.UndirectedWeightedGraph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BellmanFordAlgorithm {
    private static class WeightedVertex<T> {
        final UndirectedWeightedGraph<T>.Vertex vertex;
        Integer pathWeight;
        WeightedVertex<T> previousInPath;

        public WeightedVertex(UndirectedWeightedGraph<T>.Vertex vertex) {
            this.vertex = vertex;
            this.pathWeight = Integer.MAX_VALUE;
            this.previousInPath = null;
        }
    }

    @SuppressWarnings({"SuspiciousMethodCalls", "DuplicatedCode"})
    public static <T> Optional<List<T>> findShortestPath(
            UndirectedWeightedGraph<T> graph,
            T originId,
            T destId
    ) {
        var weightedVertexById =
                graph.getVertices()
                        .stream()
                        .collect(Collectors.toMap(UndirectedWeightedGraph.Vertex::getId, WeightedVertex::new));
        var numOfVertices = graph.getVertices().size();

        var originWeightedVertex = weightedVertexById.get(originId);
        if (originWeightedVertex == null) {
            return Optional.empty();
        }
        originWeightedVertex.pathWeight = 0;

        var weightsChanged = true;
        var iteration = 0;
        while (weightsChanged && iteration < numOfVertices) {
            weightsChanged = false;
            iteration++;
            for (var vertex : graph.getVertices()) {
                var weightedVertex = weightedVertexById.get(vertex.getId());
                if (weightedVertex.pathWeight == Integer.MAX_VALUE) {
                    continue;
                }
                for (var adjacentVertex : vertex.getAdjacentVertices()) {
                    var adjacentWeightedVertex = weightedVertexById.get(adjacentVertex.getVertex().getId());
                    if (adjacentWeightedVertex.pathWeight > weightedVertex.pathWeight + adjacentVertex.getEdgeWeight()) {
                        adjacentWeightedVertex.pathWeight = weightedVertex.pathWeight + adjacentVertex.getEdgeWeight();
                        adjacentWeightedVertex.previousInPath = weightedVertex;
                        weightsChanged = true;
                    }
                }
            }
        }

        var destWeightedVertex = weightedVertexById.get(destId);
        if (destWeightedVertex == null || destWeightedVertex.previousInPath == null) {
            return Optional.empty();
        }

        var path = new LinkedList<T>();
        var current = destWeightedVertex;
        while (current != null) {
            path.add(current.vertex.getId());
            current = current.previousInPath;
        }
        Collections.reverse(path);

        return Optional.of(path);
    }
}
