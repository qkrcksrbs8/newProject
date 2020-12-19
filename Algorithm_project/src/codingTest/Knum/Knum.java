package codingTest.Knum;

import java.util.Arrays;

class Solution {
	
    public int[] solution(int[] array, int[][] commands) {
    
    	//전달 받은 commands배열
    	int[] answer = new int[commands.length];
        
        for(int i = 0; i < commands.length; ++i){
            
            int[] temp = Arrays.copyOfRange(array,commands[i][0]-1, commands[i][1]);//배열 n번째 부터 n번째 까지 카피
            Arrays.sort(temp);//정렬
            answer[i] = temp[commands[i][2]-1];//결과
            
        }//for
        
        return answer;
    }
}