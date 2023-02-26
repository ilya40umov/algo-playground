from bfs import Vertex, Graph


def main():
    g = Graph(
        vertices=[
            Vertex("A", ["B", "C"]),
            Vertex("B", ["D"]),
            Vertex("C", ["E"]),
            Vertex("D", ["E", "F"]),
            Vertex("E", ["F"]),
            Vertex("F", []),
            Vertex("Z", []),
        ]
    )

    print("Graph:")
    for v in g.vertices:
        print(v.id, " -> ", v.neighbour_ids)
    print()

    print("Shorted path from A to A: ", g.find_shortest_path("A", "A"))
    print("Shorted path from A to B: ", g.find_shortest_path("A", "B"))
    print("Shorted path from B to E: ", g.find_shortest_path("B", "E"))
    print("Shorted path from A to F: ", g.find_shortest_path("A", "F"))
    print("Shorted path from A to Z: ", g.find_shortest_path("A", "Z"))


if __name__ == "__main__":
    main()
