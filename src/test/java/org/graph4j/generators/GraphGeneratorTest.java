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
package org.graph4j.generators;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.graph4j.Graph;
import org.graph4j.GraphTests;
import org.graph4j.generators.CompleteTreeGenerator;
import org.graph4j.generators.RandomGnmGraphGenerator;
import org.graph4j.generators.RandomGnpGraphGenerator;
import org.graph4j.generators.GraphGenerator;
import org.graph4j.generators.RandomTreeGenerator;
import org.graph4j.generators.RegularGraphGenerator;

/**
 *
 * @author Cristian Frăsinaru
 */
public class GraphGeneratorTest {

    private final Random random = new Random();

    public GraphGeneratorTest() {
    }

    @Test
    public void complete() {
        int n = 10;
        Graph g = GraphGenerator.complete(n);
        assertEquals((long) n * (n - 1) / 2, g.numEdges());
    }

    @Test
    public void completeBipartite() {
        int n1 = 10;
        int n2 = 5;
        Graph g = GraphGenerator.completeBipartite(n1, n2);
        assertEquals(n1 * n2, g.numEdges());
    }

    @Test
    public void path() {
        int n = 10;
        Graph g = GraphGenerator.path(n);
        assertEquals(n - 1, g.numEdges());
    }

    @Test
    public void cycle() {
        int n = 10;
        Graph g = GraphGenerator.cycle(n);
        assertEquals(n, g.numEdges());
        assertTrue(g.containsEdge(0, n - 1));
    }

    @Test
    public void wheel() {
        int n = 10;
        Graph g = GraphGenerator.wheel(n);
        assertEquals(2 * (n - 1), g.numEdges());
        for (int i = 1; i < n; i++) {
            assertTrue(g.containsEdge(0, i));
        }
    }

    @Test
    public void star() {
        int n = 10;
        Graph g = GraphGenerator.star(n);
        assertEquals(n - 1, g.numEdges());
        for (int i = 1; i < n; i++) {
            assertTrue(g.containsEdge(0, i));
        }
    }

    @Test
    public void randomGnm() {
        int n = 10;
        int m = 20;
        Graph g = new RandomGnmGraphGenerator(n, m).createGraph();
        assertEquals(m, g.numEdges());
    }

    @Test
    public void randomGnp() {
        int n = 100;
        var g1 = new RandomGnpGraphGenerator(n, 0).createGraph();
        var g2 = new RandomGnpGraphGenerator(n, 1).createGraph();
        assertEquals(0, g1.numEdges());
        assertEquals(n * (n - 1) / 2, g2.numEdges());
    }

    @Test
    public void randomTree() {
        int n = 10;
        var g = new RandomTreeGenerator(n).createTree();
        assertEquals(n - 1, g.numEdges());
        assertTrue(GraphTests.isConnected(g));
        assertTrue(GraphTests.isAcyclic(g));
    }

    @Test
    public void completeTree() {
        int numLevels = 5, degree = 3;
        int n = (int) (Math.pow(degree, numLevels) - 1) / (degree - 1);
        var g = new CompleteTreeGenerator(numLevels, degree).create();
        assertEquals(n, g.numVertices());
        assertEquals(n - 1, g.numEdges());
        assertTrue(GraphTests.isConnected(g));
        assertTrue(GraphTests.isAcyclic(g));
    }

    @Test
    public void regularEven() {
        int n = 10, degree = 4;
        var g = new RegularGraphGenerator(n, degree).createGraph();
        for (int v : g.vertices()) {
            assertEquals(degree, g.degree(v));
        }
    }

    @Test
    public void regularOdd() {
        int n = 10, degree = 3;
        var g = new RegularGraphGenerator(n, degree).createGraph();
        for (int v : g.vertices()) {
            assertEquals(degree, g.degree(v));
        }
    }

    @Test
    public void grid() {
        int m = 1 + random.nextInt(10);
        int n = 1 + random.nextInt(10);
        Graph g = GraphGenerator.grid(m, n);
        assertEquals(m * n, g.numVertices());
        assertEquals((m - 1) * n + (n - 1) * m, g.numEdges());
    }

}
