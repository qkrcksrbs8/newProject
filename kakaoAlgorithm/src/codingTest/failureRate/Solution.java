package codingTest.failureRate;

import java.util.*;

public class Solution {

	class Fail {//실패율을 저장하기 위한 변수 선언
		
		int stage;
		double rate;
		
		Fail(int stage, double rate){
			
			this.stage = stage;
			this.rate = rate;
			
		}//fail
		
	}
	
	Comparator<Fail> comp = new Comparator<Fail>() {
		
		public int compare(Fail a, Fail b) {
		
			//--------------------
			//실패율이 큰게 앞에 와야함
			//--------------------
			
			if(a.rate < b.rate) {
				
				return 1;
				
			}else if(a.rate > b.rate) {
				
				return -1;
				
			}else {//실패율이 같은 경우는 작은게 앞에 오게함
				
				if(a.stage > b.stage) {
					
					return 1;
					
				}else if(a.stage < b.stage) {
					
					return -1;
					
				}else {//나머지
					
					return 0;
					
				}//else
				
			}// else
			
		}
		
	};
	
	/**
	 * 스테이지 클리어 실패율
	 * @param N
	 * @param stages
	 * @return
	 */
	public int[] solution(int N, int[] stages) {
		
		int[] answer = new int[N];//사용자가 도전 중인 스테이지 번호
		List<Fail> fails = new ArrayList<Fail>();//실패율 저장하기 위한 리스트
		int total = stages.length;// 전체 사용자 수
		
		
		//--------------
		//마지막 스테이지를 클리어한 사용자는 있을 수 있기 때문에
		//총 스테이지 + 1을 해준다.
		//--------------
		int[] users = new int[N+1];//마지막 스테이지가 N이면 그 스테이지를 클리어한 사용자가 존재할 수 있기 때문에 +1
		
		for(int s : stages) {//각 스테이지의 사용자 수를 구함
			
			users[s-1]++;//1번 스테이지의 사용자 수를  0부터 사용하기 위해
			
		}//for
		
		
		//----------------------------------
		//스테이지 실패율 구하기
		//----------------------------------
		for(int i=0; i < N; ++i) {
			
			if(users[i] == 0) {//i가 0이면 스테이지에 도달한 사용자
				
				fails.add(new Fail(i + 1, 0));//위에서 1을 빼줬으니 다시 더해준다. 실패율은 0
				
			}else {
				
				fails.add(new Fail(i + 1, (double)users[i]/total));//위에서 1을 빼줬으니 다시 더해준다. 현재 스테이지에 남아있는 사용자 수 / 전체 사용자 수
				total -= users[i];//전체에서 현재 스테이지에 머물고 있는 사람들 빼주기
				
			}//else
			
		}//for
		
		Collections.sort(fails, comp);
		
		for(int i = 0; i < N; ++i) {
			
			answer[i] = fails.get(i).stage;  
			
		}//for
		
		return answer;
	}
	
}
