package codingTest.Knum;

import java.util.Arrays;

class Solution {
	
    public int[] solution(int[] array, int[][] commands) {
    
    	//���� ���� commands�迭
    	int[] answer = new int[commands.length];
        
        for(int i = 0; i < commands.length; ++i){
            
            int[] temp = Arrays.copyOfRange(array,commands[i][0]-1, commands[i][1]);//�迭 n��° ���� n��° ���� ī��
            Arrays.sort(temp);//����
            answer[i] = temp[commands[i][2]-1];//���
            
        }//for
        
        return answer;
    }
}