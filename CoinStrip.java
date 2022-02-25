/**
 * This is a program that randomly places 5 coins labeled (1-5)
 * in numerical order from left to right across the board. The objective is to
 * move all of the coins to the far left spaces. The game is played between two
 * players and the last person to move the last coin to the left wins.
 * @author Jeremy Mednik
 */
 import java.util.Scanner;

 public class CoinStrip {

   /**
    * The three fields for the number of coins in on the board, the max gap
    * for placing them on the board and moving the coins, and an integer array
    * to represent the board.
    */
   private static final int NUM_COINS = 5;
   private static final int MAX_GAP = 3;
   private int[] strip = new int[20];

   /**
    * This is the constructer that randomly spreads the coins on a conceptual
    * strip of an array with a max gap of three spaces between them.
    */
   public CoinStrip() {
     //Represents the first index of the array.
     int coinPlace = 0;
     // The for loop goes through each coin and assigns a random gap between 1 to 3
     for (int j = 1; j <= NUM_COINS; j++) {
       int i =  coinPlace + (int) (Math.random()*MAX_GAP + 1);
       /**
        * Coinplace is updated to represent the strip index for the most recent
        * coin placed in the arrary.
        */
       coinPlace = i + 1;
       this.strip[i] = j;
     }
   }

   /**
    * This is a non-static string method which builds the game board into a string.
    * @return gameBoard - A string representation of the board.
    */
   public String toString() {
     String gameBoard = "| ";
     /**
      * A for loop that adds a number and a bar to the string if strip[a] is equal to
      * the value of one of the coins. Otherwise, the string adds additional Spaces
      * and a bar to represent the space is empty.
      */
     for (int a = 0; a < strip.length; a++) {
       if (this.strip[a] > 0) {
         gameBoard += this.strip[a] + " | ";
       }
       else {
         gameBoard += "  | ";
       }
     }
     return gameBoard;
   }

   /**
    * This is a non-static boolean method which checks if the 5th coin is in
    * strip[4]. That means that the other coins must be in the first 4 indexs
    * of the array.
    * @return true or false - A boolean value that determines if the game is over.
    */
   public boolean isGameOver(){
     if (strip[4] == 5) {
       return true;
     }
     return false;
   }

   /**
    * This is a non-static boolean method which checks if the move a user inputs when
    * they select a coin and the number
    * @param int coinNumber, int numSpaces - two integers stored from the terminal
    * when a user inputs which coin and how many spaces they want to move that coin.
    * @return true or false - A boolean value that determines if the move is legal.
    */
   public boolean isLegalMove(int coinNumber, int numSpaces) {
     int index = 0;
     int closestIndex = -1;
     //Returns false if the user inputs a value less than 1 or greater than 3 for numSpaces.
     if (numSpaces > 3 || numSpaces <= 0) {
       return false;
     }
     /**
      * A for loop that goes through the strip to determine what is the distance between
      * the coin a user wants to move and the next coin to the left of it.
      */
     for (int b = 0; b < this.strip.length; b++) {
       if (coinNumber != 1 && this.strip[b] == coinNumber - 1) {
         closestIndex = b;
       }
       //Finds the index for the coin you want to move in the strip.
       if (this.strip[b] == coinNumber) {
         index = b;
         //Ends the loop.
         break;
       }
     }
     /**
      * Returns false if the index of the coin - numSpaces exceeds index of 0 for the strip.
      * or if the index of the selected coin is less than or equal to the index of the next
      * closest coin after the coin is moved.
      */
     if (index-numSpaces < 0 || index-numSpaces <= closestIndex) {
       return false;
     }
     return true;
   }

   /**
    * This is a non-static boolean method which moves the coin and returns true.
    * @param int coinNumber, int numSpaces - two integers stored from the terminal
    * when a user inputs which coin and how many spaces they want to move that coin.
    * @return true after the coin is moved.
    */
   public boolean moveCoin(int coinNumber, int numSpaces) {
     /**
      * A for loop that moves the selected coin number to a new index and removes
      * the coin value in the old index.
      */
     for (int k = 0; k < this.strip.length; k++) {
       if(this.strip[k]==coinNumber) {
         this.strip[k] = 0;
         this.strip[k-numSpaces] = coinNumber;
       }
     }
     return true;
   }

   /**
    * This method is used to execute the other methods in this class. It keeps
    * track of the player numbers, scans what the user inputs in the terminal for
    * the coinNumber and numSpaces, and creates a CoinStrip object.
    */
   public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     CoinStrip obj = new CoinStrip();
     int playerNumber = 0;
     /**
      * While the game is not over, these methods will be executed each time a
      * player makes a valid move.
      */
     while(obj.isGameOver() == false) {
       System.out.println();
       System.out.println(obj.toString());
       System.out.print("Player " + (playerNumber % 2 + 1) + ": " );
       System.out.print("What Coin Do You Want to Move? : ");
       int coinNumber = sc.nextInt();
       System.out.print("How Many Spaces Do You Want to Move? : ");
       int numSpaces = sc.nextInt();
       //If the move is legal, the coin will be moved to the new index.
       if(obj.isLegalMove(coinNumber,numSpaces)) {
         obj.moveCoin(coinNumber, numSpaces);
         playerNumber++;
       }
       //Based off of if the move is not legal and resets for the same player to go again.
       else{
         System.out.println("Sorry that's not a legal move");
       }
     }
     int winner = (playerNumber + 1) % 2 + 1;
     System.out.println("Game over! Player " + winner + " wins!");
   }
 }
