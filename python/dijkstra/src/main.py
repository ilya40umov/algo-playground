from dijkstra import Graph


def main():
    g = Graph(
        [
            Graph.Edge("A", "B", 12),
            Graph.Edge("A", "D", 10),
            Graph.Edge("B", "C", 23),
            Graph.Edge("B", "D", 33),
            Graph.Edge("C", "D", 34),
            Graph.Edge("C", "F", 51),
            Graph.Edge("D", "E", 45),
            Graph.Edge("D", "F", 80),
            Graph.Edge("E", "F", 56),
        ]
    )

    print("Graph: ", sorted(list(g.vertices)))
    for vertex, edges in g.outbound_by_vertex.items():
        print(vertex, " -> ", end="")
        for edge in edges:
            print("[", edge.to_vertex, ":", edge.weight, "] ", sep="", end="")
        print()

    print("Shortest path from A to F: ", g.find_shortest_path("A", "F"))
    print("Shortest path from B to F: ", g.find_shortest_path("B", "F"))
    print("Shortest path from D to F: ", g.find_shortest_path("D", "F"))
    print("Shortest path from A to E: ", g.find_shortest_path("A", "E"))


if __name__ == "__main__":
    main()
