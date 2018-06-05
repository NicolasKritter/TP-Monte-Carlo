package main.monte_carlo_tree_search;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import utils.Utils;

public class MonteCarloAlgo {
	public static void expandNode(Node node) {
		List<int[]> possibleMoves = Utils.findPossibleMoves(node.getBoard());
		
		for (int[] is : possibleMoves) {
			node.addChild(new Node(node,is));
		}
	}
	
	private Node selectPromisingNode(Node rootNode) {
	    Node node = rootNode;
	    while (node.getChildren().size() != 0) {
	        node = findBestNodeWithUCT(node);
	    }
	    return node;
	}
	public static Node findBestNodeWithUCT(Node node) {
        int parentVisit = node.getVisits();
        return Collections.max(
          node.getChildren(),
          Comparator.comparing(c -> calcUTCValue(parentVisit, 
            c.getWins(), c.getVisits())));
    }
	public static double calcUTCValue(int totalParentSimulation,double winScore,int nbSimulations) {
		if (nbSimulations==0) {
			return Integer.MAX_VALUE;
		}
		return (winScore/(double)nbSimulations)+1.41*Math.sqrt((Math.log(totalParentSimulation)/(double) nbSimulations));
	}
	
}
