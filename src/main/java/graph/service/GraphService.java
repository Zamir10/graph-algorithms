package graph.service;

import graph.data.DirectedGraph;
import graph.data.DirectedWeightedGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class GraphService {

  public boolean cyclic(DirectedGraph graph) {

    Map<Integer, Integer> visit = new HashMap<>();

    for (Entry<Integer, List<Integer>> entry : graph.getAdjacency().entrySet()) {
      visit.put(entry.getKey(), 0);
    }

    for (int node : graph.getAdjacency().keySet()) {
      if (hasCycle(graph.getAdjacency(), visit, node)) {
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

  public int[] dijkstra(DirectedWeightedGraph graph, int source, int destination) {

    PriorityQueue<List<Integer>> pq =
        new PriorityQueue<>(Comparator.comparing(k -> k.get(0)));

    int[] distances = new int[graph.getAdjacency().size()];
    Arrays.fill(distances, Integer.MAX_VALUE);

    distances[source - 1] = 0;
    List<Integer> start = new ArrayList<>();
    start.add(0);
    start.add(source);
    pq.offer(start);

    Map<Integer, Set<Integer>> paths = new HashMap<>();

    while (!pq.isEmpty()) {
      List<Integer> current = pq.poll();
      int edge = current.get(1);

      for (List<Integer> neighbor : graph.getAdjacency().get(edge)) {
        int n = neighbor.get(0);
        int weight = neighbor.get(1);
        if (distances[n - 1] > distances[edge - 1] + weight) {
          distances[n - 1] = distances[edge - 1] + weight;

          Set<Integer> path = paths.get(edge);
          paths.computeIfAbsent(n, k -> new LinkedHashSet<>());
          if (path != null) {
            paths.get(n).addAll(path);
          }
          paths.get(n).add(edge);

          List<Integer> temp = new ArrayList<>();
          temp.add(distances[n - 1]);
          temp.add(n);
          pq.offer(temp);
        }
      }
    }

    Set<Integer> shortest = paths.get(destination);
    if (shortest != null) {
      shortest.add(destination);
    }
    return shortest == null ? null : shortest.stream().mapToInt(Integer::intValue).toArray();
  }
}
