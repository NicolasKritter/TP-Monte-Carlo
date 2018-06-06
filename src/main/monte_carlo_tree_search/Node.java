package main.monte_carlo_tree_search;
import java.util.LinkedList;
import java.util.List;

import utils.Utils;

public class Node {
	private Node parent;
	private List<Node> children;
	private int[][] board;
	private int visits =0 ;
	private int wins;
	private int[] nextMove;
	private int ending = 0;
	
	public int getVisits() {
		return visits;
	}


	public void setVisits(int visits) {
		this.visits = visits;
	}


	public int getWins() {
		return wins;
	}


	public void setWins(int wins) {
		this.wins = wins;
	}

	
	public Node(int [][] board) {
		this.board = Utils.copyBoard(board);
		children = new LinkedList<Node>();
		
	}
	
	
	public Node(Node parent,int[] move) {
		this.parent= parent;
		this.nextMove = move;
		this.board = Utils.copyBoard(parent.getBoard());
		this.board[move[0]][move[1]] = 2;
		children = new LinkedList<Node>();
	}
	
	public int getEnding() {
		return ending;
	}
	public void setEnding(int ending) {
		this.ending = ending;
	}
	public int[] getNextMove() {
		return this.nextMove;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getParent() {
		return this.parent;
	}
	
	public int[][] getBoard(){
		return board;
	}
		
	public List<Node> getChildren(){
		return children;
	}
	public void addChild(Node node) {
		children.add(node);
	}
	
	public Node getRandomeChild() {
		int index = (int) Math.round(Math.random()*this.children.size());
		return this.children.get(index);
	}

	public void setBoard(int[][] board2) {
		this.board=board2;
		
	}
	public Node getChildWithMaxScore() {
		List<Node>children=this.getChildren();
		Node M=new Node(this.getBoard());
		for(Node c:children)
			if(c.getWins()>M.getWins())
					M=c;
		return(M);
	}
}
