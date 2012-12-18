/**
 * Static class to maintain threading of the program
 * This class contains:
 *  1. Entity which is responsible for executing searching task (ExecutorService)
 *  2. Entity to track number of submitted task (AtomicInteger)
 *  3. Entity which is collect all result of executing a task (List<Future<?>>)
 */
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
	private static List<Future<?>> futures;
	
	static{
		threadPool = Executors.newFixedThreadPool(100);
		threadCount = new AtomicInteger();
		futures = new ArrayList<Future<?>>();
	}
	
	/**
	 * Check whether current node has been visited or not
	 * @param visited list of visited node
	 * @param node current node
	 * @return true if current node has been visited
	 */
	public static boolean isVisited(List<Integer> visited, int node){
		return visited.contains(node);
	}
	
	/**
	 * Check whether current node has arrived at destination
	 * @param node current position
	 * @return true if current node equal tujuan
	 */
	public static boolean isArrive(int node){
		return tujuan == node;
	}
	
	/**
	 * Submit new task to be executed
	 * @param task new task
	 */
	public static void addTask(Runnable task){
		Future<?> result = threadPool.submit(task);
		futures.add(result);
		try{
			result.get();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Clone list of visited node
	 * @param visited list of visited node
	 * @return clonned visited node
	 */
	public static ArrayList<Integer> cloneVisitedList(ArrayList<Integer> visited){
		return new ArrayList<Integer>(visited);
	}
}
