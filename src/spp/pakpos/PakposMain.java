package spp.pakpos;

import java.util.ArrayList;

import spp.pakpos.controllers.ExplorerTask;
import spp.pakpos.models.PakPosPath;
import spp.pakpos.models.PosArea;

public class PakposMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// set value of PosArea
		// This is representation of the graph in form of matrix adjacency
		int temp[][] = {{0, 3, 2, 5}, {3, 0, 0, 1}, {2, 0, 0, 1}, {5, 1, 1, 0}};
		
		for (int i=0; i<temp.length; i++){
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int j=0; j<temp[i].length; j++){
				row.add(temp[i][j]);
			}
			PosArea.append(row);
		}
		System.out.println(PosArea.matrixToString());
		
		// Set Destination to PakPos
		PakPos.tujuan = 3;
		
		// Start daemon
		PakPos.addTask(new ExplorerTask(new ArrayList<Integer>(), 0, 0));
		
		try{
			System.out.println(Thread.currentThread().getName() + " is joining");
			while (!PakPos.threadPool.isShutdown())
				Thread.currentThread().join(100);
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " is going to finish");
		PakPosPath.printPath();
	}

}
