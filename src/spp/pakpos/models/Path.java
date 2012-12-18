/**
 * Represent Path of PakPos. Path contains sequence of node and length
 */
package spp.pakpos.models;

import java.util.ArrayList;

public class Path {
	private ArrayList<Integer> path;
	private int length;
	
	public Path(){
		path = new ArrayList<Integer>();
		length = 0;
	}
	
	public Path(ArrayList<Integer> path, int length){
		this.path = path;
		this.length = length;
	}
	
	public int getLength(){
		return length;
	}
	
	public void increaseLength(int newLength){
		length += newLength;
	}
	
	public void increasePath(int newPath){
		path.add(newPath);
	}
	
	public ArrayList<Integer> getPath(){
		return path;
	}
	 
	public String toString(){
		return String.format("path: %s, jarak: %s", path.toString(), length);
	}
	
	public static void main(String args[]){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		Path path = new Path(list, 50);
		System.out.println(path);
	}
}
