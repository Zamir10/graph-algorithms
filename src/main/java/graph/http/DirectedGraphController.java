package graph.http;

import graph.data.DirectedGraph;
import graph.data.DirectedWeightedGraph;
import graph.service.GraphService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectedGraphController {


  private final GraphService graphService;

  public DirectedGraphController(GraphService graphService) {
    this.graphService = graphService;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/graph/hasCycle")
  public boolean checkCycle(@RequestBody int[][] edges) {
    validateEdges(edges);
    DirectedGraph graph = new DirectedGraph(edges);

    return graphService.cyclic(graph);
  }

  @RequestMapping(method = RequestMethod.POST, path = "/graph/shortestPath/{source}/{destination}")
  public int[] shortestPath(@RequestBody int[][] edges, @PathVariable int source,
      @PathVariable int destination) {
    validateWeightedEdges(edges);
    DirectedWeightedGraph dwg = new DirectedWeightedGraph(edges);

    return graphService.dijkstra(dwg, source, destination);
  }

  public void validateEdges(int[][] edges) {
    for (int i = 0; i < edges.length; i++) {
      if (edges[i] == null) {
        throw new IllegalArgumentException("Edge at index " + i + " is null");
      }

      if (edges[i].length != 2) {
        throw new IllegalArgumentException(
            "Edge at index " + i + " must have exactly 2 nodes, but has " + edges[i].length
        );
      }

      if (edges[i][0] < 0 || edges[i][1] < 0) {
        throw new IllegalArgumentException(
            "Edge at index " + i + " contains negative node values: [" +
                edges[i][0] + ", " + edges[i][1] + "]"
        );
      }
    }
  }

  public void validateWeightedEdges(int[][] edges) {
    for (int i = 0; i < edges.length; i++) {
      if (edges[i] == null) {
        throw new IllegalArgumentException("Edge at index " + i + " is null");
      }

      if (edges[i].length != 3) {
        throw new IllegalArgumentException(
            "Edge at index " + i + " must have exactly 3 nodes, but has " + edges[i].length
        );
      }

      if (edges[i][0] < 0 || edges[i][1] < 0 || edges[i][2] < 0) {
        throw new IllegalArgumentException(
            "Edge at index " + i + " contains negative node values or negative weight: [" +
                edges[i][0] + ", " + edges[i][1] + ", " + edges[i][2] + "]"
        );
      }
    }
  }
}