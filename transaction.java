package blockchain;

import java.util.ArrayList;

public class transaction {
	public String version;
	public int id_transaction;
	public String SenderHash;
	public String Timestamp;
	
	public int In_counter;
	public ArrayList<input>InputList = new ArrayList();
	public int Out_counter;
	public ArrayList<output>OutList = new ArrayList();
	
	public class input{
		public  String Previous_tx;
		public int index;
		public String scriptSig;
	}
	public class output{
		public double Value;
		public String ScriptPubKey;
	}
}
