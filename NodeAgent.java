package blockchain;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class NodeAgent extends Agent {
	public ArrayList<Block> blockchain = new ArrayList();
	public final String[] Agentlist = { "agent1", "agent2", "agent3", "agent4", "agent5", "agent6" };

	protected void setup() {
		System.out.println("creation of " + this.getAID().getLocalName() + " ...");
		// test();
		try {
			if ((String) this.getLocalName() != "agent2") {
				send_block(1);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
				ACLMessage message = getAgent().receive();
				if (message != null) {
					recive_block(message);
				} else {
					block();
				}
			}

		});

	}

	public Block create_block() {
		System.out.println("creating a block ...");
		int id = (int) Math.floor(Math.random() * 10);
		return new Block(id, this.getLocalName());

	}

	public void send_block(int repeat) throws IOException {
		for (int i = 0; i < repeat; i++) {
			Block block = create_block();
			for (String j : Agentlist) {
				if (!j.equals(this.getLocalName())) {

					System.out.println("sending a block ...");
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(new AID(j, AID.ISLOCALNAME));
					msg.setContentObject(block);
					send(msg);
				}
			}
		}
	}

	public void printchain(ArrayList<Block> blockchain) {
		System.out.println("blockchain of " + this.getLocalName() + " is:---------------------------------");
		for (Block block : blockchain) {

			System.out.println("|    "+block.toString() + ": id=" + block.id + " , sender=" + block.sender+"    |");

		}
		System.out.println("---------------------------------------------------------");
	}

	public void recive_block(ACLMessage message) {
		try {
			Block receivedBlock = (Block) message.getContentObject();
			System.out.println("[+] "+this.getLocalName() + " just recived " + receivedBlock +" with id="+receivedBlock.id+" from "+receivedBlock.sender+" [+]");
			blockchain.add(receivedBlock);
			printchain(blockchain);
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
