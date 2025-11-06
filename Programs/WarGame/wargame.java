import java.util.Random;
import java.util.Scanner;


public class wargame {

	public static int maxCards = 52;
	public static Scanner s = new Scanner(System.in);
	public static int maxRounds = 10000;

	public static void main(String[] args){
		
		//VARIABLES
		Card[] deck = new Card[maxCards];
		Stack p1 = new Stack();
		Stack p2 = new Stack();
		Stack p3 = new Stack();
		Stack p4 = new Stack();
		Stack w1 = new Stack();
		Stack w2 = new Stack();
		Stack w3 = new Stack();
		Stack w4 = new Stack();
		Stack display = new Stack();
		boolean running = true;
		String winner = "";
		String noWinner = "";

		deck = makeDeck(maxCards);
		printDeck(deck);
		shuffle(deck);
		printDeck(deck);
		deal(deck,p1,p2,p3,p4);

		//PLAYER NAME SCANNER + MAX ROUNDS
		System.out.print("Please enter Player 1's name: ");
		String p1Name = s.next();
		System.out.println();

		System.out.print("Please enter Player 2's name: ");
		String p2Name = s.next();
		System.out.println();

		System.out.print("Please enter Player 3's name: ");
		String p3Name = s.next();
		System.out.println();

		System.out.print("Please enter Player 4's name: ");
		String p4Name = s.next();
		System.out.println();

		System.out.print("How many rounds would you like? ");
		maxRounds = s.nextInt();
		System.out.println();

		//THE GAME
		int round = 0 ;
		while (running){
			//System.out.println("Hit ENTER to play next card");
			//s.nextLine();
			//Four Players
			playNext(p1,p2,p3,p4,w1,w2,w3,w4,p1Name,p2Name,p3Name,p4Name);
			checkEmpty(p1,w1,p1Name);
			checkEmpty(p2,w2,p2Name);
			checkEmpty(p3,w3,p3Name);
			checkEmpty(p4,w4,p4Name);

			int r = (round)%200;
			if (r==0 && round!=0) {
				System.out.println("***ROUND NUMBER "+round+"***");
				System.out.println("Player 1:");
				playerRank(p1,w1,display);
				System.out.println("Player 2:");
				playerRank(p2,w2,display);
				System.out.println("Player 3:");
				playerRank(p3,w3,display);
				System.out.println("Player 4:");
				playerRank(p4,w4,display);
			}

			//ONE PLAYER REMAINING
			if (p1.empty() && w1.empty() && p3.empty() && w3.empty() && p4.empty() && w4.empty()){
				winner = p2Name;
				running = false;
			}
			else if (p2.empty() && w2.empty() && p3.empty() && w3.empty() && p4.empty() && w4.empty()){
				winner = p1Name;
				running = false;
			}	
			else if (p1.empty() && w1.empty() && p2.empty() && w2.empty() && p4.empty() && w4.empty()){
				winner = p3Name;
				running = false;
			}
			else if (p2.empty() && w2.empty() && p3.empty() && w3.empty() && p1.empty() && w1.empty()){
				winner = p4Name;
				running = false;
			}	

			round++;
			if (round == maxRounds){
				running = false;
				System.out.println("\nMax Rounds reached!\n*** " + p1Name + "***");
				printStack(p1);
				printStack(w1);
				System.out.println("\n*** " + p2Name + "***");
				printStack(p2);
				printStack(w2);
				System.out.println("\n*** " + p3Name + "***");
				printStack(p3);
				printStack(w3);
				System.out.println("\n*** " + p4Name + "***");
				printStack(p4);
				printStack(w4);
			}

		}
		if (winner == noWinner) {
			System.out.println("There is no winner in "+round+" rounds\nGoodbye");
		}
		else {
			System.out.println("\nThe winner is " + winner + " in "+round+" rounds\nGoodbye");
		}
		s.close();
	}//END OF MAIN

	//METHODS
	public static void checkEmpty(Stack playDeck, Stack winStack, String player){
		if (playDeck.empty() && winStack.empty()==false){
			System.out.println("Flipping Win Stack for " + player);
			while (winStack.notEmpty()){
				Card c = winStack.pop();
				if (c == null)
					return;
				playDeck.push(c);
				System.out.printf("%s%s ",c.getNumber(),c.getSuit());
			}
			System.out.println("\nComplete");
			System.out.println();
		}
	}//END OF CHECK EMPTY

	public static Card[] makeDeck(int maxCards){
		Card[] deck = new Card[maxCards];

		int cardIndex = 0;
		for(int s = 0; s <=3 ;s++){
			String n = "";
			char suit = ' ';
			switch (s) {
			case 0: //SPADE
				suit = (char)'\u2660';
				break;
			case 1: //DIAMOND:
				suit = (char)'\u2666';
				break;
			case 2: //CLUB:
				suit = (char)'\u2663';
				break;
			case 3: //HEART:
				suit = (char)'\u2764';
				break;
			}

			for(int v = 2; v <= 14; v++){
				if(v == 11)
					n = "J";
				else if (v == 12)
					n = "Q";
				else if (v == 13)
					n = "K";
				else if (v == 14)
					n = "A";
				else
					n = "" + v;
				
				Card c = new Card(n,suit,v);
				deck[cardIndex] = c;
				cardIndex++;

			}
		}

		return deck;
	}//END OF MAKE DECK

	public static void printDeck(Card[] d){
		System.out.println("\nCurrent Deck\n***********");
		for(int x = 0; x < d.length; x++){
			System.out.printf("%s%s - %d\n",d[x].getNumber(),d[x].getSuit(),d[x].getValue());
		}
	}//END OF PRINT DECK

	public static void printStack(Stack s){ 
		//DEBUGGING PURPOSES
		System.out.println("\nCurrent Hand\n***********");

		Card c = s.pop();
		while (c != null){
			System.out.printf("%s%s\n", c.getNumber(), c.getSuit());
			c = s.pop();
		}
	}//END OF PRINT STACK


	public static void shuffle(Card[] deck){
		Random rand = new Random();

		for (int i = 0; i < deck.length; i++){
			int randomIndexToSwap = rand.nextInt(deck.length);
			Card temp = deck[randomIndexToSwap];
			deck[randomIndexToSwap] = deck[i];
			deck[i] = temp;
		}		
	}//END OF SHUFFLE

	public static void deal(Card[] deck, Stack p1, Stack p2, Stack p3, Stack p4){
		for (int card = 0; card < maxCards; card+=4){
			p1.push(deck[card]);
			p2.push(deck[card+1]);
			p3.push(deck[card+2]);
			p4.push(deck[card+3]);
		}

	}//END OF DEAL

	public static void playerRank(Stack hand, Stack win, Stack display){ 
		System.out.println("Main hand:");

		while (hand.notEmpty()){
			Card c = hand.pop();
			if (c == null)
				return;
			display.push(c);
			System.out.printf("%s%s ",c.getNumber(),c.getSuit());
		}

		System.out.println("\n");
		while (display.notEmpty()){
			Card c = display.pop();
			if (c == null)
				return;
			hand.push(c);
		}

		System.out.println("Win deck:");
		while (win.notEmpty()){
			Card c = win.pop();
			if (c == null)
				return;
			display.push(c);
			System.out.printf("%s%s ",c.getNumber(),c.getSuit());
		}

		System.out.println("\n");
		while (display.notEmpty()){
			Card c = display.pop();
			if (c == null)
				return;
			win.push(c);
		}
	}//END OF PLAYER RANK

	public static void playLoser(Stack player) {
		Card loser = new Card(" ",' ',0);
		player.push(loser);
	}//END OF PLAY LOSER

	//PLAYNEXT
	public static Stack playNext (Stack player1, Stack player2, Stack player3, Stack player4, Stack win1, Stack
			win2, Stack win3, Stack win4, String p1Name, String p2Name, String p3Name, String p4Name){
		for (int i=0;i<1;i++) {
			if (player1.empty() && win1.empty())
			playLoser(player1);
			if (player2.empty() && win2.empty())
			playLoser(player2);
			if (player3.empty() && win3.empty())
			playLoser(player3);
			if (player4.empty() && win4.empty())
			playLoser(player4);
		}
		
		Card c1 = player1.pop();
		Card c2 = player2.pop();
		Card c3 = player3.pop();
		Card c4 = player4.pop();

		System.out.printf("%s%s %s%s %s%s %s%s\n",c1.getNumber(),c1.getSuit(),c2.getNumber(),c2.getSuit(),
				c3.getNumber(),c3.getSuit(),c4.getNumber(),c4.getSuit());
		Stack getAll = null;
		
		if ((c1.getValue() > c2.getValue()) && (c1.getValue()>c3.getValue()) && (c1.getValue()>c4.getValue())){
			if (c1.getValue()!=0)
				win1.push(c1);
			if (c2.getValue()!=0)
				win1.push(c2);
			if (c3.getValue()!=0)
				win1.push(c3);
			if (c4.getValue()!=0)
				win1.push(c4);
			System.out.println(p1Name+" wins!\n");
			getAll = win1;
		}
		else if ((c2.getValue() > c1.getValue())&&(c2.getValue() > c3.getValue())&&(c2.getValue() > c4.getValue())){
			if (c1.getValue()!=0)
				win2.push(c1);
			if (c2.getValue()!=0)
				win2.push(c2);
			if (c3.getValue()!=0)
				win2.push(c3);
			if (c4.getValue()!=0)
				win2.push(c4);
			System.out.println(p2Name+" wins!\n");
			getAll = win2;
		}
		else if ((c3.getValue() > c1.getValue())&&(c3.getValue() > c2.getValue())&&(c3.getValue() > c4.getValue())){
			if (c1.getValue()!=0)
				win3.push(c1);
			if (c2.getValue()!=0)
				win3.push(c2);
			if (c3.getValue()!=0)
				win3.push(c3);
			if (c4.getValue()!=0)
				win3.push(c4);					
			System.out.println(p3Name+" wins!\n");
			getAll = win3;
		}
		else if ((c4.getValue() > c1.getValue())&&(c4.getValue() > c2.getValue())&&(c4.getValue() > c3.getValue())){
			if (c1.getValue()!=0)
				win4.push(c1);
			if (c2.getValue()!=0)
				win4.push(c2);
			if (c3.getValue()!=0)
				win4.push(c3);
			if (c4.getValue()!=0)
				win4.push(c4);				
			System.out.println(p4Name+" wins!\n");
			getAll = win4;
		}
		else{ //EQUAL
			System.out.println("**BATTLE**");
			checkEmpty(player1,win1,p1Name);
			checkEmpty(player2,win2,p2Name);
			checkEmpty(player3,win3,p3Name);
			checkEmpty(player4,win4,p4Name);
			LL battleCards = new LL();
			battleCards.add(c1);
			battleCards.add(c2);
			battleCards.add(c3);
			battleCards.add(c4);
			Card CardMax = playBattle(battleCards);
			if (c1.getValue() != CardMax.getValue())
				playLoser(player1);
			if (c2.getValue() != CardMax.getValue())
				playLoser(player2);
			if (c3.getValue() != CardMax.getValue())
				playLoser(player3);
			if (c4.getValue() != CardMax.getValue())
				playLoser(player4);
		
			
			getAll = playNext(player1, player2, player3, player4, win1, win2, win3, win4, p1Name, p2Name, p3Name, p4Name);
			if (c1.getValue()!=0)
			getAll.push(c1);
			if (c2.getValue()!=0)
			getAll.push(c2);
			if (c3.getValue()!=0)
			getAll.push(c3);
			if (c4.getValue()!=0)
			getAll.push(c4);
		}

		return getAll;
}//END OF PLAYNEXT
	
	//DETERMINES CARD VALUE FOR TIES
	public static Card playBattle (LL battleCards) {
		Card current = battleCards.getFirst();
		Card tieCard = battleCards.getNextElement();
		while (current != null) {
			if (current.getValue() > tieCard.getValue()) {
			tieCard = current;
			}
		current = battleCards.getNextElement();
		}
		return tieCard;
				
	}//END OF PLAY BATTLE
			
}





