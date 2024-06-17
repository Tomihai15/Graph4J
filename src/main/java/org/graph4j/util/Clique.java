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
 * A <em>clique</em> is a set of vertices of a graph such that any two of them
 * are adjacent.
 *
 *
 * @author Cristian Frăsinaru
 */
public class Clique extends VertexSet {

    public Clique(Graph graph) {
        super(graph);
    }

    public Clique(Graph graph, int initialCapacity) {
        super(graph, initialCapacity);
    }

    /**
     *
     * @param graph the graph this clique belongs to.
     * @param vertices the vertices of the clique.
     */
    public Clique(Graph graph, int[] vertices) {
        super(graph, vertices);
    }

    /**
     *
     * @param other a vertex set.
     */
    public Clique(VertexSet other) {
        super(other);
    }

    @Override
    public Clique union(int... other) {
        Clique result = new Clique(graph, this.size() + other.length);
        union(this, other, result);
        return result;
    }

    protected void checkEdge(int v, int u) {
        if (v == u) {
            return;
        }
        if (!graph.containsEdge(v, u) && !graph.containsEdge(u, v)) {
            throw new IllegalArgumentException(
                    "Vertices do not form a clique, "
                    + "there is no edge connecting " + v + " and " + u);
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
     * Checks if the vertices in this set actually represent a clique.
     *
     * @return {@code true} if the clique is valid, {@code false} otherwise.
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
     * Checks if the clique is maximal.
     *
     * @return {@code true} if the clique is maximal, {@code false} otherwise.
     */
    public boolean isMaximal() {
        for (int v : graph.vertices()) {
            if (this.contains(v)) {
                continue;
            }
            boolean connectedToAll = true;
            for (int i = 0; i < numVertices; i++) {
                int u = vertices[i];
                if (!graph.containsEdge(v, u)) {
                    connectedToAll = false;
                    break;
                }
            }
            if (connectedToAll) {
                return false;
            }
        }
        return true;
    }

}
