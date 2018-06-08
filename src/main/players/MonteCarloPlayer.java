package main.players;
import main.Player;
import main.monte_carlo_tree_search.MonteCarloAlgo;

public class MonteCarloPlayer extends Player{
	
	public MonteCarloPlayer(String name, int id) {
		super(name, id);		
	}

	@Override
	public int[] getMove(int[][] board) {
	
		return MonteCarloAlgo.startMonteCarlo(board);
	}
	
	
	
}
