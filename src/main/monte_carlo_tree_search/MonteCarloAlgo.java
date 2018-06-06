package main.monte_carlo_tree_search;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import main.Gomoku;
import utils.Utils;

public class MonteCarloAlgo {
	public static void expandNode(Node node) {
		List<int[]> possibleMoves = Utils.findPossibleMoves(node.getBoard());
		for (int[] is : possibleMoves) {
			node.addChild(new Node(node,is));
		}
	}
	
	public Node selectPromisingNode(Node rootNode) {
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
	
	// backProp and simulateRandom to do..
	private void backPropogation(Node leaf, int playerNo) {
	    Node tempNode = leaf;
	    boolean mark=false;
	    while (tempNode != null) {
	    	if(mark) {
	    		tempNode.setWins(tempNode.getWins()+1);
	    	}
	        tempNode.setVisits(tempNode.getVisits()+1);
	        if (Gomoku.evaluate(tempNode.getBoard())==2) {
	        	tempNode.setWins(tempNode.getWins()+1);
	        	mark=true;
	        }
	        tempNode = tempNode.getParent();
	    }
	}
	
	private int simulateRandomPlayout(Node node) {
	    Node tempNode = new Node(node.getBoard());
	    int boardStatus =Gomoku.evaluate(tempNode.getBoard());
	    if (boardStatus == 1) {
	        tempNode.getParent().setWins(Integer.MIN_VALUE);
	        return boardStatus;
	    }
	    int p=1;
	    List<int[]> Moves = Utils.findPossibleMoves(tempNode.getBoard());
	    while (boardStatus == 0) {
	        int[] m= Utils.generateNextMoveFromList(Moves);
	        int[][]board2=tempNode.getBoard();
	        board2[m[0]][m[1]]=p;
	        tempNode.setBoard(board2);
	     
	        boardStatus = Gomoku.evaluate(tempNode.getBoard());
	        p=3-p;
	    }
	    return boardStatus;
	}
	
}
