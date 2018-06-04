package main.monte_carlo_tree_search;

import java.util.List;

import utils.Utils;

public class MonteCarloAlgo {
	public static void expandNode(Node node) {
		List<int[]> possibleMoves = Utils.findPossibleMoves(node.getBoard());
		
	}
	
	public static Node selectPromisingNode(Node node) {
		Node res = node;
	}
	public static double calcUTCValue(int totalParentSimulation,double winScore,int nbSimulations) {
		if (nbSimulations==0) {
			return Integer.MAX_VALUE;
		}
		return (winScore/(double)nbSimulations)+1.41*Math.sqrt((Math.log(totalParentSimulation)/(double) nbSimulations));
	}
	
	public static Node findBestNodeByUTC(Node node) {
		
	}
}
