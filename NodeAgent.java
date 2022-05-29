//-----import libraries---------- 
// *******this code was written by Bouragaa seif eddine groupe1 and rbih Boulanouar groupe3******
package blockchain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import blockchain.transaction.input;
import blockchain.transaction.output;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

//------------------------main class--------------------------------
public class NodeAgent extends Agent {
	public ArrayList<Block> blockchain = new ArrayList(); // blocks list
	Block selfblock;
	public final String[] Agentlist = { "agent1", "agent2", "agent3", "agent4", "agent5", "agent6" }; //agents list
	public ArrayList<transaction> transactionlist = new ArrayList(); //transactions list
	
	
//----------------------------------------main jade function ----------------------------------------
	protected void setup() {

		System.out.println("creation of " + this.getAID().getLocalName() + " ...");
		// test();
		try {
			//if ((String) this.getLocalName() != "agent1") {
				
				send_TRANSACTION(1);
				send_block(1);
				
				
				

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {
				// TODO Auto-generated method stub

				ACLMessage message = getAgent().receive();
				try {
					if (message != null) {

						recive_block(message);

					} else {
						block();
					}
				} catch (Exception e) {

				}
			}

		});

	}
	
	
	
//--------------------------------------------create blocks with object --------------------------------------------------
	public Block create_block() {
		System.out.println("creating a block ...");
		int id = (int) Math.floor(Math.random() * 10); //generate random id 
		
		selfblock = new Block(this.getLocalName(), id,"prehash", "1.0.0", 10000, 10000, "mrkelroot", "",transactionlist);//create block
		
		HashAlgorithm hashage = new HashAlgorithm();
		selfblock.HashHeaderBlock = hashage.sha256(selfblock); //header hash
		return selfblock;

	}
	
	
	
	
//-----------------------------------------send blocks to agents throw ACL message jade ---------------------------------------------------------------
	public void send_block(int repeat) throws IOException {
		for (int i = 0; i < repeat; i++) {
			Block block = create_block();
			for (String j : Agentlist) {
				if (!j.equals(this.getLocalName())) {

					System.out.println("sending a block ...");
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(new AID(j, AID.ISLOCALNAME)); // j agent receiver
					msg.setContentObject(selfblock);
					send(msg);
				}
			}
		}
	}
	
	
	
//-----------------------------------------print the block list-----------------------------------------------------------------
	public void printchain(ArrayList<Block> blockchain) {
		System.out.println("blockchain of " + this.getLocalName() + " is:---------------------------------");
		for (Block block : blockchain) {

			System.out.println("|    " + block.toString() + ": id=" + block.id + " index: " + block.index + " , sender="
					+ block.sender + " " + selfblock.transactionlist + " timestamp: " + block.Timestamp + " hash: "
					+ block.HashHeaderBlock+" prevhash: "+ block.HashPrevBlock);
			
			
		}
		
		System.out.println("---------------------------------------------------------");
	}

	
	
	
	
//------------------------------------------------ receive all the blocks -----------------------------------------------------------------
	public void recive_block(ACLMessage message) throws UnreadableException {
		if (message.getContentObject().getClass() == selfblock.getClass()) { // check if is block or transaction object to avoid lost 
			try { //block object then print block list

				Block receivedBlock = (Block) message.getContentObject();
				System.out.println("[+] " + this.getLocalName() + " just recived " + receivedBlock + " with id="
				+ receivedBlock.id + " from " + receivedBlock.sender + " [+]");
				blockchain.add(receivedBlock);
				printchain(blockchain);
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try { // transaction object then print transaction list
				transaction receivedtransaction = (transaction) message.getContentObject();
				System.out.println("[+] " + this.getLocalName() + " just recived " + receivedtransaction + " with id="
						+ receivedtransaction.id_transaction + " from " + message.getSender().getLocalName() + " [+]");
				transactionlist.add(receivedtransaction);
				selfblock.transactionlist = transactionlist;

				printtransactions(transactionlist);
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	
	
//-------------------------------------------create transaction with object-----------------------------------------------------------------------------------
	public transaction createTranstacion() {
		ArrayList<input> InputList = new ArrayList();
		ArrayList<output> OutList = new ArrayList();
		return new transaction("1.0.0", randomtransactionhash(), InputList, OutList);
	}

	

	
	
	
//--------------------------------------------send transactions to all agents---------------------------------------------------------------------------------------------
	public void send_TRANSACTION(int repeat) throws IOException {
		for (int i = 0; i < repeat; i++) {
			transaction tran = createTranstacion();
			for (String j : Agentlist) {
				if (!j.equals(this.getLocalName())) {

					System.out.println("sending a transaction ...");
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(new AID(j, AID.ISLOCALNAME)); // j receiver
					msg.setContentObject(tran);
					send(msg);
				}
			}
		}
	}
	
	

//-----------------------------------------'function allow us to print all the transactions sent' ----------------------------------------------------------------------------------
	public void printtransactions(ArrayList<transaction> transactionlist) {
		System.out.println("transaction list of " + this.getLocalName() + " is:---------------------------------");
		
		for (transaction tran : transactionlist) {

			System.out.println("|    " + tran.toString() + ": id=" + tran.id_transaction + " , senderhash="
					+ tran.SenderHash + " timestamp " + tran.Timestamp + " version: " + tran.version + "    |");

		}
		System.out.println("---------------------------------------------------------");
	}
//-----------------------------------------------------------------------------------------------------------------------------	
	
	
	
	
//----------------------------------------------------------------------------------------------------------------------------

	
	
	
	public String randomtransactionhash() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 102;
		int targetStringLength = 64;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		return generatedString;
	}

}
//-----------------------------------------------------------------------------------------------------------------------------------