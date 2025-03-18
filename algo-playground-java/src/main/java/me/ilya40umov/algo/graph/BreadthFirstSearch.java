package me.ilya40umov.algo.graph;

import me.ilya40umov.algo.graph.model.UndirectedGraph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BreadthFirstSearch {

    private record QueuedPath<T>(
            UndirectedGraph<T>.Vertex vertex,
            List<T> path
    ) {
    }

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
        Deque<QueuedPath<T>> queue = new LinkedList<>();
        Set<T> processedVertices = new HashSet<>();
        queue.addLast(new QueuedPath<>(fromVertex, Collections.singletonList(originId)));
        while (!queue.isEmpty()) {
            var currentPath = queue.removeFirst();
            if (currentPath.vertex.getId() == destId) {
                return Optional.of(currentPath.path);
            }
            processedVertices.add(currentPath.vertex.getId());
            for (var neighbour : currentPath.vertex.getAdjacentVertices()) {
                if (processedVertices.contains(neighbour.getId())) {
                    continue;
                }
                var pathToNeighbour = Stream.concat(
                        currentPath.path.stream(),
                        Stream.of(neighbour.getId())
                ).collect(Collectors.toList());
                queue.add(new QueuedPath<>(neighbour, pathToNeighbour));
            }
        }
        return Optional.empty();
    }

}
