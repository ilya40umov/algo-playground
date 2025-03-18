package me.ilya40umov.algo.graph.model;

import java.util.*;

public class UndirectedWeightedGraph<T> {

    public class Vertex {
        private final T id;
        private final Set<AdjacentVertex> adjacentVertices;

        public Vertex(T identifier) {
            this.id = identifier;
            this.adjacentVertices = new LinkedHashSet<>();
        }

        @SuppressWarnings("unchecked")
        @Override
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

        public Set<AdjacentVertex> getAdjacentVertices() {
            return Collections.unmodifiableSet(adjacentVertices);
        }
    }

    public class AdjacentVertex {
        private final Vertex vertex;
        private final int edgeWeight;

        public AdjacentVertex(Vertex vertex, int edgeWeight) {
            this.vertex = vertex;
            this.edgeWeight = edgeWeight;
        }

        public Vertex getVertex() {
            return vertex;
        }

        public int getEdgeWeight() {
            return edgeWeight;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            AdjacentVertex that = (AdjacentVertex) o;
            return Objects.equals(vertex, that.vertex);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(vertex);
        }
    }

    private final Map<T, Vertex> vertexById;

    public UndirectedWeightedGraph() {
        vertexById = new LinkedHashMap<>();
    }

    public UndirectedWeightedGraph<T> addEdge(T fromId, T toId, int weight) {
        var from = vertexById.computeIfAbsent(fromId, Vertex::new);
        var to = vertexById.computeIfAbsent(toId, Vertex::new);
        from.adjacentVertices.add(new AdjacentVertex(to, weight));
        to.adjacentVertices.add(new AdjacentVertex(from, weight));
        return this;
    }

    public Vertex getVertexById(T id) {
        return vertexById.get(id);
    }

    public Collection<Vertex> getVertices() {
        return Collections.unmodifiableCollection(vertexById.values());
    }

}
