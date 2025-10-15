package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

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

  public int[] dijkstra(int source, int destination) {
    PriorityQueue<List<Integer>> pq =
        new PriorityQueue<>(Comparator.comparing(k -> k.get(0)));

    int[] distances = new int[adjacency.size()];
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

      for (List<Integer> neighbor : adjacency.get(edge)) {
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
