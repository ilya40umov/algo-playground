package me.ilya40umov.algo.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BreadthFirstSearch {

    private record QueuedPath<T>(
            UndirectedGraph.Vertex<T> vertex,
            List<T> path
    ) {
    }

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
        Deque<QueuedPath<T>> queue = new LinkedList<>();
        Set<T> processedVertices = new HashSet<>();
        queue.addLast(new QueuedPath<>(fromVertex, Collections.singletonList(fromId)));
        while (!queue.isEmpty()) {
            var currentPath = queue.removeFirst();
            if (currentPath.vertex.id == toId) {
                return Optional.of(currentPath.path);
            }
            processedVertices.add(currentPath.vertex.id);
            for (var neighbour : currentPath.vertex.adjacentVertices) {
                if (processedVertices.contains(neighbour.id)) {
                    continue;
                }
                var pathToNeighbour = Stream.concat(
                        currentPath.path.stream(),
                        Stream.of(neighbour.id)
                ).collect(Collectors.toList());
                queue.add(new QueuedPath<>(neighbour, pathToNeighbour));
            }
        }
        return Optional.empty();
    }

}
