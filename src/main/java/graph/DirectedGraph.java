package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DirectedGraph {

  public boolean cyclic(int[][] edges) {
    Map<Integer, List<Integer>> adjacency = new HashMap<>();
    for (int[] edge : edges) {
      adjacency.computeIfAbsent(edge[0],
          k -> new ArrayList<>()).add(edge[1]);
    }
    Map<Integer, Integer> visit = new HashMap<>();

    for (Entry<Integer, List<Integer>> entry : adjacency.entrySet()) {
      visit.put(entry.getKey(), 0);
    }

    for (int node : adjacency.keySet()) {
      if (hasCycle(adjacency, visit, node)) {
        return true;
      }
    }
    return false;
  }

  private boolean hasCycle(Map<Integer, List<Integer>> adjacency, Map<Integer, Integer> visit,
      int node) {
    if (adjacency.containsKey(node)) {
      visit.replace(node, 2);
      for (int n : adjacency.get(node)) {
        if (visit.containsKey(n)) {
          if (visit.get(n) == 2) {
            return true;
          }
          if (visit.get(n) == 0 && hasCycle(adjacency, visit, n)) {
            return true;
          }
        }
      }
    }
    visit.replace(node, 1);
    return false;
  }
}
