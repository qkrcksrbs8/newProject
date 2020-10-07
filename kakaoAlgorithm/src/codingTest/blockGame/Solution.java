package codingTest.blockGame;
import java.util.*;

public class Solution {
	
	int N;			//입력받은 사이즈
	int[][] Board;	//입력받은 블록 배열

	boolean canFill(int row, int col) {
		
		for(int i = 0; i < row; ++i) {
			
			if(Board[i][col] != 0 )return false;//하나라도 0이면 지울 수 없
			
		}//for
		
		return true;//블록을 지을 수 있음
		
	}//canFill
	
	boolean find(int row, int col, int h, int w) {
		
		int emptyCnt = 0;	//빈 공간 cnt
		int lastValue = -1;	//lastVlaue와 같은 값만 블록 제거
		
		for(int r = row; r < row + h; ++r) {
			
			for(int c = col; c< col + w; ++c) {
				
				if(Board[r][c] == 0) {				//0이면 빈 공간
					
					if(!canFill(r,c)) return false;	//블록을 지울 수 없음
					if(++emptyCnt > 2) return false;//빈 공간이 2개 이상이면 지울 수 없음
					
				}else {
					
					//--------------------------------------
					//동일한 블록인 경우 같은 값을 갖고 있음.
					//블록 찾는 베열 중에서 하나라도 다른 블록이 겹쳐 있다면 return false
					//동일한 블록을 찾아서 지울 수 있는지 체크해야 함.
					//lastValue가 -1이 아니고 현재 블록과 값이 다르다면
					//--------------------------------------		
					if(lastValue != -1 && lastValue != Board[r][c]) return false;
					lastValue = Board[r][c];//현재 원소 값
					
				}//else
				
			}//for
			
		}//for
		
		
		//--------------------------------------
		//위의 로직을 통과했다면 지울 수 있는 블록이라는 뜻
		//지을 수 있는 블록이니 지워주고 리턴
		//--------------------------------------
		for(int r = row; r <row + h; ++r) {
			
			for(int c = col; c < col + w; ++c) {
				
				Board[r][c] = 0;//블록 지우기
				
			}//for
			
		}//for
		
		return true;
		
	}//find
	
	public int solution(int[][] board) {
	
		Board = board;		//입력받은 블록 배열
		N = board.length;	//모두의 크기
		
		int answer = 0;		//최종 결과
		int cnt;			//지울 수 있는 블록 수
		
		
		//--------------------------------------
		//배열안의 블록을 찾아서 지울 수 있는 블록이면 지워준다.
		//블록을 찾는 중 배열의 max를 초과해서 찾을 수 있으니 
		//가로와 세로에 N-2, N-3 또는 N-3, N-2를 부여한다.
		//2*3 또는 3*2 배열로 찾기 때문임.
		//--------------------------------------
		do {
			
			cnt = 0;//최초의 카운트 0으로 초기화
			
			for(int i = 0; i < N; ++i) {
				
				for(int j = 0; j < N; ++j) {
					
					if(i <= N-2 && j < N-3 && find(i, j, 2, 3)) {		//가로에서 찾았으면 cnt증가
						
						++cnt;
						
					}else if(i <= N-3 && j <= N-2 && find(i, j, 3, 2)) {//세로에서 찾았으면 cnt증가
						
						++cnt;
						
					}
					
				}//for
				
			}//for
			
			answer += cnt;// 최종 결과에 찾은 지을 수 있는 블록 수 담기 
			
		}while(cnt != 0);//지운 블록이 없다면 초기화
		
		return answer;//최종 결과 리턴
		
	};//solution
	
	
}
