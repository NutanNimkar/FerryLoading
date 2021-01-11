import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Main {
	    
	//Variable declaration
	static int bestK = -1;
 	static  int[] bestX;
 	static  int[] currX;
	static  boolean[][] visited;

	static	int L, n;
	static 	int totalK, spAvaR;
	
	static ArrayList<Integer> lengths = new ArrayList<Integer>();
	
	//method initialization 
	public static void BackTrackSolve(int currK, int currS) {
		totalK = 0;
		for(int z = 0; z < currK; z++){
			totalK += lengths.get(z);
		}

		spAvaR = L - (totalK - (L - currS));//rightside space available 
		
		if (currK > bestK){
			bestK = currK;

			for (int i=0; i< currX.length; i++) {
				bestX[i] = currX[i]; 
			}
		}
		if(currK < n){
			if(((lengths.get(currK) <= spAvaR)&&(visited[currK+1][currS] == false))){
				
				currX[currK] = 0;
				BackTrackSolve(currK+1,currS);
				visited[currK+1][currS]=true;

			}
			if((lengths.get(currK) <= currS)&&(visited[currK+1][(currS-lengths.get(currK))] == false)){
				
				currX[currK] = 1;
				int newS = currS-lengths.get(currK);
				BackTrackSolve(currK+1, newS);
				visited[currK+1][newS] = true;
			
			}
			
       		//right side space and value check 
			// if(((lengths.get(currK) <= spAvaR)&&(visited[currK+1][currS] == false))){
				
			// 	currX[currK] = 0;
			// 	BackTrackSolve(currK+1,currS);
			// 	visited[currK+1][currS]=true;

			// }
		}

	 }

	public static void main(String[] args) throws FileNotFoundException {
		
		//In order to run this file in online judge, I had to comment out the lines 65-67 and convert the Scanner "sc" to Scanner "scan"

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		File file = new File(input);
		Scanner scan = new Scanner(file);

	

		try {
			int cases = Integer.parseInt(scan.nextLine());

			for(int i = 0; i < cases; i++) {
				scan.nextLine();

				L = 100*(Integer.parseInt(scan.nextLine()));

				lengths = new ArrayList<Integer>();

				int count = 0;
				while(scan.hasNextLine()) {
					String lineValue = scan.nextLine();

					if(Integer.parseInt(lineValue) == 0) {
						n = count;
						break;
					} else {
						count += 1;

						lengths.add(Integer.parseInt(lineValue));
					}
				}
				//variable initialization
				bestK = -1;

				bestX = new int[n];
				currX = new int[n];
				visited = new boolean[n+1][L+1];

				//method call
				BackTrackSolve(0,L);

				//Output Representation
				System.out.println(bestK);

				for(int j = 0; j < bestK; j++){
					if(bestX[j] == 1){
						System.out.println("port");
					}else{
						System.out.println("starboard");
					}
				}
				if(i!=(cases-1)){
					System.out.println();
				}

			}
			//printing the arraylist to check its content
			// for (int element: lengths) {
			// 	System.out.println(element);
			// }	

		} catch (Exception e) {
			e.printStackTrace();
		}

	}//main
}//ferryloadingbigtable

