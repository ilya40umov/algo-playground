from src.dijkstra import Graph
import unittest


class TestDijkstra(unittest.TestCase):
    def test_find_shortest_path_returns_none_when_graph_empty(self):
        g = Graph([])
        self.assertIsNone(g.find_shortest_path("A", "B"))

    def test_find_shortest_path_returns_none_when_no_path_possible(self):
        g = Graph([Graph.Edge("A", "B", 1), Graph.Edge("C", "D", 2)])
        self.assertIsNone(g.find_shortest_path("A", "D"))

    def test_find_shortest_path_returns_path_when_only_one_path_exists(self):
        g = Graph([Graph.Edge("A", "B", 1), Graph.Edge("B", "C", 2)])
        self.assertEqual(g.find_shortest_path("A", "C"), (["A", "B", "C"], 3))

    def test_find_shortest_path_returns_none_when_multiple_paths_exist(self):
        g = Graph(
            [
                Graph.Edge("A", "B", 1),
                Graph.Edge("A", "C", 4),
                Graph.Edge("B", "C", 2),
                Graph.Edge("B", "E", 1),
                Graph.Edge("B", "X", 4),
                Graph.Edge("C", "D", 3),
                Graph.Edge("X", "D", 2),
            ]
        )
        self.assertEqual(g.find_shortest_path("A", "D"), (["A", "B", "C", "D"], 6))


if __name__ == "__main__":
    unittest.main()
