package codingTest.clothes;

import java.util.HashMap;

class Solution {
	
    public int solution(String[][] clothes) {
    	
//    	String[][] clothes = {{"yellow_hat", "headgear"}
//		, {"blue_sunglasses", "eyewear"}
//		, {"green_turban", "headgear"}};
        
    	HashMap<String, Integer> hm = new HashMap<String, Integer>();//2중 배열의 값을 담을 HashMap
 
        //------------------------------
        //2중 배열의 수 만큼 반복문 실행
        //HashMap에 있는 value의 중복 체크
    	//중복 여부에 따라 분류
    	//착용할 수 있는 조합 수 반환
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