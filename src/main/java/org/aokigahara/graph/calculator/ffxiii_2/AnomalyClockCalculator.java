package org.aokigahara.graph.calculator.ffxiii_2;

import java.util.List;

import org.aokigahara.graph.GraphAnalysis;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * TODO
 * 
 * @author rieko
 */
public class AnomalyClockCalculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DefaultDirectedGraph<ClockNode, DefaultEdge> g = new DefaultDirectedGraph<ClockNode, DefaultEdge>(
				DefaultEdge.class);
		ClockNode[] nodes = new ClockNode[args.length];

		// add vertices
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new ClockNode(i, Integer.parseInt(args[i]));
			g.addVertex(nodes[i]);
		}

		// add edges. required in separate loop because jgrapht
		for (int i = 0; i < nodes.length; i++) {
			ClockNode currentNode = nodes[i];
			int counterClockwiseMoveIndex = (currentNode.getIndex()
					- currentNode.getValue() + nodes.length)
					% nodes.length;
			int clockwiseMoveIndex = (currentNode.getIndex() + currentNode
					.getValue()) % nodes.length;
			g.addEdge(currentNode, nodes[counterClockwiseMoveIndex]);
			if (counterClockwiseMoveIndex != clockwiseMoveIndex)
				g.addEdge(currentNode, nodes[clockwiseMoveIndex]);
		}

		List<ClockNode> hamCycle = GraphAnalysis.hamiltonianCycle(g);
		for (ClockNode node : hamCycle)
			System.out.print(node.getIndex() + " ");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
