package codingTest.candidateKey;

public class Main {

	public static void main(String[] args) {
		
		Solution solution = new Solution();//후보키 식별 함수
		
		//학생 인적사항
		String[][] str = {
				
				//학번       이름          전공         학년
				{"100", "ryan", "music", "2"}
			   ,{"200", "apeach", "math", "2"}
			   ,{"300", "tube", "computer", "3"}
			   ,{"400", "con", "computer", "1"}
			   ,{"500", "muzi", "music", "3"}
			   ,{"600", "apeach", "music", "2"}	
			   
		};
		
		int cnt = solution.solution(str);
		
		System.out.println(cnt);
		
	}
	
}
