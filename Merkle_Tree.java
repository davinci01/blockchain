package blockchain;

public class Merkle_Tree {
	String merkleroothash="";
	transaction[] t;
	
	public void hashing(Block b){
		HashAlgorithm h=new HashAlgorithm();
		for (int i=0; i< b.transactions.length-1 ;i++ ){
			String a=h.sha256(b.transactions[i].id_transaction).concat(h.sha256(b.transactions[i+1].id_transaction));
			merkleroothash=merkleroothash+a;
			
		}
	}
}
