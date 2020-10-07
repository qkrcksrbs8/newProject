package codingTest.blockGame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Solution solution = new Solution();//블록게임 배열

		//블록게임 배열
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
		
		//배열 결과
		System.out.println("제거한 블록의 수는 "+solution.solution(board)+"개 입니다.");
		
	}

}
