from typing import Set, List, Optional
from collections import deque


class Graph:
    def __init__(self, vertices: Set[str]):
        self.vertices = set(vertices)
        self.neighbours_by_vertex = {vertex: [] for vertex in vertices}

    def add_edge(self, from_vertex: str, to_vertex: str):
        if from_vertex not in self.vertices or to_vertex not in self.vertices:
            return
        if to_vertex not in self.neighbours_by_vertex[from_vertex]:
            self.neighbours_by_vertex[from_vertex].append(to_vertex)
        if from_vertex not in self.neighbours_by_vertex[to_vertex]:
            self.neighbours_by_vertex[to_vertex].append(from_vertex)

    def find_shortest_path(
        self, from_vertex: str, to_vertex: str
    ) -> Optional[List[str]]:
        if from_vertex not in self.vertices or to_vertex not in self.vertices:
            return []

        if from_vertex == to_vertex:
            return [to_vertex]

        processed = set()
        queued = deque(from_vertex)
        prev_in_path_by_vertex = {}

        def restore_path(vertex: str) -> List[str]:
            path = []
            while vertex is not None:
                path.append(vertex)
                vertex = prev_in_path_by_vertex.get(vertex)
            path.reverse()
            return path

        while queued:
            current = queued.popleft()
            processed.add(current)

            for neighbour in self.neighbours_by_vertex[current]:
                if neighbour in processed:
                    continue

                if neighbour == to_vertex:
                    return restore_path(current) + [to_vertex]

                if neighbour not in prev_in_path_by_vertex:
                    prev_in_path_by_vertex[neighbour] = current

                queued.append(neighbour)

        return None
