package codingTest.blockGame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Solution solution = new Solution();//��ϰ��� �迭

		//��ϰ��� �迭
		int[][] board ={ {0,0,0,0,0,0,0,0,0,0}
						,{0,0,0,0,0,0,0,0,0,0}
						,{0,0,0,0,0,0,0,0,0,0}
						,{0,0,0,0,0,0,0,0,0,0}
						,{0,0,0,0,0,0,4,0,0,0}
						,{0,0,0,0,0,4,4,0,0,0}
						,{0,0,0,0,3,0,4,0,0,0}
						,{0,0,0,2,3,0,0,0,5,5}
						,{1,2,2,2,3,3,0,0,0,5}
						,{1,1,1,0,0,0,0,0,0,5}};
		
		//�迭 ���
		System.out.println("������ ����� ���� "+solution.solution(board)+"�� �Դϴ�.");
		
	}

}
