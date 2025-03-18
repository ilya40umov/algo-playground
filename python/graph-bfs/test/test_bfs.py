from src.bfs import Graph, Vertex
import unittest


class TestBfs(unittest.TestCase):
    def setUp(self):
        self.graph = Graph(
            vertices=[
                Vertex("A", ["B", "C", "G"]),
                Vertex("B", ["D"]),
                Vertex("C", ["E"]),
                Vertex("D", ["E", "F"]),
                Vertex("E", ["F"]),
                Vertex("F", ["H"]),
                Vertex("G", ["H"]),
                Vertex("H", []),
                Vertex("Z", []),
            ]
        )

    def test_bfs_finds_shortest_path_from_a_to_a(self):
        self.assertListEqual(self.graph.find_shortest_path("A", "A"), ["A"])

    def test_bfs_finds_shortest_path_from_a_to_b(self):
        self.assertListEqual(self.graph.find_shortest_path("A", "B"), ["A", "B"])

    def test_bfs_finds_shortest_path_from_a_to_h(self):
        self.assertListEqual(self.graph.find_shortest_path("A", "H"), ["A", "G", "H"])

    def test_bfs_finds_shortest_path_from_b_to_h(self):
        self.assertListEqual(
            self.graph.find_shortest_path("B", "H"), ["B", "D", "F", "H"]
        )


if __name__ == "__main__":
    unittest.main()
