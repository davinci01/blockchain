package blockchain;
import jade.core.Profile;
public class Main {
	 static String [] commande = new String[4]; 
	 static String argument =""; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 createAgent("agent1");
		 createAgent("agent2");
		 createAgent("agent3");
		 createAgent("agent4");
		 createAgent("agent5");
		 createAgent("agent6");
		 jade.Boot.main(commande);
	}
	public static void createAgent(String AgentName){
		 
		 argument = argument+AgentName+":blockchain.NodeAgent;";
		 commande [0]="-cp"; 
		 commande [1]="jade.boot"; 
		 commande [2]="-gui";
		 commande [3]= argument; 
		 
		
		
	}

}
