package me.ilya40umov.algo.graph.model;

import java.util.*;

public class UndirectedGraph<T> {
    public class Vertex {
        private final T id;
        private final Set<Vertex> adjacentVertices;

        public Vertex(T identifier) {
            this.id = identifier;
            this.adjacentVertices = new LinkedHashSet<>();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(id, vertex.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        public T getId() {
            return id;
        }

        public Set<Vertex> getAdjacentVertices() {
            return adjacentVertices;
        }
    }

    private final Map<T, Vertex> vertexById;

    public UndirectedGraph() {
        vertexById = new LinkedHashMap<>();
    }

    public UndirectedGraph<T> addEdge(T fromId, T toId) {
        var from = vertexById.computeIfAbsent(fromId, Vertex::new);
        var to = vertexById.computeIfAbsent(toId, Vertex::new);
        from.adjacentVertices.add(to);
        to.adjacentVertices.add(from);
        return this;
    }

    public Vertex getVertexById(T id) {
        return vertexById.get(id);
    }
}
