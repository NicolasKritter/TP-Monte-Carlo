package main.players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.Gomoku;
import main.Player;
import main.monte_carlo_tree_search.Node;
import main.monte_carlo_tree_search.Tree;
import utils.Utils;

public class MonteCarloPlayer extends Player{

	
	
	public MonteCarloPlayer(String name, int id) {
		super(name, id);

		
	}

	private int[] computeNextMove(int[][] board) {
		int score =0;
		int[] res = {-1,-1};
		
		int temp;
		List<int[]> todo = Utils.findPossibleMoves(board);
		for (int[] coord:todo) {
			 temp =searchVictory(board,todo,true);
			if (score<=temp) {
				res = coord;
				score = temp;
			}
		}
		return res;
	}
	private int[] computeNextMove2(int[][] board) {
		int end=4000;
		Tree tree= new Tree();
		Node rootNode = tree.getRoot();
		rootNode.setBoard(board);
		while(System.currentTimeMillis()<end) {
			
		}
		return null;
		
	}
	

	@Override
	public int[] getMove(int[][] board) {
	
		return computeNextMove(board);
	}
	
	
	
}
