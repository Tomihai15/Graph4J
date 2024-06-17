package org.graph4j.spanning;

import org.graph4j.Edge;
import org.graph4j.Graph;

/**
 *
 * Boruvka algorithm for finding a minimum spanning tree.
 *
 * You can see more : https://en.wikipedia.org/wiki/Bor%C5%AFvka%27s_algorithm
 *
 * @author Sorodoc Cosmin
 */
public class BoruvkaMinimumSpanningTreeDefault extends BoruvkaMinimumSpanningTreeBase {

    public BoruvkaMinimumSpanningTreeDefault(Graph graph) {
        super(graph);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean updateCheapestEdges() {

        boolean findOutgoingEdge = false;

        for (Edge e : this.graph.edges()) {
            int node1 = e.source();
            int node2 = e.target();
            int componentNode1 = uf.find(node1);
            int componentNode2 = uf.find(node2);

            if (componentNode1 == componentNode2) {//they are in the same component
                continue;
            }

            //find the smallest weighted edge for each component
            if (cheapest[componentNode1] == null || e.weight() < cheapest[componentNode1].weight()) {
                cheapest[componentNode1] = e;
                findOutgoingEdge = true;
            }

            if (cheapest[componentNode2] == null || e.weight() < cheapest[componentNode2].weight()) {
                cheapest[componentNode2] = e;
            }

        }

        return findOutgoingEdge;

    }
}
