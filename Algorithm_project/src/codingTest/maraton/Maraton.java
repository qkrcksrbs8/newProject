package codingTest.maraton;

import java.util.Arrays;

class Solution {
	
    public String solution(String[] participant, String[] completion) {
    	
        Arrays.sort(participant);	//마라톤 참가 선수 정렬
        Arrays.sort(completion);	//마라톤 완주 선수 정렬
        
        //-------------------------
        //마라톤 완주 선수 수 만큼 반복문
        //선수 목록이 정렬되어 있어서
        //순서가 일치하지 않은 배열이 오면
        //완주하지 못한 선수
        //------------------------
        for(int i=0; i<completion.length; i++){
        	
            if(!participant[i].equals(completion[i])) {
            	
                return participant[i];
                
            }
            
        }
        
        return participant[participant.length-1];
        
    }
    
}