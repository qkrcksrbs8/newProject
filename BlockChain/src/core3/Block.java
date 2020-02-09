package core3;

import java.util.ArrayList;

import util.Util;

public class Block {

	private int blockID;
	public String previousBlockHash;
	private int nonce;
	private ArrayList<Transaction> transactionList;
	
	public String getPreviousBlockHash() {
		return previousBlockHash;
	}
	public void setPreviousBlockHash(String previousBlockHash) {
		this.previousBlockHash = previousBlockHash;
	}

	
	public int getblockID() {
		return blockID;
	}
	public void setblockID(int blockID) {
		blockID = blockID;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	
	

	public Block(int blockID,String previousBlockHash, int nonce, ArrayList transactionList) {
		super();
		blockID = blockID;
		this.previousBlockHash = previousBlockHash;
		this.nonce = nonce;
		this.transactionList = transactionList;
	}
	
	public void addTransaction(Transaction transaction ) {
		transactionList.add(transaction);
	}
	
	public void getInformation() {
		System.out.println("=========================");
		System.out.println("블록 번호 : "+ getblockID());
		System.out.println("이전 해시 : " + getPreviousBlockHash());
		System.out.println("채굴 변수 값 : "+getNonce());
		System.out.println("트랜잭션 개수" + transactionList.size()+"개");
		for(int i = 0; i<transactionList.size(); i++) {
			System.out.println(transactionList.get(i).getInformation());
		}
		System.out.println("블록 해시 : "+ getBlockHash());
		System.out.println("---------------------------------------------");
		
	}
	
	
	public String getBlockHash() {
		String transactionInformations = "";
		for(int i = 0; i <transactionList.size(); i++) {
			transactionInformations += transactionList.get(i).getInformation();
		}
		return Util.getHash(nonce + transactionInformations + previousBlockHash);
	}
	
	public void mine() {
		while(true) {
			if(getBlockHash().substring(0,4).equals("0000")) {
				System.out.println(blockID + "번째 블록의 채굴에 성공했습니다.");
				break;
			}
			nonce++;
		}
	}
	
}
