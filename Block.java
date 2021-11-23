package blockchain;

import java.io.Serializable;

public class Block implements Serializable {
	public String sender;
	public int id;
	public int index;
	public String version;
	public long Timestamp;
	public String HashPrevBlock;
	public String Nouce;
	public String Target;
	public String MerkleRoot;
	
	public String HashHeaderBlock;
	public transaction Donnee; 
	
	public Block(int id, String sender) {
		this.id=id;
		this.sender=sender;
		// TODO Auto-generated constructor stub
		
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public double getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
