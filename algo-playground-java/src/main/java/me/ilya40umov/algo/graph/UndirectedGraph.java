package me.ilya40umov.algo.graph;

import java.util.*;

public class UndirectedGraph<T> {
    static class Vertex<T> {
        final T id;
        final Set<Vertex<T>> adjacentVertices;

        public Vertex(T identifier) {
            this.id = identifier;
            this.adjacentVertices = new LinkedHashSet<>();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<T> vertex = (Vertex<T>) o;
            return Objects.equals(id, vertex.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }

    private final Map<T, Vertex<T>> vertexById;

    public UndirectedGraph() {
        vertexById = new LinkedHashMap<>();
    }

    public void addVertex(T id) {
        vertexById.putIfAbsent(id, new Vertex<>(id));
    }

    public void addEdge(T fromId, T toId) {
        var from = vertexById.get(fromId);
        if (from == null) {
            throw new IllegalArgumentException("Vertex not found: " + fromId);
        }
        var to = vertexById.get(toId);
        if (to == null) {
            throw new IllegalArgumentException("Vertex not found: " + toId);
        }
        from.adjacentVertices.add(to);
        to.adjacentVertices.add(from);
    }

    Vertex<T> getVertexById(T id) {
        return vertexById.get(id);
    }
}
