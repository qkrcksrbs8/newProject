package codingTest.blockGame;
import java.util.*;

public class Solution {
	
	int N;			//�Է¹��� ������
	int[][] Board;	//�Է¹��� ��� �迭

	boolean canFill(int row, int col) {
		
		for(int i = 0; i < row; ++i) {
			
			if(Board[i][col] != 0 )return false;//�ϳ��� 0�̸� ���� �� ��
			
		}//for
		
		return true;//����� ���� �� ����
		
	}//canFill
	
	boolean find(int row, int col, int h, int w) {
		
		int emptyCnt = 0;	//�� ���� cnt
		int lastValue = -1;	//lastVlaue�� ���� ���� ��� ����
		
		for(int r = row; r < row + h; ++r) {
			
			for(int c = col; c< col + w; ++c) {
				
				if(Board[r][c] == 0) {				//0�̸� �� ����
					
					if(!canFill(r,c)) return false;	//����� ���� �� ����
					if(++emptyCnt > 2) return false;//�� ������ 2�� �̻��̸� ���� �� ����
					
				}else {
					
					//--------------------------------------
					//������ ����� ��� ���� ���� ���� ����.
					//��� ã�� ���� �߿��� �ϳ��� �ٸ� ����� ���� �ִٸ� return false
					//������ ����� ã�Ƽ� ���� �� �ִ��� üũ�ؾ� ��.
					//lastValue�� -1�� �ƴϰ� ���� ��ϰ� ���� �ٸ��ٸ�
					//--------------------------------------		
					if(lastValue != -1 && lastValue != Board[r][c]) return false;
					lastValue = Board[r][c];//���� ���� ��
					
				}//else
				
			}//for
			
		}//for
		
		
		//--------------------------------------
		//���� ������ ����ߴٸ� ���� �� �ִ� ����̶�� ��
		//���� �� �ִ� ����̴� �����ְ� ����
		//--------------------------------------
		for(int r = row; r <row + h; ++r) {
			
			for(int c = col; c < col + w; ++c) {
				
				Board[r][c] = 0;//��� �����
				
			}//for
			
		}//for
		
		return true;
		
	}//find
	
	public int solution(int[][] board) {
	
		Board = board;		//�Է¹��� ��� �迭
		N = board.length;	//����� ũ��
		
		int answer = 0;		//���� ���
		int cnt;			//���� �� �ִ� ��� ��
		
		
		//--------------------------------------
		//�迭���� ����� ã�Ƽ� ���� �� �ִ� ����̸� �����ش�.
		//����� ã�� �� �迭�� max�� �ʰ��ؼ� ã�� �� ������ 
		//���ο� ���ο� N-2, N-3 �Ǵ� N-3, N-2�� �ο��Ѵ�.
		//2*3 �Ǵ� 3*2 �迭�� ã�� ������.
		//--------------------------------------
		do {
			
			cnt = 0;//������ ī��Ʈ 0���� �ʱ�ȭ
			
			for(int i = 0; i < N; ++i) {
				
				for(int j = 0; j < N; ++j) {
					
					if(i <= N-2 && j < N-3 && find(i, j, 2, 3)) {		//���ο��� ã������ cnt����
						
						++cnt;
						
					}else if(i <= N-3 && j <= N-2 && find(i, j, 3, 2)) {//���ο��� ã������ cnt����
						
						++cnt;
						
					}
					
				}//for
				
			}//for
			
			answer += cnt;// ���� ����� ã�� ���� �� �ִ� ��� �� ��� 
			
		}while(cnt != 0);//���� ����� ���ٸ� �ʱ�ȭ
		
		return answer;//���� ��� ����
		
	};//solution
	
	
}
