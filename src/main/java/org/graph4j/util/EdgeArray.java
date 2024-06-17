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

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import org.graph4j.Edge;
import org.graph4j.Graph;

/**
 * A primitive collection of edges in a graph.
 *
 * @see EdgeSet
 * @author Cristian Frăsinaru
 */
@Deprecated
public class EdgeArray { //WORK IN PROGRESS

    protected final Graph graph;
    protected int[][] edges;
    protected int numEdges;
    protected final static int DEFAULT_CAPACITY = 10;

    /**
     *
     * @param graph the graph the edges belong to.
     */
    public EdgeArray(Graph graph) {
        this(graph, DEFAULT_CAPACITY);
    }

    /**
     *
     * @param graph the graph the edges belong to.
     * @param initialCapacity the initial capacity of this collection.
     */
    public EdgeArray(Graph graph, int initialCapacity) {
        this.graph = graph;
        this.edges = new int[initialCapacity][2];
        this.numEdges = 0;
    }

    /**
     *
     * @param graph the graph the edges belong to.
     * @param edges the initial set of edges.
     */
    public EdgeArray(Graph graph, int[][] edges) {
        this.graph = graph;
        this.edges = edges;
        this.numEdges = edges.length;
    }

    /**
     *
     * @param graph the graph the edges belong to.
     * @param edges the initial set of edges.
     */
    public EdgeArray(Graph graph, Edge[] edges) {
        this(graph, edges.length);
        for (Edge e : edges) {
            add(e.source(), e.target());
        }
    }

    /**
     *
     * @return {@code true} if this collection has no edges.
     */
    public boolean isEmpty() {
        return numEdges == 0;
    }

    /**
     * Same as {@code size()}.
     *
     * @return the number of edges in the collection.
     */
    public int numEdges() {
        return numEdges;
    }

    /**
     * Same as {@code numVertices()}.
     *
     * @return the number of edges in the collection.
     */
    public int size() {
        return numEdges;
    }

    /**
     * For performance reasons, the returned array represents the actual data
     * structure where edges of the collection are stored, so it must not be
     * modified.
     *
     * @return the edges in the collection.
     */
    public int[][] edges() {
        if (numEdges != edges.length) {
            edges = Arrays.copyOf(edges, numEdges);
        }
        return edges;
    }

    public int indexOf(int v, int u) {
        return indexOf(v, u, 0);
    }

    public int indexOf(int v, int u, int startPos) {
        for (int i = 0; i < numEdges; i++) {
            if ((edges[i][0] == v && edges[i][1] == u)
                    || (!graph.isDirected() && edges[i][0] == u && edges[i][1] == v)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds an edge to the collection.
     *
     * @param v a vertex number.
     * @param u a vertex number.
     * @return {@code true}, if the collection changed as a result of this call,
     * {@code false} otherwise.
     */
    public boolean add(int v, int u) {
        if (numEdges == edges.length) {
            grow();
        }
        edges[numEdges][0] = v;
        edges[numEdges][1] = u;
        numEdges++;
        /*
        if (bitset != null) {
            bitset.set(v, true);
        }*/
        return true;
    }

    /**
     * Adds an edge to the collection.
     *
     * @param e an edge.
     * @return {@code true} if the collection was modified as a result of this
     * invocation, {@code false} otherwise.
     */
    public boolean add(Edge e) {
        return add(e.source(), e.target());
    }

    /**
     *
     * @param edges an array of edges.
     */
    public void addAll(int[]... edges) {
        for (int[] e : edges) {
            add(e[0], e[1]);
        }
    }

    /**
     * Removes an edge from the collection.
     *
     * @param v a vertex number.
     * @param u a vertex number.
     * @return {@code true}, if the collection was modified as a result of this
     * invocation, {@code false} otherwise.
     */
    public boolean remove(int v, int u) {
        int pos = indexOf(v, u);
        if (pos < 0) {
            return false;
        }
        removeFromPos(pos);
        return true;
    }

    /**
     * Removes an edge from the collection.
     *
     * @param e the edge to be removed.
     * @return {@code true}, if the collection was modified as a result of this
     * invocation, {@code false} otherwise.
     */
    public boolean remove(Edge e) {
        return remove(e.source(), e.target());
    }

    /**
     * Removes the last edge of the collection.
     */
    public void removeLast() {
        if (numEdges == 0) {
            throw new NoSuchElementException("The collection is empty");
        }
        numEdges--;
    }

    //the order is maintained by default
    public void removeFromPos(int pos) {
        for (int i = pos; i < numEdges - 1; i++) {
            edges[i] = edges[i + 1];
        }
        numEdges--;
    }

    /**
     * Checks if an edge belongs to the collection.
     *
     * @param v a vertex number.
     * @param u a vertex number.
     * @return {@code true}, if this collection contains the edge (v,u),
     * {@code false} otherwise.
     */
    public boolean contains(int v, int u) {
        return indexOf(v, u) >= 0;
    }

    /**
     * Computes the sum of the weights associated with each edge in the
     * collection.
     *
     * @return the sum of all weights of the edges in the collection, including
     * duplicates.
     */
    public double weight() {
        double weight = 0;
        for (int i = 0; i < numEdges; i++) {
            weight += graph.getEdgeWeight(edges[i][0], edges[i][1]);
        }
        return weight;
    }

    protected void grow() {
        int oldLen = edges.length;
        int newLen = Math.max(DEFAULT_CAPACITY, oldLen + (oldLen >> 1));
        int[][] newEdges = new int[newLen][2];
        for (int i = 0; i < oldLen; i++) {
            newEdges[i][0] = edges[i][0];
            newEdges[i][1] = edges[i][1];
        }
        edges = newEdges;
    }

    /**
     *
     * @return the vertices representing endpoints of the edges in this
     * collection.
     */
    public VertexSet vertexSet() {
        VertexSet set = new VertexSet(graph, size());
        for (int[] e : edges) {
            set.add(e[0]);
            set.add(e[1]);
        }
        Arrays.sort(set.vertices());
        return set;
    }

    /**
     *
     * @return the vertices representing endpoints of the edges in this
     * collection.
     */
    public int[] vertices() {
        return vertexSet().vertices();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Arrays.hashCode(this.edges());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EdgeArray other = (EdgeArray) obj;
        for (int i = 0; i < numEdges; i++) {
            if (!(this.edges[i][0] == other.edges[i][0] && this.edges[i][1] == other.edges[i][1])
                    || (!graph.isDirected() && this.edges[i][0] == other.edges[i][1] && this.edges[i][1] == other.edges[i][0])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        var sb = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < numEdges; i++) {
            sb.add(edges[i][0] + "-" + edges[i][1]);
        }
        return sb.toString();
    }

}
