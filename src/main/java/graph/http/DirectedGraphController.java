package graph.http;

import graph.data.DirectedGraph;
import graph.data.DirectedWeightedGraph;
import graph.service.GraphService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class DirectedGraphController {


  private final GraphService graphService;

  public DirectedGraphController(GraphService graphService) {
    this.graphService = graphService;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/graph/hasCycle")
  public boolean checkCycle(@RequestBody int[][] edges) {
    DirectedGraph graph = new DirectedGraph(edges);

    return graphService.cyclic(graph);
  }

  @RequestMapping(method = RequestMethod.POST, path = "/graph/shortestPath/{source}/{destination}")
  public int[] shortestPath(@RequestBody int[][] edges, @PathVariable int source,
      @PathVariable int destination) {
    DirectedWeightedGraph dwg = new DirectedWeightedGraph(edges);

    return graphService.dijkstra(dwg, source, destination);
  }
}