package codingTest.clothes;

import java.util.HashMap;

class Solution {
	
    public int solution(String[][] clothes) {
    	
//    	String[][] clothes = {{"yellow_hat", "headgear"}
//		, {"blue_sunglasses", "eyewear"}
//		, {"green_turban", "headgear"}};
        
    	HashMap<String, Integer> hm = new HashMap<String, Integer>();//2�� �迭�� ���� ���� HashMap
 
        //------------------------------
        //2�� �迭�� �� ��ŭ �ݺ��� ����
        //HashMap�� �ִ� value�� �ߺ� üũ
    	//�ߺ� ���ο� ���� �з�
    	//������ �� �ִ� ���� �� ��ȯ
    	//------------------------------
        for (int i = 0; i < clothes.length; i++) {
        	
            if (hm.containsKey(clothes[i][1])) {

                hm.replace(clothes[i][1], hm.get(clothes[i][1])+1);
                
            }//if
            	
            else {

                hm.put(clothes[i][1], 1);
                
            }//else
            	 
        }//for 
 
        int answer = 1;
        for (int value : hm.values()) {
        	 
            answer*=(value+1);
            
        }//for
        
        answer-=1;
 
        return answer;
    }
    
}