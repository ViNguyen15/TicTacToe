package game;

import java.util.Random;
import java.util.Scanner;

public class Janken {

	public static void main(String[] args) {
		Janken janken = new Janken();
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		int player, computer;
		String playerDecision = "", cpuDecision="", results="";
		
		//asking for players input
		System.out.println("Let's play a game of rock paper scissor\n"
				+ "type 0 for rock\n"
				+ "type 1 for paper\n"
				+ "type 2 for scissor");
		
		//inputs
		player = input.nextInt(); input.close();
		computer = random.nextInt(3);
		
		//decision
		playerDecision = janken.getDecision(player);
		cpuDecision = janken.getDecision(computer);
		results = janken.getResults(playerDecision, cpuDecision);
		
		
		//display
		System.out.println("Player chooses " + playerDecision 
				+ "\nComputer chooses " + cpuDecision 
				+ "\nYou " + results);
	}
	
	//results
	public String getResults(String playerDecision, String cpuDecision) {
		String results = "";
	if(playerDecision.equals(cpuDecision))
		results = "Drew";
	else if(playerDecision.equals("rock")&&cpuDecision.equals("paper")||
			playerDecision.equals("paper")&&cpuDecision.equals("scissor")||
			playerDecision.equals("scissor")&&cpuDecision.equals("rock"))
		results = "Lose";
	else
		results = "Win";
	return results;
	}
	
	//Decision
	public String getDecision(int num) {
		String decision = "";
		if(num == 0)
			decision = "rock";
		if(num == 1)
			decision = "paper";
		if(num == 2)
			decision = "scissor";
		return decision;
	}
}
