package codingTest.nodeinfo;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Solution solution = new Solution();

		int[][] arrayNum = {
				
				 {5,3}
				,{11,5}
				,{13,3}
				,{3,5}
				,{6,1}
				,{1,3}
				,{8,6}
				,{7,2}
				,{2,2}
				
		};
		
		arrayNum = solution.solution(arrayNum);
		
		int arrayLength = arrayNum.length;
		
		for(int i = 0; i < arrayLength; i++) {
			
			System.out.print("[");
			
			for(int j = 0; j < arrayNum[i].length; j++) {
				
				System.out.print(arrayNum[i][j]);
				
			};//for
			
			System.out.print("]");
			
		};//for
		
	}

}
