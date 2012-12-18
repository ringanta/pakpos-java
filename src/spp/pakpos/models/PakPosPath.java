package spp.pakpos.models;

import java.util.ArrayList;

public class PakPosPath {
	private static ArrayList<Path> validPath;
	
	static{
		validPath = new ArrayList<Path>();
	}
	
	public static void savePath(Path path){
		validPath.add(path);
	}
	
	public static void printPath(){
		System.out.println(Thread.currentThread().getName() + " is printing all valid path");
		for (int i=0; i<validPath.size(); i++){
			System.out.println(validPath.get(i));
		}
	}
}
