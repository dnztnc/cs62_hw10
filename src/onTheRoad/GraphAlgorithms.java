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
		EdgeWeightedDigraph reversed = new EdgeWeightedDigraph(g.V());
		for (int v = 0; v < g.V(); v++) {
			for (DirectedEdge e : g.adj(v)) {
				reversed.addEdge(new DirectedEdge(e.to(), e.from(), e.weight()));
			}
		}
		return reversed;
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
		g.reset();
		//create a queue for breadth-first search
		Deque<Integer> queue = new ArrayDeque<Integer>();
		//mark start as visited and add it to the queue
		g.visit(new DirectedEdge(start, start, 0), 0);
		queue.add(start);
		//while the queue is not empty, remove the first vertex and add all of its unvisited neighbors to the queue
		while (!queue.isEmpty()) {
			int v = queue.remove();
			for (DirectedEdge e : g.adj(v)) {
				if (!g.isVisited(e.to())) {
					g.visit(e, g.getDist(v) + e.weight());
					queue.add(e.to());
				}}}	
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
		if (g.V() == 0) {
			return true;
		}
		int start = 0;
		breadthFirstSearch(g, start);
		for (int v = 0; v < g.V(); v++) {
			if (!g.isVisited(v)) {
				return false;
			}
		}
		// now reverse the graph, do another breadth-first search,
		// and make sure all visited again. If not, return false
		EdgeWeightedDigraph reversed = graphEdgeReversal(g);
		breadthFirstSearch(reversed, start);
		for (int v = 0; v < reversed.V(); v++) {
			if (!reversed.isVisited(v)) {
				return false;
			}
		}
		return true;
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
		g.reset();
		HashMap<Integer, ArrayList<DirectedEdge>> myhashmap = new HashMap<Integer, ArrayList<DirectedEdge>>();
		IndexMinPQ<Double> pq = new IndexMinPQ<Double>(g.V());
		g.visit(new DirectedEdge(s, s, 0), 0);
		pq.insert(s, 0.0);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (DirectedEdge e : g.adj(v)) {
				if (!g.isVisited(e.to()) || g.getDist(e.to()) > g.getDist(v) + e.weight()) {
					g.visit(e, g.getDist(v) + e.weight());
					if (pq.contains(e.to())) {
						pq.changeKey(e.to(), g.getDist(e.to()));
					} else {
						pq.insert(e.to(), g.getDist(e.to()));
					}
				}
			}
		}
		for (int v = 0; v < g.V(); v++) {
			ArrayList<DirectedEdge> path = new ArrayList<DirectedEdge>();
			if (g.isVisited(v)) {
				for (DirectedEdge e = g.getEdgeTo(v); e != null && e.from() != e.to(); e = g.getEdgeTo(e.from())) {
					path.add(0, e);
				}
			}
			myhashmap.put(v, path);
		}
		return myhashmap;


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
		return dijkstra(g, start).get(end);
	}

	/**
	 * Using the output from getShortestPath, print the shortest path
	 * between two nodes
	 * 
	 * @param path shortest path from start to end
	 * @param isDistance prints it based on distance (true) or time (false)
	 * 
	 * Example:
	 * (isDistance = true)
	 *  Begin at Irvine
		Continue to Chino Hills (1.0)
		Continue to Claremont (2.0)
		Total distance: 3.0 miles

		(isDistance = false)
		Begin at Irvine
		Continue to Chino Hills (1 hours 0 minutes 0.0 seconds)
		Continue to Claremont (2 hours 0 minutes 0.0 seconds)
		Total time: 3 hours 0 minutes 0.0 seconds
	 */
	public static void printShortestPath(ArrayList<DirectedEdge> path, boolean isDistance, List<String> vertices) {
		// Hint: Look into TestGraphs for format of printout
		if (path == null) {
			System.out.println("No path exists.");
			return;
		}
		double total = 0;
		for (int i = 0; i < path.size(); i++) {
			DirectedEdge e = path.get(i);
			if (i == 0) {
				System.out.println("Begin at " + vertices.get(e.from()));

			}
			
				System.out.print("Continue to " + vertices.get(e.to()));
				if (isDistance) {
					System.out.println(" (" + e.weight() + ")");
				} else {
					System.out.println(" (" + hoursToHMS(e.weight()) + ")");
				}
			total += e.weight();
	}
		if (isDistance) {
			System.out.println("Total distance: " + total + " miles");
		} else {
			System.out.println("Total time: " + hoursToHMS(total));
		}
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
		double hours = Math.floor(rawhours);
		double minutes = Math.floor((rawhours - hours) * 60);
		double seconds = Math.round(((rawhours - hours) * 60 - minutes) * 60 * 10) / 10.0;
		if (hours==0) {
			if (minutes == 0) {
				return (seconds + " secs");
			}  
			else {
				return (minutes + " mins " + seconds + " secs");}
		}
		return (hours+ " hrs " + minutes + " mins " + seconds + " secs");
}
}