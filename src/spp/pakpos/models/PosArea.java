/**
 * Representation of all nodes which may be visited by Pak Pos to deliver a letter
 * This is Map of all city in form of matrix 2 dimensions
 */
package spp.pakpos.models;

import java.util.ArrayList;

public class PosArea {
	private static ArrayList<ArrayList<Integer>> matrix; // 2 dimensional matrix in form of ArrayList
	private static int NOTNEIGHBOUR = 0;
	
	static{
		matrix = new ArrayList<ArrayList<Integer>>();
	}
	
	/**
	 * Add new row to the matrix
	 * @param row row to be added
	 */
	public static void append(ArrayList<Integer> row){
		matrix.add(row);
	}
	
	/**
	 * Set new row into specific index
	 * @param row row to be added
	 * @param index specific index
	 */
	public static void set(ArrayList<Integer> row, int index){
		matrix.set(index, row);
	}
	
	/**
	 * Get all neighbours of given node
	 * @param node given node
	 * @return ArrayList contains all neighbour
	 */
	public static ArrayList<Integer> getNeighbours(int node){
		ArrayList<Integer> neighbours = matrix.get(node);
		return neighbours;
	}
	
	public static boolean isNeighbour(int node){
		return node > NOTNEIGHBOUR;
	}
	
	/**
	 * Convert the matrix into string
	 * @return string
	 */
	public static String matrixToString(){
		StringBuffer buffer = new StringBuffer();
	
		for (int i=0; i<matrix.size(); i++){
			buffer.append(matrix.get(i)).append("\n");
		}
		return buffer.toString();
	}
}
