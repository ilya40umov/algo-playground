from src.bfs import Graph
import unittest


class TestBfs(unittest.TestCase):
    def setUp(self):
        graph = Graph({"A", "B", "C", "D", "E", "F", "G", "H", "Z"})
        graph.add_edge("A", "B")
        graph.add_edge("A", "C")
        graph.add_edge("A", "G")
        graph.add_edge("B", "D")
        graph.add_edge("C", "E")
        graph.add_edge("D", "E")
        graph.add_edge("D", "F")
        graph.add_edge("E", "F")
        graph.add_edge("F", "H")
        graph.add_edge("G", "H")
        self.graph = graph

    def test_bfs_finds_shortest_path_from_a_to_a(self):
        self.assertListEqual(self.graph.find_shortest_path("A", "A"), ["A"])

    def test_bfs_finds_shortest_path_from_a_to_b(self):
        self.assertListEqual(self.graph.find_shortest_path("A", "B"), ["A", "B"])

    def test_bfs_finds_shortest_path_from_a_to_h(self):
        self.assertListEqual(self.graph.find_shortest_path("A", "H"), ["A", "G", "H"])

    def test_bfs_finds_shortest_path_from_b_to_h(self):
        self.assertListEqual(
            self.graph.find_shortest_path("B", "H"), ["B", "A", "G", "H"]
        )