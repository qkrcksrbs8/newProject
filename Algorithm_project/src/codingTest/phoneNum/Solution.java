package codingTest.phoneNum;

import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
      
        Arrays.parallelSort(phone_book);//전화번호 정렬

		//전화번호 마지막 개수 - 1 만큼 반복문 실행
		//i번째 배열 앞자리 값과 i+1번째 배열 값이 일치하는지 비교
		//일치하면 리턴
		for(int i = 0; i < phone_book.length-1; ++i) {
			
			if(phone_book[i+1].startsWith(phone_book[i])) {
				return false;
			}//if
			
		}//for
		
		return true;
        
    }
}