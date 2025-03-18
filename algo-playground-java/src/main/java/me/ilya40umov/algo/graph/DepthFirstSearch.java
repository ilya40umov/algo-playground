package me.ilya40umov.algo.graph;

import java.util.*;

public class DepthFirstSearch {

    public static <T> Optional<List<T>> findShortestPath(
            UndirectedGraph<T> graph,
            T fromId,
            T toId
    ) {
        var fromVertex = graph.getVertexById(fromId);
        var toVertex = graph.getVertexById(toId);
        if (fromVertex == null || toVertex == null) {
            return Optional.empty();
        }
        return findPathRecursive(
                Collections.singletonList(fromId),
                fromVertex,
                new HashSet<>(),
                toId
        );
    }

    private static <T> Optional<List<T>> findPathRecursive(
            List<T> currentPath,
            UndirectedGraph.Vertex<T> currentVertex,
            Set<T> visitedIds,
            T toId
    ) {
        if (currentVertex.id == toId) {
            return Optional.of(currentPath);
        }
        visitedIds.add(currentVertex.id);
        for (var neighbour : currentVertex.adjacentVertices) {
            if (visitedIds.contains(neighbour.id)) {
                continue;
            }
            var pathToNeighbour = new ArrayList<>(currentPath);
            pathToNeighbour.add(neighbour.id);
            var shortestPath = findPathRecursive(pathToNeighbour, neighbour, visitedIds, toId);
            if (shortestPath.isPresent()) {
                return shortestPath;
            }
        }
        return Optional.empty();
    }

}
