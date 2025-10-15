package graph.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedWeightedGraph {

  private final Map<Integer, List<List<Integer>>> adjacency = new HashMap<>();

  public DirectedWeightedGraph(int[][] edges) {
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      int weight = edge[2];

      List<Integer> e1 = new ArrayList<>();
      e1.add(v);
      e1.add(weight);
      adjacency.computeIfAbsent(u, k -> new ArrayList<>()).add(e1);

      List<Integer> e2 = new ArrayList<>();
      e2.add(u);
      e2.add(weight);
      adjacency.computeIfAbsent(v, k -> new ArrayList<>()).add(e2);
    }
  }

  public Map<Integer, List<List<Integer>>> getAdjacency() {
    return adjacency;
  }
}
