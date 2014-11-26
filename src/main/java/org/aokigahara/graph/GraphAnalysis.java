package org.aokigahara.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;

/**
 * TODO
 * 
 * @author rieko
 * 
 */
public final class GraphAnalysis {
	/**
	 * Traverses the graph depth-first until a valid Hamiltonian cycle is found.
	 * 
	 * @param g
	 *            the graph which to find a Hamiltonian cycle
	 * @return the first Hamiltonian cycle found or null if none exists
	 */
	public static <V, E> List<V> hamiltonianCycle(Graph<V, E> g) {
		for (V vertex : g.vertexSet()) {
			List<V> path = new ArrayList<V>(g.vertexSet().size());
			path.add(vertex);
			List<V> solution = hamiltonianCycle(g, path);
			if (solution != null)
				return solution;
		}
		return null;
	}

	private static <V, E> List<V> hamiltonianCycle(Graph<V, E> g, List<V> path) {
		if (path.size() == g.vertexSet().size())
			// valid solution
			return path;

		V currentVertex = path.get(path.size() - 1);
		Set<E> edges;
		if (g instanceof DirectedGraph)
			edges = ((DirectedGraph<V, E>) g).outgoingEdgesOf(currentVertex);
		else
			edges = g.edgesOf(currentVertex);
		for (E nextEdge : edges) {
			V nextVertex = g.getEdgeTarget(nextEdge);
			if (!path.contains(nextVertex)) {
				// if we haven't visited this vertex
				List<V> newPath = new ArrayList<V>(path);
				newPath.add(nextVertex);
				List<V> solution = hamiltonianCycle(g, newPath);
				if (solution != null)
					return solution;
			}
		}
		// null = no valid solution on this branch
		return null;
	}
}
