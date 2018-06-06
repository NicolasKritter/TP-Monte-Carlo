package main.players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.Gomoku;
import main.Player;
import main.monte_carlo_tree_search.MonteCarloAlgo;
import utils.Utils;

public class MonteCarloPlayer extends Player{

	
	
	public MonteCarloPlayer(String name, int id) {
		super(name, id);

		
	}


	

	@Override
	public int[] getMove(int[][] board) {
	
		return MonteCarloAlgo.startMonteCarlo(board);
	}
	
	
	
}
