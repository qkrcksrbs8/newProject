package core1;

import util.Util;

public class BlockChainStarter {

	public static void main(String[] args) {

		/* 해쉬변환 */
		System.out.println(Util.getHash("박찬균"));
		
		/* nonce변수 채굴  */
//		int nonce = 0;
//		while(true){
//			if(Util.getHash(nonce + "").substring(0,6).equals("000000")) {
//				System.out.println("정답 : "+nonce);
//				System.out.println("해시 값: : "+ Util.getHash(nonce + ""));
//				break;
//			}
//			nonce++;
//		}
		
		

	}

}
