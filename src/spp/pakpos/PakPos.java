package spp.pakpos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class PakPos {
	public static int tujuan;
	public static ExecutorService threadPool;
	public static AtomicInteger threadCount;
	
	static{
		threadPool = Executors.newFixedThreadPool(100);
		threadCount = new AtomicInteger();
	}
	
	public static boolean isVisited(List<Integer> visited, int node){
		return visited.contains(node);
	}
	
	public static boolean isArrive(int node){
		return tujuan == node;
	}
	
	public static void addTask(Runnable task){
		threadPool.submit(task);
	}
	
	public static ArrayList<Integer> cloneVisitedList(ArrayList<Integer> visited){
		return new ArrayList<Integer>(visited);
	}
}
