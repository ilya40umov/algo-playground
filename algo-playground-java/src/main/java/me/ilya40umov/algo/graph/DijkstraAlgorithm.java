package me.ilya40umov.algo.graph;

import me.ilya40umov.algo.graph.model.UndirectedWeightedGraph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DijkstraAlgorithm {
    private static class WeightedVertex<T> {
        final UndirectedWeightedGraph<T>.Vertex vertex;
        Integer pathWeight;
        WeightedVertex<T> previousInPath;

        WeightedVertex(UndirectedWeightedGraph<T>.Vertex vertex) {
            this.vertex = vertex;
            this.pathWeight = Integer.MAX_VALUE;
            this.previousInPath = null;
        }
    }

    private static class CandidateWeightedVertex<T> {
        final WeightedVertex<T> weightedVertex;
        final Integer capturedWeight;

        CandidateWeightedVertex(WeightedVertex<T> weightedVertex) {
            this.weightedVertex = weightedVertex;
            this.capturedWeight = weightedVertex.pathWeight;
        }
    }

    @SuppressWarnings({"SuspiciousMethodCalls", "DuplicatedCode"})
    public static <T> Optional<List<T>> findShortestPath(
            UndirectedWeightedGraph<T> graph,
            T originId,
            T destId
    ) {
        System.out.println("findShortestPath(" + originId + ", " + destId + ")");

        var weightedVertexById =
                graph.getVertices()
                        .stream()
                        .collect(Collectors.toMap(UndirectedWeightedGraph.Vertex::getId, WeightedVertex::new));

        var originWeightedVertex = weightedVertexById.get(originId);
        if (originWeightedVertex == null) {
            return Optional.empty();
        }
        originWeightedVertex.pathWeight = 0;

        var candidates = new PriorityQueue<CandidateWeightedVertex<T>>(
                Comparator.comparing(candidate -> candidate.capturedWeight)
        );
        candidates.add(new CandidateWeightedVertex<>(originWeightedVertex));

        var visitedIds = new HashSet<T>();

        while (!candidates.isEmpty()) {
            var currentCandidate = candidates.poll();
            var currentCandidateId = currentCandidate.weightedVertex.vertex.getId();
            if (!visitedIds.add(currentCandidateId)) {
                continue;
            }
            var weightedVertex = currentCandidate.weightedVertex;
            for (var adjacentVertex : currentCandidate.weightedVertex.vertex.getAdjacentVertices()) {
                var adjacentWeightedVertex = weightedVertexById.get(adjacentVertex.getVertex().getId());
                if (adjacentWeightedVertex.pathWeight > weightedVertex.pathWeight + adjacentVertex.getEdgeWeight()) {
                    adjacentWeightedVertex.pathWeight = weightedVertex.pathWeight + adjacentVertex.getEdgeWeight();
                    adjacentWeightedVertex.previousInPath = weightedVertex;
                }
                if (!visitedIds.contains(adjacentWeightedVertex.vertex.getId())) {
                    candidates.add(new CandidateWeightedVertex<>(adjacentWeightedVertex));
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
