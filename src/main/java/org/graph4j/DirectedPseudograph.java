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
 * Multiple (parallel) edges are allowed.
 *
 * @author Cristian Frăsinaru
 * @param <V> the type of vertex labels in this graph.
 * @param <E> the type of edge labels in this graph.
 */
public interface DirectedPseudograph<V, E> extends Pseudograph<V, E>, DirectedMultigraph<V, E> {

    /**
     *
     * @return an identical copy of the directed pseudograph
     */
    @Override
    DirectedPseudograph<V, E> copy();

    @Override
    DirectedPseudograph<V, E> subgraph(VertexSet vertexSet);

    @Override
    DirectedPseudograph<V, E> subgraph(Collection<Edge> edges);

}
