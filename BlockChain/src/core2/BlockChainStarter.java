package core2;

public class BlockChainStarter {

	public static void main(String[] args) {


		
		
		Block block1 = new Block(1, null, 0, "chasemart");
		block1.mine();
		block1.getInformation();
		
		Block block2 = new Block(2, block1.getBlockHash(), 0, "데이터123");
		block2.mine();
		block2.getInformation();
		
		Block block3 = new Block(3, block2.getBlockHash(), 0, "데이터");
		block3.mine();
		block3.getInformation();

		Block block4 = new Block(4, block3.getBlockHash(), 0, "데이터");
		block4.mine();
		block4.getInformation();
	
	}

}
