
//Joe Schiel

public class Card{

   private int suit;
   private int faceValue;
   
   //default constructor
   public Card(){
	  
	  //declare variables
      suit = 10;
      faceValue = 0;
   
   }
   public Card(int s, int fv){
	   this.suit = s;
	   this.faceValue = fv;
   }
   
   //Creates a string representation of a card object
   public String toString(){
   
   String str = "";
   
	   if (this.faceValue == 1){
		   str = "Ace of ";
	   }
	   else if (this.faceValue == 11){
		   str = "Jack of ";
	   }
	   else if (this.faceValue == 12){
		   str = "Queen of ";
	   }
	   else if (this.faceValue == 13){
		   str = "King of ";
	   }
	   else{
		   str = this.faceValue + " of ";
	   }
	   
	   if(this.suit == 0){
		   str = str + "Hearts";
	   }
	   else if(this.suit == 1){
		   str = str + "Diamonds";
	   }
	   else if(this.suit == 2){
		   str = str + "Clubs";
	   }
	   else if(this.suit == 3){
		   str = str + "Spades";
	   }
	   return str;
   }
   
   //Add 52 card objects in to the deck array
   public static void buildDeck(Card deck[]){
	   int suit = 4;
	   int faceValue = 13;
	   int counter = 0;
	   
		   for(int j = 0; j < suit ; j ++){
			   for(int k = 1 ; k < faceValue + 1 ; k++){ //card face value can not be 0
				   deck[counter] = new Card(j, k);
				   counter++;
			   }
		  }   
   }

   public static void shuffleDeck(Card deck[]){
       for (int i = 0; i < deck.length; i++)
       {
           // Generate a random number between 0 and 52.
           // Swap those two cards.
           int swapcard = (int)(Math.random() * 52);
           
           //A temporary array to hold the shuffled cards
           //and then replace the temp array wtih deck array.
           Card temp = deck[i];
           deck[i] = deck[swapcard];
           deck[swapcard] = temp;
       }
   }
   
   //Print deck array in index order
   public static void dealDeck(Card deck[]){
	   for(int i = 0; i < deck.length ; i ++){
			System.out.println(deck[i]);
		}
   }
   
   public int getSuit(){
	   return this.suit;
   }
   
   public int getFaceValue(){
	   return this.faceValue;
   }
   
   public void setFaceValue(int val){
	    this.faceValue = val;
   }
   
}