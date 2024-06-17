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
package org.graph4j.util;

import org.graph4j.Graph;

/**
 * A <em>stable set</em> is a set of vertices of a graph, no two of which are
 * adjacent. It is also called an <em>independent set</em> of vertices.
 *
 * @author Cristian Frăsinaru
 */
public class StableSet extends VertexSet {

    public StableSet(Graph graph) {
        super(graph);
    }

    public StableSet(Graph graph, int initialCapacity) {
        super(graph, initialCapacity);
    }

    public StableSet(Graph graph, int[] vertices) {
        super(graph, vertices);
    }

    protected void checkEdge(int v, int u) {
        if (v == u) {
            return;
        }
        if (graph.containsEdge(v, u) || graph.containsEdge(u, v)) {
            throw new IllegalArgumentException(
                    "Vertices do not form a stable set, "
                    + "there is an edge connecting " + v + " and " + u);
        }
    }

    protected final void checkEdges() {
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                checkEdge(vertices[i], vertices[j]);
            }
        }
    }

    /**
     * Checks if the vertices in this set actually represent a stable set.
     *
     * @return {@code true} if the stable set is valid, {@code false} otherwise.
     */
    public boolean isValid() {
        try {
            checkEdges();
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the stable set is maximal.
     *
     * @return {@code true} if the stable set is maximal, {@code false}
     * otherwise.
     */
    public boolean isMaximal() {
        for (int v : graph.vertices()) {
            if (this.contains(v)) {
                continue;
            }
            boolean connectedToNone = true;
            for (int i = 0; i < numVertices; i++) {
                int u = vertices[i];
                if (graph.containsEdge(v, u)) {
                    connectedToNone = false;
                    break;
                }
            }
            if (connectedToNone) {
                return false;
            }
        }
        return true;
    }

}
