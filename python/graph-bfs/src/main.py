from bfs import Graph


def main():
    g = Graph(["A", "B", "C", "D", "E", "F", "Z"])
    g.add_edge("A", "B")
    g.add_edge("A", "C")
    g.add_edge("B", "D")
    g.add_edge("C", "E")
    g.add_edge("D", "E")
    g.add_edge("D", "F")
    g.add_edge("E", "F")

    print("Graph:")
    for v in sorted(g.vertices):
        print(v, " -> ", list(g.neighbours_by_vertex[v]))
    print()

    print("Shorted path from A to A: ", g.find_shortest_path("A", "A"))
    print("Shorted path from A to B: ", g.find_shortest_path("A", "B"))
    print("Shorted path from B to E: ", g.find_shortest_path("B", "E"))
    print("Shorted path from A to F: ", g.find_shortest_path("A", "F"))
    print("Shorted path from A to Z: ", g.find_shortest_path("A", "Z"))


if __name__ == "__main__":
    main()
