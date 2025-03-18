package me.ilya40umov.algo.graph;

import me.ilya40umov.algo.graph.model.UndirectedGraph;

import java.util.*;

public class DepthFirstSearch {

    public static <T> Optional<List<T>> findShortestPath(
            UndirectedGraph<T> graph,
            T originId,
            T destId
    ) {
        var fromVertex = graph.getVertexById(originId);
        var toVertex = graph.getVertexById(destId);
        if (fromVertex == null || toVertex == null) {
            return Optional.empty();
        }
        return findPathRecursive(
                Collections.singletonList(originId),
                fromVertex,
                new HashSet<>(),
                destId
        );
    }

    private static <T> Optional<List<T>> findPathRecursive(
            List<T> currentPath,
            UndirectedGraph<T>.Vertex currentVertex,
            Set<T> visitedIds,
            T toId
    ) {
        if (currentVertex.getId() == toId) {
            return Optional.of(currentPath);
        }
        visitedIds.add(currentVertex.getId());
        for (var neighbour : currentVertex.getAdjacentVertices()) {
            if (visitedIds.contains(neighbour.getId())) {
                continue;
            }
            var pathToNeighbour = new ArrayList<>(currentPath);
            pathToNeighbour.add(neighbour.getId());
            var shortestPath = findPathRecursive(pathToNeighbour, neighbour, visitedIds, toId);
            if (shortestPath.isPresent()) {
                return shortestPath;
            }
        }
        return Optional.empty();
    }

}
