//Joe Schiel
//A game of blackjack in the console

import java.util.ArrayList;
import java.util.Scanner;

public class Play {
	
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		char playerAction = 'z';
		//counter to increment deck index
		int deckNext = 0;
		boolean gameOver = false;
		//Arraylists to hold player hand and dealer hand.
		// Array[] not used because the size of the array can change.
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> dealerHand = new ArrayList<Card>();	
		//Next step: Implement GUI
		//Panel blackjackPanel = new Panel();
		//Frame.BlackJackFrame blackjackFrame = new Frame.BlackJackFrame(blackjackPanel);
				
		System.out.println("Welcome to black jack!");
		
		while(gameOver == false){
			
			//Create a deck of cards and shuffle the deck.
			Card deck[] = new Card[52];
			Card.buildDeck(deck);
			Card.shuffleDeck(deck);
			
			//Creates the first hands of cards and increments deckNext counter
			deckNext = initialDeal(deckNext, deck, playerHand, dealerHand);
			
			//Initial hands dealt
			System.out.println("You have been dealt " + playerHand.get(0) + " and " + playerHand.get(1));
			System.out.println("Your current total is " + handTotal(playerHand));
			if(handTotal(dealerHand) < 21){
				System.out.println("Dealer is showing " + dealerHand.get(0));
			}
			else if(handTotal(dealerHand) == 21){
				System.out.println("Dealer has blackjack " + dealerHand.get(0) + " " + dealerHand.get(1));
			}
			
			//prompt user for input
			System.out.println("Hit or stand?");
			playerAction = console.next().toLowerCase().charAt(0);
			
			//Until the player chooses to stand
			while (playerAction != 's'){
			if(playerAction == 'h'){
				//Add a card to the player's hand if they choose to hit.
				//If they have 21 or bust move on.
				playerHand.add(deck[deckNext]);
				deckNext++;
				System.out.println("You were dealt a " + playerHand.get(playerHand.size()- 1).toString());
				System.out.println("Your total: " + handTotal(playerHand));
				if(handTotal(playerHand) > 21){
					break;
				}
				else if(handTotal(playerHand) == 21){
					break;
				}
				else{
				System.out.println("Hit or stand?");
				playerAction = console.next().toLowerCase().charAt(0);
				System.out.println("Your total: " + handTotal(playerHand));
				}
			}
			}
			
			//dealer is dealt a card until his total is 17 or higher
			dealerTurn(deckNext, deck, dealerHand);
			
			//compares scores to find out who wins
			resultConditions(playerHand, dealerHand);
			
			//Game over
			System.out.println("Would you like to play again? ('y' or 'n')");
				playerAction = console.next().toLowerCase().charAt(0);
				//End program
				if(playerAction == 'n'){
					gameOver = true;
					playerHand.clear();
					dealerHand.clear();
				}
				// play a new game
				else if(playerAction == 'y'){
					playerHand.clear();
					dealerHand.clear();
				}
	
	}
		//Scanner resource leak if not closed.
		console.close();
	}
	
	//returns an int to increment deckNext counter
	public static int initialDeal(int dNext, Card deck[], ArrayList<Card> pHand, ArrayList<Card> dHand){
		dNext++;
		pHand.add(deck[dNext]);
		dNext++;
		dHand.add(deck[dNext]);
		dNext++;
		pHand.add(deck[dNext]);
		dNext++;
		dHand.add(deck[dNext]);
		dNext++;
		
		return dNext;
	}
	
	//Totals the cards in the arraylist
	public static int handTotal(ArrayList<Card> hand){
		int total = 0;
		boolean isAce = false;	
		
		////Add what to do if 2 aces
		
		//If the face value is jack, queen or king add 10 to the total
		for (int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getFaceValue() > 10){
				total = total + 10;
			}
			//add eleven if ace card is dealt
			else if(hand.get(i).getFaceValue() == 1){
				total = total + 11;
				isAce = true;
			}
			else{
			total = total + hand.get(i).getFaceValue();
			}
		}
		
		//If ace is dealt causing the player to bust, ace value is 1 instead of eleven.
		if (total > 21 && isAce == true){
			total = total - 10;
		}		
		return total;
	}
	
	////Refactor code in main
	//public static void playerTurn(){	
	//}
	
	//Dealer draws card until dealer's total is >= 17
	public static void dealerTurn(int dNext, Card deck[], ArrayList<Card> dHand){
		
		System.out.println("Dealer hand: " + dHand.get(0) + " and " + dHand.get(1));
		System.out.println("Dealer total: " + handTotal(dHand));
		sleep(1500);
		
		//Dealer can not hit if total is 17 or greater
		while((handTotal(dHand)) < 17){
			dHand.add(deck[dNext]);
			dNext++;
			System.out.println("Dealer was dealt " + dHand.get(dHand.size()- 1).toString());
			System.out.println("Dealer total: " + handTotal(dHand));
			sleep(1500);
		}
	}
	
	//calculates if the player wins, loses, or ties(push) and prints results.
	public static void resultConditions(ArrayList<Card> pHand, ArrayList<Card> dHand){
		
		if(handTotal(dHand) > handTotal(pHand) && handTotal(dHand) < 22){
			System.out.println("Your hand: " + handTotal(pHand) + " Dealer hand: " + handTotal(dHand));
			System.out.println("You lost.");
		}
		
		if(handTotal(dHand) < handTotal(pHand) && handTotal(pHand) < 22){
			System.out.println("Your hand: " + handTotal(pHand) + " Dealer hand: " + handTotal(dHand));
			System.out.println("You Win!");	
		}
			
		if(handTotal(dHand) == handTotal(pHand)){
			System.out.println("Your hand: " + handTotal(pHand) + " Dealer hand: " + handTotal(dHand));
			System.out.println("Push. Nobody wins.");	
		}
		
		if(handTotal(pHand) > 21 && handTotal(dHand) > 21){
			System.out.println("Your hand: " + handTotal(pHand) + " Dealer hand: " + handTotal(dHand));
			System.out.println("You and the dealer busted");
			System.out.println("Dealer wins");
		}
		
		
		if(pHand.size() == 2 && handTotal(pHand) == 21){
			System.out.println("Blackjack!");			
		}
		
		if(handTotal(pHand) <= 21 && handTotal(dHand) > 21 ){
			System.out.println("Your hand: " + handTotal(pHand) + " Dealer hand: " + handTotal(dHand));
			System.out.println("Dealer busted! You win!");
		}		
	}
	
	public static void sleep(int t){
		try {
		    Thread.sleep(t);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		    // handle the exception...  //Stackoverflow.com      
		    // For example consider calling Thread.currentThread().interrupt(); here.
		}
	}	
}
