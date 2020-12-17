package codingTest.maraton;

import java.util.Arrays;

class Solution {
	
    public String solution(String[] participant, String[] completion) {
    	
        Arrays.sort(participant);	//������ ���� ���� ����
        Arrays.sort(completion);	//������ ���� ���� ����
        
        //-------------------------
        //������ ���� ���� �� ��ŭ �ݺ���
        //���� ����� ���ĵǾ� �־
        //������ ��ġ���� ���� �迭�� ����
        //�������� ���� ����
        //------------------------
        for(int i=0; i<completion.length; i++){
        	
            if(!participant[i].equals(completion[i])) {
            	
                return participant[i];
                
            }
            
        }
        
        return participant[participant.length-1];
        
    }
    
}