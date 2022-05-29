package blockchain;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Block implements Serializable {
	private static AtomicInteger counter = new AtomicInteger();
	public String sender;
	public int id;
	public int index;
	public String version;
	public LocalTime Timestamp;
	public String HashPrevBlock;
	public long Nonce;
	public long Target;
	public String MerkleRoot;
	public String HashHeaderBlock;
	public ArrayList<transaction> transactionlist = new ArrayList();

	public Block(String sender, int id,String hashPrevBlock, String version, long nonce, long target,
			String merkleRoot, String hashHeaderBlock, ArrayList<transaction> transactionlist) {
		super();
		this.sender = sender;
		this.id = id;
		this.index = counter.incrementAndGet();
		this.version = version;
		this.Timestamp = java.time.LocalTime.now();
		this.HashPrevBlock = hashPrevBlock;
		this.Nonce = nonce;
		this.Target = target;
		this.MerkleRoot = merkleRoot;
		this.HashHeaderBlock = hashHeaderBlock;
		this.transactionlist = transactionlist;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public static AtomicInteger getCounter() {
		return counter;
	}

	public static void setCounter(AtomicInteger counter) {
		Block.counter = counter;
	}

	public LocalTime getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(LocalTime timestamp) {
		Timestamp = timestamp;
	}

	public String getHashPrevBlock() {
		return HashPrevBlock;
	}

	public void setHashPrevBlock(String hashPrevBlock) {
		HashPrevBlock = hashPrevBlock;
	}

	public long getNonce() {
		return Nonce;
	}

	public void setNonce(long nonce) {
		Nonce = nonce;
	}

	public long getTarget() {
		return Target;
	}

	public void setTarget(long target) {
		Target = target;
	}

	public String getMerkleRoot() {
		return MerkleRoot;
	}

	public void setMerkleRoot(String merkleRoot) {
		MerkleRoot = merkleRoot;
	}

	public String getHashHeaderBlock() {
		return HashHeaderBlock;
	}

	public void setHashHeaderBlock(String hashHeaderBlock) {
		HashHeaderBlock = hashHeaderBlock;
	}

	public ArrayList<transaction> getTransactionlist() {
		return transactionlist;
	}

	public void setTransactionlist(ArrayList<transaction> transactionlist) {
		this.transactionlist = transactionlist;
	}

}
