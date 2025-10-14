package graph;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DirectedWeightedGraphTest {

  @Test
  public void testGraphHasACycle() {
    int[][] vertex = new int[][]{{1, 2, 5}, {1, 3, 10}, {2, 4, 2}, {3, 4, 1}, {4, 5, 3}};
    DirectedWeightedGraph graph = new DirectedWeightedGraph(vertex);

    graph.dijkstra(1, 3);
  }
}
