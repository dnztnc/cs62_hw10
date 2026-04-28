package onTheRoad;

/**
 * Common algorithms for Graphs. 
 * They all assume working with a EdgeWeightedDirected graph.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class GraphAlgorithms {

	/**
	 * Reverses the edges of a graph
	 * 
	 * @param g
	 *            edge weighted directed graph
	 * @return graph like g except all edges are reversed
	 */
	public static EdgeWeightedDigraph graphEdgeReversal(EdgeWeightedDigraph g) {
		// FIX THIS!
		return null;
	}

	/**
	 * Performs breadth-first search of g from vertex start.
	 * 
	 * @param g
	 *            directed edge weighted graph
	 * @param start
	 *            index of starting vertex for search
	 */
	public static void breadthFirstSearch(EdgeWeightedDigraph g, int start) {
		//reset graph so that no vertex starts marked as visited
		// FIX THIS! 		
	}

	/**
	 * Calculates whether the graph is strongly connected
	 * 
	 * @param g
	 *            directed edge weighted graph
	 * @return whether graph g is strongly connected.
	 */
	public static boolean isStronglyConnected(EdgeWeightedDigraph g) {
		// do breadth-first search from start and make sure all vertices
		// have been visited. If not, return false.
		
		
		// now reverse the graph, do another breadth-first search,
		// and make sure all visited again. If not, return false

		//FIX THIS!
		return false;
	}

	/**
	 * Runs Dijkstra's algorithm on path to calculate the shortest path from
	 * starting vertex to every other vertex of the graph.
	 * 
	 * @param g
	 *            directed edge weighted graph
	 * @param s
	 *            starting vertex
	 * @return a hashmap where a key-value pair <i, path_i> corresponds to the i-th
	 *         vertex and path_i is an arraylist that contains the edges along the
	 *         shortest path from s to i.
	 */
	public static HashMap<Integer, ArrayList<DirectedEdge>> dijkstra(EdgeWeightedDigraph g, int s) {
		//reset graph
		
		
		// FIX THIS!
		return null;
	}

	/**
	 * Computes shortest path from start to end using Dijkstra's algorithm.
	 *
	 * @param g
	 *            directed graph
	 * @param start
	 *            starting node in search for shortest path
	 * @param end
	 *            ending node in search for shortest path
	 * @return a list of edges in that shortest path in correct order
	 */
	public static ArrayList<DirectedEdge> getShortestPath(EdgeWeightedDigraph g, int start, int end) {
		// run dijkstra and create a new ArrayList with edges running from start to end.

		// FIX THIS!
		return null;
	}

	/**
	 * Using the output from getShortestPath, print the shortest path
	 * between two nodes
	 * 
	 * @param path shortest path from start to end
	 * @param isDistance prints it based on distance (true) or time (false)
	 */
	public static void printShortestPath(ArrayList<DirectedEdge> path, boolean isDistance, List<String> vertices) {
		// FIX THIS!
		// Hint: Look into TestGraphs for format of printout
	}

	/**
	 * Converts hours (in decimal) to hours, minutes, and seconds
	 * 
	 * @param rawhours
	 *            time elapsed
	 * @return Equivalent of rawhours in hours, minutes, and seconds (to nearest
	 *         10th of a second)
	 */
	private static String hoursToHMS(double rawhours) {
		// FIX THIS!
		return null;
	}
}
