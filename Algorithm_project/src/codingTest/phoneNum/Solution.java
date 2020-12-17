package codingTest.phoneNum;

import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
      
        Arrays.parallelSort(phone_book);//��ȭ��ȣ ����

		//��ȭ��ȣ ������ ���� - 1 ��ŭ �ݺ��� ����
		//i��° �迭 ���ڸ� ���� i+1��° �迭 ���� ��ġ�ϴ��� ��
		//��ġ�ϸ� ����
		for(int i = 0; i < phone_book.length-1; ++i) {
			
			if(phone_book[i+1].startsWith(phone_book[i])) {
				return false;
			}//if
			
		}//for
		
		return true;
        
    }
}