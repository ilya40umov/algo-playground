from dataclasses import dataclass
from itertools import chain, groupby
from operator import attrgetter
from typing import List, Optional, Tuple


class Graph:
    @dataclass
    class Edge:
        from_vertex: str
        to_vertex: str
        weight: float

    def __init__(self, edges: List[Edge]):
        self.edges = edges
        self.vertices = set(
            chain(*[[edge.from_vertex, edge.to_vertex] for edge in edges])
        )
        by_from_vertex = attrgetter("from_vertex")
        self.outbound_by_vertex = {
            vertex: list(edges)
            for vertex, edges in groupby(
                sorted(edges, key=by_from_vertex), by_from_vertex
            )
        }

    def find_shortest_path(
        self, from_vertex: str, to_vertex: str
    ) -> Optional[Tuple[List[str], float]]:
        parent_by_vertex = {}
        weight_by_vertex = {from_vertex: 0}

        processed = set()

        def next_to_process() -> Tuple[str | None, str | None]:
            min_weight = None
            min_vertex = None
            for _vertex, _weight in weight_by_vertex.items():
                if _vertex in processed:
                    continue
                if min_weight is None or min_weight > _weight:
                    min_vertex = _vertex
                    min_weight = _weight
            if min_vertex:
                processed.add(min_vertex)
            return min_vertex, min_weight

        (vertex, weight) = next_to_process()
        while vertex:
            for edge in self.outbound_by_vertex.get(vertex, []):
                potential_weight = weight + edge.weight
                if (
                    weight_by_vertex.get(edge.to_vertex, float("inf"))
                    > potential_weight
                ):
                    weight_by_vertex[edge.to_vertex] = potential_weight
                    parent_by_vertex[edge.to_vertex] = vertex
            (vertex, weight) = next_to_process()

        if to_vertex not in parent_by_vertex:
            return None

        path = []
        current_vertex = to_vertex
        while current_vertex != from_vertex:
            path.append(current_vertex)
            current_vertex = parent_by_vertex[current_vertex]
        path.append(from_vertex)
        path.reverse()

        return path, weight_by_vertex[to_vertex]
