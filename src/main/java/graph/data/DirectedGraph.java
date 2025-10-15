package graph.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedGraph {

  private final Map<Integer, List<Integer>> adjacency = new HashMap<>();

  public DirectedGraph(int[][] edges) {
    for (int[] edge : edges) {
      adjacency.computeIfAbsent(edge[0],
          k -> new ArrayList<>()).add(edge[1]);
    }
  }

  public Map<Integer, List<Integer>> getAdjacency() {
    return adjacency;
  }
}
