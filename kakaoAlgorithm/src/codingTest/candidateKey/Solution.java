package codingTest.candidateKey;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class Solution {
	
	Comparator<Integer> comp = new Comparator<Integer>() {
		
		int countBits(int n) {
			
			int ret = 0;
			while(n != 0) {
				
				if((n & 1) != 0) {
				
					++ret;//맨 끝이 1이라면
					
				}//if
				
				n = n >> 1;//하나씩 제거
				
			}//while
			
			return ret;
			
		}//countBits
		
		public int compare(Integer a, Integer b) {
			
			int x = countBits(a), y = countBits(b);
			
			//속성을 적게 갖고있는 순서대로 정렬
			if(x > y) {
				
				return 1;
			
			}else if(x < y) {
				
				return -1;
				
			}else {
				
				return 0;
				
			}//else
			
			
		}//compare
		
	};//comp
	
	//중복 값이 있는지 체크. 후보키 가능 여부
	boolean check(String[][] relation, int rowsize, int colsize, int subset) {
		
		//겹치는 값이 있는지 알아보기 위해 2중 for문
		for(int a = 0; a < rowsize - 1; ++a) {
			
			for(int b = a + 1; b < rowsize; ++b) {
			
				boolean isSame = true;
				
				for(int k = 0; k < colsize; ++k) {//subset에 해당하는 속성만 확인
					
					if((subset & 1 << k) == 0) continue;//0이랑 같다면 스킵
					
					if(relation[a][k].equals(relation[b][k]) == false) {//같은 값이 아니라면 유일성 가능
						
						isSame = false;
						break;//같다면 더이상 체크 안해도 됨
						
					}//if
					
				}//for
				
				if(isSame) return false;//여기서 리턴이 안된다면 모든 튜플이 구분 가능
				
			}//for
			
		}//for
		
		return true;
		
	};//check

	/**
	 * 후보키가 가능한 수
	 * @param relation
	 * @return
	 */
	public int solution(String[][] relation) {
		
		int answer = 0;
		int rowsize = relation.length;//relation 길이
		int colsize = relation[0].length;//column 갯수
		List<Integer> candidates = new LinkedList<Integer>();
		
		//비트형으로 표현, 자리 하나씩 이동하면서 체크
		for(int i = 1; i < 1 << colsize; ++i) {
			
			//유일성을 만족하는지 체크
			if(check(relation, rowsize, colsize, i) == true) {
				
				candidates.add(i);//추가
				
			}//if
			
		}//for
		
		//candidates : 유일성을 갖고있는 모든 데이터를 담고있음
		Collections.sort(candidates, comp);//
		
		while(candidates.size() != 0) {
			
			int n = candidates.remove(0);//확인한 데이터는 삭제
			++answer;//최소성이 만족되는 후보키
			
			for(Iterator<Integer> it = candidates.iterator(); it.hasNext();) {
				
				int c = it.next();
				
				//최소성을 만족하지 못하는 후보키
				if((n & c) == n) {
					
					it.remove();//제거
					
				}//if
				
			}//for
			
		}//while
		
		return answer;
		
	}
	
}
