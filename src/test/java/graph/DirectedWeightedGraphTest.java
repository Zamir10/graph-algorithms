package graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DirectedWeightedGraphTest {

  @Test
  public void testMinimumChosen() {
    int[][] edges = new int[][]{{1, 2, 5}, {1, 3, 10}, {2, 4, 2}, {3, 4, 1}, {4, 5, 3}};
    DirectedWeightedGraph graph = new DirectedWeightedGraph(edges);

    int[] expected = new int[]{1, 2, 4, 3};

    Assertions.assertArrayEquals(expected, graph.dijkstra(1, 3));
  }

  @Test
  public void testSimpleDirectGraph() {
    int[][] edges = new int[][]{{1, 2, 4}};

    DirectedWeightedGraph graph = new DirectedWeightedGraph(edges);
    int[] expected = new int[]{1, 2};

    Assertions.assertArrayEquals(expected, graph.dijkstra(1, 2));
  }

  @Test
  public void testSingleNode() {
    int[][] edges = new int[][]{{1, 2, 3}, {1, }};

    DirectedWeightedGraph graph = new DirectedWeightedGraph(edges);

    Assertions.assertNull(graph.dijkstra(1, 1));
  }

  @Test
  public void testCycledGraph() {
    int[][] edges = new int[][]{{1, 2,  5}, {2, 3, 7}, {3, 1, 14}};
    DirectedWeightedGraph graph = new DirectedWeightedGraph(edges);

    Assertions.assertArrayEquals(new int[]{1, 2, 3}, graph.dijkstra(1, 3));
  }

  @Test
  @Disabled
  public void testDuplicateEdges() {

  }
}
