package codingTest.maraton;

import java.util.Arrays;

class Solution {
	
    public String solution(String[] participant, String[] completion) {
    	
    	String[] str = {"marina", "josipa", "nikola", "vinko", "filipa"};
    	String[] str2 = {"marina", "josipa", "nikola", "filipa"};
    	String result = "";
    	
    	Arrays.sort(str);
    	Arrays.sort(str2);
    	int cnt = str.length;
    	
    	for(int i = 0; i < cnt; ++i) {
    		System.out.println("str : "+str[i]);
    		System.out.println("str : "+str2[i]);
    		if(!str[i].equals(str2[i])) {
    			result = str[i];
    			System.out.println("result : "+result);
    		}
    		
    	}//for
    	
    	
		return null;
    	
    	
    	
    	
    	
    	
//        Arrays.sort(participant);	//������ ���� ���� ����
//        Arrays.sort(completion);	//������ ���� ���� ����
//        
//        //-------------------------
//        //������ ���� ���� �� ��ŭ �ݺ���
//        //���� ����� ���ĵǾ� �־
//        //������ ��ġ���� ���� �迭�� ����
//        //�������� ���� ����
//        //------------------------
//        for(int i=0; i<completion.length; i++){
//        	
//            if(!participant[i].equals(completion[i])) {
//            	
//                return participant[i];
//                
//            }
//            
//        }
//        
//        return participant[participant.length-1];
        
    }
    
}