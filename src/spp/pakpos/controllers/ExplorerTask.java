package spp.pakpos.controllers;

import java.util.ArrayList;

import spp.pakpos.PakPos;
import spp.pakpos.models.PakPosPath;
import spp.pakpos.models.Path;
import spp.pakpos.models.PosArea;

public class ExplorerTask implements Runnable {
	private ArrayList<Integer> visitedNodes;
	private int node;
	private int totalDistance;
	
	public ExplorerTask(ArrayList<Integer> visited, int currentNode, int distance){
		visitedNodes = visited;
		node = currentNode;
		totalDistance = distance;
	}
	
	@Override
	public void run() {
		int inc = PakPos.threadCount.incrementAndGet();
		System.out.println(Thread.currentThread().getName() + ": starting, Number of Task = " + inc);
		
		if (!PakPos.isVisited(visitedNodes, node)){
			visitedNodes.add(node);
			System.out.println(Thread.currentThread().getName() + ": Visited nodes = " +visitedNodes.toString());
			
			if (PakPos.isArrive(node)){
				Path validOne = new Path(visitedNodes, totalDistance);
				PakPosPath.savePath(validOne);
				System.out.println(Thread.currentThread().getName() + ": saved its works, " + validOne.toString());
			} else {
				ArrayList<Integer> neighbours = PosArea.getNeighbours(node);
				System.out.println(Thread.currentThread().getName() + ": detects neighbour, " + neighbours.toString());
				
				int adjacencyFlag;
				for (int i=0; i<neighbours.size(); i++){
					adjacencyFlag = neighbours.get(i);
					if (PosArea.isNeighbour(adjacencyFlag)){
						System.out.println(Thread.currentThread().getName() + ": submit new task");
						
						// Submit new task to ExecutorService
						PakPos.addTask(
							new ExplorerTask(new ArrayList<Integer>(visitedNodes), i, totalDistance + adjacencyFlag)
						);
					} else {
						System.out.println(String.format( "%s: detects invalid neighbour, cell(%s,%s) = %s", Thread.currentThread().getName(), node, i, adjacencyFlag ));
						System.out.println(Thread.currentThread().getName() + ": printed its works, " + visitedNodes);
					}
				}
			}
		} else {
			System.out.println(Thread.currentThread().getName() + " hit visited node");
		}
		PakPos.threadCount.decrementAndGet();
		System.out.println(Thread.currentThread().getName() + ": has finished, Number of task = " + PakPos.threadCount.get());
		
		if (PakPos.threadCount.get() == 0){
			System.out.println(Thread.currentThread().getName() + " is trying to shutdown the pool");
			PakPos.threadPool.shutdownNow();
		}
	}

}
