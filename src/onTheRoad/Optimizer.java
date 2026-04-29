package onTheRoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Class whose main method reads in description of graph and trip requests,
 * and then returns shortest paths (according to distance or time) from one
 * given vertex to another.  The input file is given by a command line argument.
 * @author Deniz Tanaci
 * @date April 29
 */

public class Optimizer {
	public static void main(String[] args) {
		//System.out.println(args[0]);
		if (args.length < 1) {
			System.out.println("fix input file");
			return;
		}
 
		FileParser fp = new FileParser(args[0]);
		List<String> vertices = fp.getVertices();
		List<TripRequest> trips = fp.getTrips();
 
		EdgeWeightedDigraph distanceGraph = fp.makeGraph(true);
		EdgeWeightedDigraph timeGraph = fp.makeGraph(false);
 
		if (!GraphAlgorithms.isStronglyConnected(distanceGraph)) {
			System.out.println("Disconnected Map");
			return;
		}
 
		// Process each trip request in the order it appeared in the input.
		for (TripRequest trip : trips) {
			int start = trip.getStart();
			int end = trip.getEnd();
			boolean isDistance = trip.isDistance();
			EdgeWeightedDigraph g;
			if (isDistance) {
				System.out.println("Shortest distance from " + vertices.get(start) + " to " + vertices.get(end));
				g=distanceGraph;
			} else {
				System.out.println("Shortest driving time from " + vertices.get(start) + " to " + vertices.get(end));
				g=timeGraph;
			}
 
			ArrayList<DirectedEdge> path = GraphAlgorithms.getShortestPath(g, start, end);
			GraphAlgorithms.printShortestPath(path, isDistance, vertices);
 
			// Blank line between trip reports.
			System.out.println();

	}
}}
