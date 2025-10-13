package graph.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import graph.model.DirectedGraph;
import org.junit.jupiter.api.Test;

public class DirectedGraphTest {

  @Test
  public void testGraphHasACycle() {
    int[][] edges = new int[][]{{1, 2}, {2, 3}, {3, 1}};
    DirectedGraph graph = new DirectedGraph();
    assertTrue(graph.cyclic(edges));
  }

  @Test
  public void testGraphHasNoCycle() {
    int[][] edges = new int[][]{{1, 2}, {2, 3}, {3, 4}};
    DirectedGraph graph = new DirectedGraph();
    assertFalse(graph.cyclic(edges));
  }
}
