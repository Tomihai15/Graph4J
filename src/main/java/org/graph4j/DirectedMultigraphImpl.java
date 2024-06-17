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
package org.graph4j;

import java.util.Collection;
import org.graph4j.util.VertexSet;

/**
 *
 * @author Cristian Frăsinaru
 */
class DirectedMultigraphImpl<V, E> extends DigraphImpl<V, E> implements DirectedMultigraph<V, E> {

    protected DirectedMultigraphImpl() {
    }

    protected DirectedMultigraphImpl(int[] vertices, int maxVertices, int avgDegree,
            boolean directed, boolean allowingMultipleEdges, boolean allowingSelfLoops,
            int vertexDataSize, int edgeDataSize) {
        super(vertices, maxVertices, avgDegree, directed, allowingMultipleEdges, allowingSelfLoops,
                vertexDataSize, edgeDataSize);
    }

    @Override
    protected DirectedMultigraphImpl newInstance() {
        return new DirectedMultigraphImpl();
    }

    @Override
    protected DirectedMultigraphImpl newInstance(int[] vertices, int maxVertices, int avgDegree,
            boolean directed, boolean allowingMultipleEdges, boolean allowingSelfLoops,
            int vertexDataSize, int edgeDataSize) {
        return new DirectedMultigraphImpl(vertices, maxVertices, avgDegree, directed,
                allowingMultipleEdges, allowingSelfLoops, vertexDataSize, edgeDataSize);
    }

    @Override
    public DirectedMultigraph<V, E> copy() {
        return (DirectedMultigraph<V, E>) super.copy();
    }

    @Override
    public DirectedMultigraph<V, E> copy(boolean vertexWeights, boolean vertexLabels, boolean edges, boolean edgeWeights, boolean edgeLabels) {
        return (DirectedMultigraph<V, E>) super.copy(vertexWeights, vertexLabels, edges, edgeWeights, edgeLabels);
    }

    @Override
    public DirectedMultigraph<V, E> subgraph(VertexSet vertexSet) {
        return (DirectedMultigraph<V, E>) super.subgraph(vertexSet);
    }

    @Override
    public DirectedMultigraph<V, E> subgraph(Collection<Edge> edges) {
        return (DirectedMultigraph<V, E>) super.subgraph(edges);
    }

    @Override
    public DirectedMultigraph<V, E> complement() {
        return (DirectedMultigraph<V, E>) super.complement();
    }

    @Override
    public boolean isComplete() {
        for (int i = 0; i < numVertices; i++) {
            int v = vertexAt(i);
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    continue;
                }
                int u = vertexAt(j);
                if (!containsEdge(v, u)) {
                    return false;
                }
            }
        }
        return true;
    }

}
