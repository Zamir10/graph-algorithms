package graph.expose;

import graph.model.DirectedGraph;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class DirectedGraphController {

  @RequestMapping(method = RequestMethod.POST, path = "/graph/hasCycle")
  public boolean checkCycle(@RequestBody int[][] edges) {
    DirectedGraph graph = new DirectedGraph();
    return graph.cyclic(edges);
  }
}