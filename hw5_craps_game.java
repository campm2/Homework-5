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
public class hw5_craps_game{
   public static void main(String [] args) throws IOException {
	   for(int i=0;i<=20;i++) {
	   
	   
		   
		   
	   }// end of for loop
	   
   }//end of main
	/**
	 *@param  randomNumber
	 *@param rolls
	 */
	public static int GenerateRandomNumber() {
		Random randomNumbers=new Random();
		int rolls= randomNumbers.nextInt(6)+1;
		return rolls;
		
	}
	/**
	 * @param  sum_Of_Rolls
	 */
	public static int RollDice() {
		int sum_Of_Rolls=GenerateRandomNumber() +GenerateRandomNumber();
		return sum_Of_Rolls;
		
	}
	public static String DecideWhoWin(int dice_player1,int dice_player2,PrintWriter pw) throws IOException {
		//Allow to write to outfile
		PrintWriter outFile =new PrintWriter("game.txt");
	 
		int result_Of_Dice_Roll=RollDice();
		String answer="";
		
		switch (dice_player1) {
		
		case 1:
			System.out.println("Player 1 is rolling the dice");
			// if player 1 rolls a natural
			if(result_Of_Dice_Roll==7 ) {
			answer=("That is a Win! Player 1 wins");
			}//end if
			else if(result_Of_Dice_Roll==11) {
			answer=("That is a Win! Player 1 wins");
			}//end of else
			break;
		
		case 2:
			System.out.println("Player 1 is rolling the dice");
			//if player 1 rolls a craps
			if(result_Of_Dice_Roll==2) {
				answer=("That is craps! Player 1 loses");
			
			}//end of if
			else if(result_Of_Dice_Roll==3) {
			answer=("That is craps! Player 2 loses");
			
			}//end of first else if
			else if(result_Of_Dice_Roll==12) {
			answer=("That is craps! Player 2 loses");
			}// end of second else if
			break;
		
		case 3:
			System.out.println("Player 1 is rolling the dice");
			while(result_Of_Dice_Roll>=4 && result_Of_Dice_Roll<=10){
				answer=("The roll is a"+ result_Of_Dice_Roll+"That is a point. Player 1 rolls again"); 
				int result_Of_Dice_Roll2;
				result_Of_Dice_Roll2=RollDice();
				if(result_Of_Dice_Roll2==result_Of_Dice_Roll) {
					answer= ("The roll is a"+ result_Of_Dice_Roll2+"That is a the point! Player 1 wins");
				}// end of if
				else if(result_Of_Dice_Roll2!=result_Of_Dice_Roll || result_Of_Dice_Roll2!=7) {
					// confused on how to get it to go again
					answer= ("The roll is a" +result_Of_Dice_Roll2 +".The point is" + result_Of_Dice_Roll2 + "Player 1 rolls again");
					result_Of_Dice_Roll2=RollDice();
				}// end of 1st else if
				else if(result_Of_Dice_Roll2==7) {
					System.out.printf("That roll is a %d. That is a loss! Player 1 loses", result_Of_Dice_Roll2 );
				}// end of second else if
				
				}// end of while
				break;
			
			
		}// end first of switch statement
		
		switch (dice_player2) {
		case 1:
			System.out.println("Player 2 is rolling the dice");

			// if player 2 rolls a natural
			if(result_Of_Dice_Roll==7 ) {
			answer=("That is a Win! Player 2 wins");
			}//end if
			else if(result_Of_Dice_Roll==11) {
			answer=("That is a Win! Player 2 wins");
			}//end of else
			break;
		
		case 2:
			System.out.println("Player 2 is rolling the dice");

			//if player 2 rolls a craps
			if(result_Of_Dice_Roll==2) {
				answer=("That is craps! Player 2 loses");
			
			}//end of if
			else if(result_Of_Dice_Roll==3) {
			answer=("That is craps! Player 2 loses");
			
			}//end of first else if
			else if(result_Of_Dice_Roll==12) {
			answer=("That is craps! Player 2 loses");
			}// end of second else if
			break;
		
		case 3:
			System.out.println("Player 2 is rolling the dice");

			while(result_Of_Dice_Roll>=4 && result_Of_Dice_Roll<=10){
			answer=("The roll is a"+ result_Of_Dice_Roll+"That is a point. Player 2 rolls again"); 
			int result_Of_Dice_Roll2;
			result_Of_Dice_Roll2=RollDice();
			if(result_Of_Dice_Roll2==result_Of_Dice_Roll) {
				answer= ("The roll is a"+ result_Of_Dice_Roll2+"That is a the point! Player 2 wins");
			}// end of if
			else if(result_Of_Dice_Roll2!=result_Of_Dice_Roll || result_Of_Dice_Roll2!=7) {
				// confused on how to get it to go again
				answer= ("The roll is a" +result_Of_Dice_Roll2 +".The point is" + result_Of_Dice_Roll2 + "Player 2 rolls again");
				result_Of_Dice_Roll2=RollDice();
			}// end of 1st else if
			else if(result_Of_Dice_Roll2==7) {
				System.out.printf("That roll is a %d. That is a loss! Player 2 loses", result_Of_Dice_Roll2 );
			}// end of second else if
			
			}// end of while
			break;
		
		}// end of switch statement
		
		return answer;
	
	}// end of function bracket
	public static void AnnounceFinalWinner(int money_player1, int money_player2, PrintWriter pw) {
		//while()
		int intital_Amount=1000;
	}
} //end of class bracket 


