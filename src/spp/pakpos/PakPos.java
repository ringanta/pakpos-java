package spp.pakpos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;


public class PakPos {
	public static int tujuan;
	public static int awal;
	public static ExecutorService threadPool;
	public static AtomicInteger threadCount;
	private static List<Future<?>> futures = new ArrayList<Future<?>>();
	
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
		Future<?> result = threadPool.submit(task);
		futures.add(result);
		try{
			result.get();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean isFinish(){
		return threadPool.isTerminated();
	}
	
	public static ArrayList<Integer> cloneVisitedList(ArrayList<Integer> visited){
		return new ArrayList<Integer>(visited);
	}
}
