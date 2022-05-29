package blockchain;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class transaction implements Serializable {
	private static AtomicInteger counter = new AtomicInteger();
	public String version;
	public int id_transaction;
	public String SenderHash;
	public LocalTime Timestamp;
	public int In_counter;
	public ArrayList<input> InputList = new ArrayList();
	public int Out_counter;

	public static AtomicInteger getCounter() {
		return counter;
	}

	public static void setCounter(AtomicInteger counter) {
		transaction.counter = counter;
	}

	public LocalTime getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(LocalTime timestamp) {
		Timestamp = timestamp;
	}

	public int getIn_counter() {
		return In_counter;
	}

	public void setIn_counter(int in_counter) {
		In_counter = in_counter;
	}

	public int getOut_counter() {
		return Out_counter;
	}

	public void setOut_counter(int out_counter) {
		Out_counter = out_counter;
	}

	public ArrayList<output> OutList = new ArrayList();

	public class input {
		public String Previous_tx;
		public int index;
		public String scriptSig;
	}

	public class output {
		public double Value;
		public String ScriptPubKey;
	}

	public transaction(String version, String senderHash, ArrayList<input> inputList, ArrayList<output> outList) {
		super();
		this.version = version;
		this.id_transaction = counter.incrementAndGet();
		this.SenderHash = senderHash;
		this.Timestamp = java.time.LocalTime.now();
		// this.In_counter = in_counter;
		this.InputList = inputList;
		// this.Out_counter = out_counter;
		this.OutList = outList;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getId_transaction() {
		return id_transaction;
	}

	public void setId_transaction(int id_transaction) {
		this.id_transaction = id_transaction;
	}

	public String getSenderHash() {
		return SenderHash;
	}

	public void setSenderHash(String senderHash) {
		SenderHash = senderHash;
	}

	public ArrayList<input> getInputList() {
		return InputList;
	}

	public void setInputList(ArrayList<input> inputList) {
		InputList = inputList;
	}

	public ArrayList<output> getOutList() {
		return OutList;
	}

	public void setOutList(ArrayList<output> outList) {
		OutList = outList;
	}

}
