/*
 * Copyright (C) 2022 Cristian Frăsinaru and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graph4j.shortestpath;

import org.graph4j.Graph;
import org.graph4j.util.VertexHeap;

/**
 * Implementation of Dijkstra's algorithm that uses a heap in order to select
 * the optimal vertex at each step.
 *
 * The complexity of this implementation is O(m log n), where m is the number of
 * edges and n the number of vertices.
 *
 * Suitable for sparse graphs.
 *
 * <p>
 * {@inheritDoc}
 *
 * @author Cristian Frăsinaru
 */
public class DijkstraShortestPathHeap extends DijkstraShortestPathBase {

    private VertexHeap heap;

    public DijkstraShortestPathHeap(Graph graph, int source) {
        super(graph, source);
    }

    @Override
    protected void preCompute() {
        this.heap = new VertexHeap(graph, (i, j) -> (int) Math.signum(cost[i] - cost[j]));
    }

    @Override
    protected void postUpdate(int index) {
        heap.update(index);
    }

    @Override
    protected int findMinIndex() {
        return heap.poll();
    }
}
