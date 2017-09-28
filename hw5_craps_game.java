/* ========================================================================== */
/* PROGRAM: Slot Machine Simulation
    AUTHOR: Megan Camp, 
    COURSE NUMBER: CIS 210
    COURSE SECTION NUMBER: 02
    INSTRUCTOR NAME: Dr.Tian
    PROJECT NUMBER: 5
    DUE DATE: 09/28/2017
SUMMARY
Write a program simulates the dice game called Craps. For this project, assume that the game is being played between two players, and that the rules are as follows:
1. One of the players goes first. That player announces the size of the bet, and rolls the dice. If the player rolls a
a. 7 or 11, it is called a natural. The player who rolled the dice wins.
b. 2, 3 or 12, it is called craps. The player who rolled the dice loses.
c. If the player’s first roll is any other number (4, 5, 6, 8, 9, or 10), the bet is not
immediately decided. Instead, the number that the player rolled becomes the point and he continues to roll the dice until either he rolls the point a 2nd time, in which case he wins, or he rolls a 7, in which case he loses. For example, suppose a player’s 1st roll is a 6. Then 6 becomes the point. He must roll again. If his next roll were a 3, he would have to roll a 3rd time. The point remains 6. He continues to roll the dice until he either gets a 6 (a win) or a 7 (a loss).
2. When a player wins, he collects the bet, and gets to keep the dice and take another turn. Then the play begins again with rule (1)
3. When a player loses, his opponent collects the money that was bet and it becomes the opponent’s turn to roll the dice. The opponent starts play again with rule (1)

INPUT
No input
OUTPUT
A roll-by-roll description of what happens as the game is played should be print out on the console and should be written to a file as well.
ASSUMPTIONS
/* MAIN FUNCTION */
import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.*;
public class hw5_craps_game {
public static void main(String [] args) throws IOException {
	   String turn="Player 1";
	   int WAGERS = 20;
	   int dice_player2=0;
	   int dice_player1=0;
	   int player2_Total_Amount=1000;
	   int player1_Total_Amount=1000;
	   int player2_Bet_Amount=0;
	   int Player1_Bet_Amount=100;
	   int count=1;
	   PrintWriter outFile =new PrintWriter("game.txt");
	   while(count<=WAGERS || player1_Total_Amount <=0 || player2_Total_Amount<=0 ) {
		   if(turn.equals("Player 1")) {
			   dice_player1=RollDice();
			   dice_player2=0;
			   System.out.println("Wager " + count +" :Bet is " + Player1_Bet_Amount);
			   WriteToFile(outFile, "Wager " + count +" :Bet is " + Player1_Bet_Amount);
			   System.out.println("Player 1 is rolling.");
			   WriteToFile(outFile,"Player 1 is rolling.");
			   System.out.println("The roll is a " +dice_player1);
			   WriteToFile(outFile,"The roll is a " +dice_player1);
			   turn=DecideWhoWin(dice_player1,dice_player2,outFile);
			   while(turn.equals("Point")) {
				   turn=DecideWhoWin(dice_player1,dice_player2,outFile); 
			   }
			   	player1_Total_Amount+=Player1_Bet_Amount;
			   	player2_Total_Amount-=Player1_Bet_Amount;
			   	System.out.println("Currently, Player 1 has "+player1_Total_Amount+ " and Player 2 has " +player2_Total_Amount);
			   	WriteToFile(outFile,"Currently, Player 1 has "+player1_Total_Amount+ " and Player 2 has " +player2_Total_Amount);
		   }
			else if (turn.equals("Player 2")){
				dice_player2=RollDice();
				dice_player1=0;
				// if player 2 total money is more or equal to 1000
				if(player2_Total_Amount>=1000) {
					player2_Bet_Amount=150;
					System.out.println("Wager " + count +" :Bet is " + player2_Bet_Amount);
					WriteToFile(outFile,"Wager " + count +" :Bet is " + player2_Bet_Amount);
					System.out.println("Player 2 is rolling.");
					WriteToFile(outFile,"Player 2 is rolling.");
					System.out.println("The roll is a " +dice_player2);
					WriteToFile(outFile,"The roll is a " +dice_player2);
					turn=DecideWhoWin(dice_player1,dice_player2,outFile);
					 while(turn.equals("Point")) {
						   turn=DecideWhoWin(dice_player1,dice_player2,outFile);  
					 }
					player2_Total_Amount+=player2_Bet_Amount;
					player1_Total_Amount-=player2_Bet_Amount;
					System.out.println("Currently, Player 1 has "+player1_Total_Amount+ "and Player 2 has " +player2_Total_Amount);
				   	WriteToFile(outFile,"Currently, Player 1 has "+player1_Total_Amount+ "and Player 2 has " +player2_Total_Amount);
				 }
				//if player 2 total money is less than 1000
				  else if(player2_Total_Amount<1000) {
					  player2_Bet_Amount=50;
					  System.out.println("Wager " + count +" :Bet is " + player2_Bet_Amount);
						WriteToFile(outFile,"Wager " + count +" :Bet is " + player2_Bet_Amount);
						System.out.println("Player 2 is rolling.");
						WriteToFile(outFile,"Player 2 is rolling.");
						System.out.println("The roll is a " +dice_player2);
						WriteToFile(outFile,"The roll is a " +dice_player2);
					  turn=DecideWhoWin(dice_player1,dice_player2,outFile);
					  while(turn.equals("Point")) {
						   turn=DecideWhoWin(dice_player1,dice_player2,outFile); 
					  }
					  player2_Total_Amount+=player2_Bet_Amount;
					  player1_Total_Amount-=player2_Bet_Amount;
					  System.out.println("Currently, Player 1 has "+player1_Total_Amount+ "and Player 2 has " +player2_Total_Amount);
					  WriteToFile(outFile,"Currently, Player 1 has "+player1_Total_Amount+ "and Player 2 has " +player2_Total_Amount);
						
				  }// end of else
		     }//end of else if
		  turn=DecideWhoWin(dice_player1,dice_player2,outFile);
		  count++;
	   }//end of while loop	  
	   AnnounceFinalWinner(player1_Total_Amount, player2_Total_Amount, outFile);
	   
   }//main
	/**
	 *@param  randomNumber
	 *@param rolls
	 *@return rolls which will be between 1&6
	 */
	public static int GenerateRandomNumber() {
		Random randomNumbers=new Random();
		int rolls= randomNumbers.nextInt(6)+1;
		return rolls;
	}
	/**
	 * @param  sum_Of_Rolls // which returns the dice values added together
	 */
	public static int RollDice() {
		int sum_Of_Rolls=GenerateRandomNumber() +GenerateRandomNumber();
		return sum_Of_Rolls;
	}
	/**
	 * @param dice_player1// what player 1 rolls
	 * @param dice_player2// what player 2 rolls
	 * @param pw //to write to the file
	 * @return answer// will determine who plays next
	 * @throws IOException
	 */
	public static String DecideWhoWin(int dice_player1,int dice_player2,PrintWriter pw) throws IOException {
		String answer="";
		String error="error";
		if(dice_player1==0) {
			if(dice_player2==7 || dice_player2==11) {
				// if player 1 rolls a natural
				System.out.println("That is a natural! Player 2 Wins!");
				WriteToFile(pw,"That is a natural! Player 2 Wins!");
				answer="Player 2";
				return answer;
			}
			else if(dice_player2==2|| dice_player2==3 ||dice_player2==12) {
				//if player  rolls a craps
					System.out.println("That is craps! Player 2 loses");
					WriteToFile(pw,"That is craps! Player 2 loses");
					answer=("Player 1");
					return answer;
			}
			else if(dice_player2==4 || dice_player2==5 || dice_player2==6 || dice_player2==8 || dice_player2==9 || dice_player2==10) {
				int dice_Roll2=RollDice();
				//if new roll matches point
				if(dice_Roll2==dice_player2) {
					System.out.println("Player 2 Wins!");
					WriteToFile(pw," Player 2 Wins!");
					answer="Player 2";
					return answer;
				}// end of if
				else if (dice_Roll2!=7 && dice_Roll2!=dice_player2) {
					answer="Point";
					return answer;
				}//end of else if
				// if new roll is a 7
				else if(dice_Roll2==7) {
					System.out.println("Player 1 Wins!");
					WriteToFile(pw," Player 1 Wins!");
					answer="Player 1";
					return answer;
				}// end of second else if	
			}
	
		}// end of main if
		
		else if(dice_player2==0) {
			if(dice_player1==7 || dice_player1==11) {
				// if player 1 rolls a natural
				answer=("Player 1");
				System.out.println("That is a natural! Player 1 Wins!");
				WriteToFile(pw,"That is a natural! Player 1 Wins!");
				answer=("Player 1");
				return answer;
			}//end of if
			else if(dice_player1==2 || dice_player1==3 || dice_player1==12) {
				//if player  rolls a craps
				System.out.println("That is craps! Player 1 loses");
				WriteToFile(pw,"That is craps! Player 1 loses");
				answer=("Player 2");
				return answer;
			}//end of else if
			else if(dice_player1==4 || dice_player1==5 || dice_player1==6 || dice_player1==8 || dice_player1==9 || dice_player1<=10) {
				int dice_Roll2=RollDice();
				//if the new roll matches the point
				if(dice_Roll2==dice_player1) {
					System.out.println("Player 1 Wins!");
					WriteToFile(pw,"Player 1 Wins!");
					answer="Player 1";
					return answer;
				}// end of if
				else if (dice_Roll2!=7 && dice_Roll2!=dice_player1) {
					answer="Point";
					return answer;
				}//end of first else if
				//if the new roll is 7
				else if(dice_Roll2==7) {
					System.out.println("Player 2 Wins!");
					WriteToFile(pw,"Player 2 Wins!");
					answer="Player 2";
					return answer;
				}// end of second else if
				
			}//end of main else if
	}
		//will return this if function does not go through ifs
		//should not get here
		return error;
		
}
	/**
	 * 
	 * @param money_player1// the total money player 1 has
	 * @param money_player2// the total money player 2 has
	 * @param pw// to output to file
	 * @throws IOException
	 */
	public static void AnnounceFinalWinner(int money_player1, int money_player2, PrintWriter pw) throws IOException {
		if(money_player1>money_player2) {
			System.out.println("Player 1 Wins the Game!");
			WriteToFile(pw,"Player 1 Wins the Game!");
		}// end of if
		else if(money_player1==money_player2) {
			System.out.println("Its a Draw!");
			WriteToFile(pw,"Its a Draw!");
			
		}//end of first else if
		else if(money_player1<money_player2) {
			System.out.println("Player 2 Wins the Game!");
			WriteToFile(pw,"Player 2 Wins the Game!");
			
		}//end of second else if
	}
	/**
	 * @param pw
	 * @param data
	 * @throws IOException
	 */
	public static void WriteToFile(PrintWriter pw,String data) throws IOException{
		pw.println(data);
	}
} //end of class bracket 


