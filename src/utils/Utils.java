package utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import main.Gomoku;

public class Utils {
	
	
	public static int[][] copyBoard(int[][] board){
		int len = board[0].length;
		int[][] res = new int[board.length][len];
		for (int i=0;i<board.length;i++) {
			res[i] = Arrays.copyOf(board[i], len);
		}
		return res;
	}
	
	public static  List<int[]> findPossibleMoves(int[][] board){
		List<int[]> res = new ArrayList<int[]>();
		for (int i=0;i<board.length;i++) {
			for (int j=0;j<board[0].length;j++) {
				int [] coord = {i,j};
				if (Gomoku.valid(coord, board)) {
					res.add(coord);
				}
				
			}
		}
		
		return res;
		
	}
	
	
	
	public  static int[] generateNextMoveFromList(List<int[]> list) {
	int index = (int) Math.round(Math.random()*list.size());
	int[] res = list.get(index);
	list.remove(index);
	return res;
	}
	
}
