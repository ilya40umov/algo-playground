from dataclasses import dataclass
from typing import List, Optional
from collections import deque


@dataclass
class Vertex:
    id: str
    neighbour_ids: List[str]


@dataclass
class Graph:
    vertices: List[Vertex]

    def find_shortest_path(self, from_id: str, to_id: str) -> Optional[List[str]]:
        if from_id == to_id:
            return [to_id]

        neighbour_ids_by_id = {v.id: v.neighbour_ids for v in self.vertices}

        processed_ids = set()
        prev_path_id_by_id = {}
        queued_ids = deque(from_id)

        def restore_path(vertex_id: str) -> List[str]:
            path = []
            while vertex_id is not None:
                path.append(vertex_id)
                vertex_id = prev_path_id_by_id.get(vertex_id)
            path.reverse()
            return path

        while queued_ids:
            current_id = queued_ids.popleft()
            processed_ids.add(current_id)

            if current_id not in neighbour_ids_by_id:
                continue

            neighbour_ids = neighbour_ids_by_id[current_id]
            for neighbour_id in neighbour_ids:
                if neighbour_id in processed_ids:
                    continue

                if neighbour_id == to_id:
                    return restore_path(current_id) + [to_id]

                if neighbour_id not in prev_path_id_by_id:
                    prev_path_id_by_id[neighbour_id] = current_id

                queued_ids.append(neighbour_id)

        return None
