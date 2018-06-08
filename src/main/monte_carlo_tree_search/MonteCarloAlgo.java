package main.monte_carlo_tree_search;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.Gomoku;
import utils.Utils;

public class MonteCarloAlgo {
	private static final int WIN_VALUE = 10;
	public static int[] startMonteCarlo(int[][] board) {
		long end  = System.currentTimeMillis()+4000;
		Node root = new Node(board);
		while(System.currentTimeMillis()<end) {
			Node promisingNode = selectPromisingNode(root);
			if (promisingNode.getEnding()==0) {
				expandNode(promisingNode);
			}
			
			Node nodeToExplore = promisingNode;
			if (promisingNode.getChildren().size()>0) {
				nodeToExplore = promisingNode.getRandomChild();
			}
			simulateRandomPlayout(nodeToExplore);
			backPropogation(nodeToExplore);
		}
		
		Node winner = root.getChildWithMaxScore();
		return winner.getNextMove();
		
	}
	
	private static void expandNode(Node node) {
		List<int[]> possibleMoves = Utils.findPossibleMoves(node.getBoard());
		for (int[] move : possibleMoves) {
			node.addChild(new Node(node,move));
		}
	}
	
	private static Node selectPromisingNode(Node rootNode) {
	    Node node = rootNode;
	    while (node.getChildren().size() != 0) {
	        node = findBestNodeWithUCT(node);
	    }
	    return node;
	}
	private static Node findBestNodeWithUCT(Node node) {
        int parentVisit = node.getVisits();
        return Collections.max(
          node.getChildren(),
          Comparator.comparing(c -> Utils.calcUTCValue(parentVisit, 
            c.getWins(), c.getVisits())));
    }

	
	// backProp and simulateRandom to do..
	private static void backPropogation(Node leaf) {
	    Node tempNode = leaf;
	    boolean mark=false;
	    while (tempNode != null) {
	    	tempNode.incrementVisits();
	    	if(mark) {
	    		tempNode.incrementWinScore(WIN_VALUE);
	    	}
	     
	        if (tempNode.getEnding()==2) {
	        	tempNode.incrementWinScore(WIN_VALUE);
	        	mark=true;
	        }else if (tempNode.getEnding()==1) {
	        	tempNode.setWins(Integer.MIN_VALUE);
	        	mark =false;
	        }
	        tempNode = tempNode.getParent();
	    }
	}
	
	private static int simulateRandomPlayout(Node node) {
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
	    node.setEnding(boardStatus);
	    return boardStatus;
	}
	
}
