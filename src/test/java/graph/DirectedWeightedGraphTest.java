package graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectedWeightedGraphTest {

  @Test
  public void testGraphHasACycle() {
    int[][] edges = new int[][]{{1, 2, 5}, {1, 3, 10}, {2, 4, 2}, {3, 4, 1}, {4, 5, 3}};
    DirectedWeightedGraph graph = new DirectedWeightedGraph(edges);

    int[] expected = new int[]{1, 2, 4, 3};

    Assertions.assertArrayEquals(expected, graph.dijkstra(1, 3));
  }
}
