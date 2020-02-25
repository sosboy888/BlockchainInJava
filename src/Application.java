import java.util.*;
public class Application {
	static List<Block> blockchain=new ArrayList<Block>();
	final static int difficulty=4;
	public static void main(String[] args) {
		System.out.println("Create Genesis Block");
		addBlock(new Block("0","First transaction"));
		addBlock(new Block(blockchain.get(blockchain.size()-1).returnCurrent(),"Second transaction"));
		addBlock(new Block(blockchain.get(blockchain.size()-1).returnCurrent(),"Third transaction"));
		addBlock(new Block(blockchain.get(blockchain.size()-1).returnCurrent(),"Fourth transaction"));
		for(int i=4;i<10;i++) {
			addBlock(new Block(blockchain.get(blockchain.size()-1).returnCurrent(),Integer.toString(i)+"th transaction"));
		}
		System.out.println(blockchain.toString());
		System.out.println(UtilityClass.getJSONData(blockchain));
		System.out.println(isBlockchainValid(blockchain));
	}
	public static void addBlock(Block b) {
		b.mineBlock(difficulty);
		blockchain.add(b);
		System.out.println("Block added to the blockchain.");
	}
	public static boolean isBlockchainValid(List<Block> blockchain) {
		Block prevBlock;
		Block currentBlock;
		for(int i=1;i<blockchain.size();i++) {
			prevBlock=blockchain.get(i-1);
			currentBlock=blockchain.get(i);
			if(!currentBlock.returnCurrent().equals(currentBlock.calculateHash())) {
				return false;
			}
			if(!currentBlock.returnPrevious().equals(prevBlock.returnCurrent())){
				return false;
			}
				
		}
		return true;
	}
	

}
