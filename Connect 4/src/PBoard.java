import java.util.Scanner;


public class PBoard{

	private Player red, blue; // players blue&red
	private Player[][] board = new Player[6][6]; // 6 x 6 board
	private int turn; //turn = 0 = red ;; turn = 1 = blue
	private boolean go;
	
	public PBoard()
	{
		red = new Player("red");
		blue = new Player("blue");
		turn = 0;
		go = false;
		
	}
	
	/**
	 * Adds the chip to the selected Column #
	 * @param n the column number you want to add a chip into
	 */
	public void addToColumn(int n)
	{
		n--;
		if(n > - 1 && n < 6){
			int i = 5;
			while(i >= 0 && board[i][n] != null)
			{
				i--;
			}
			if(i >= 0){
				if(board[i][n] == null){
					if(turn == 0)//red's turn
					{
						board[i][n] = red;
						turn++;//give it to blue now
					}else{//blue's turn
						board[i][n] = blue;
						turn--;//give it to red now
					}
				}
				if(checkWin(i, n)){
					if(red.isVictory()){
						System.out.println("\nRed is the winner!!!");
					}else{
						System.out.println("\nBlue is the winner!!!");
					}
				}
			}else{
				System.out.println("\nColumn is full! Pick another Column");
			}
		}else{
			System.out.println("Out of Bounds");
		}
	}
			
	/**
	 * Restarts the current game
	 */
	public void restart() {
		System.out.println("\nRestarting the game and creating a new game board...");
		red.setVictory(false);System.out.println(".");
		blue.setVictory(false);System.out.println("..");
		board = new Player[6][6];System.out.println("...");
		turn = 0;System.out.println("..");
		go = false;System.out.println(".");
		System.out.println("The board is restarted.");
	}

	/**
	 * Privately used by "addToColumn" to check after each insertion if the game has a winner
	 * @param i the column number
	 * @param n the row number
	 * @return returns true if there is a winner and false if there is no winner
	 */
	private boolean checkWin(int i, int n)
	{
		if(vertical(i, n, board[i][n], "") >= 4 || horizontal(i, n, board[i][n], "") >= 4 || diagonalLeft(i, n, board[i][n], "") >= 4 || diagonalRight(i, n, board[i][n], "") >= 4){
			if(board[i][n] == red){
				red.setVictory(true);
			}else{
				blue.setVictory(true);
			}
			go = true;
			return true;
		}
		return false;
	}
	/*
	[i-1, j-1] [i-1, j] [i-1, j+1]
	[i  , j-1] [i  , j] [i  , j+1]
	[i+1, j-1] [i+1, j] [i+1, j+1]
	*/
	
	
	private int vertical(int i, int n, Player player, String direction) 
	{
		int count = 0;
		if(i >= 0 && i < 6 && n >= 0 && n < 6){
			if(board[i][n] == player){
				count++;
				if(direction.equals("")){
					count += vertical(i-1, n, player, "up");
					count += vertical(i+1, n, player, "down");
				}else if(direction.equals("up")){
					count += vertical(i-1, n, player, "up");
				}else if(direction.equals("down")){
					count += vertical(i+1, n, player, "down");
				}
				
			}
		}
		return count;
	}

	private int horizontal(int i, int n, Player player, String direction) {
		int count = 0;
		if(i >= 0 && i < 6 && n >= 0 && n < 6){
			if(board[i][n] == player){
				count++;
				if(direction.equals("")){
					count += horizontal(i, n-1, player, "left");
					count += horizontal(i, n+1, player, "right");
				}else if(direction.equals("left")){
					count += horizontal(i, n-1, player, "left");
				}else if(direction.equals("right")){
					count += horizontal(i, n+1, player, "right");
				}
				
			}
		}
		return count;
	}

	private int diagonalLeft(int i, int n, Player player, String direction) {
		int count = 0;
		if(i >= 0 && i < 6 && n >= 0 && n < 6){
			if(board[i][n] == player){
				count++;
				if(direction.equals("")){
					count += diagonalLeft(i-1, n-1, player, "leftUp");
					count += diagonalLeft(i+1, n+1, player, "rightDown");
				}else if(direction.equals("leftUp")){
					count += diagonalLeft(i-1, n-1, player, "leftUp");
				}else if(direction.equals("rightDown")){
					count += diagonalLeft(i+1, n+1, player, "rightDown");
				}
				
			}
		}
		return count;
	}
	
	private int diagonalRight(int i, int n, Player player, String direction) {
		int count = 0;
		if(i >= 0 && i < 6 && n >= 0 && n < 6){
			if(board[i][n] == player){
				count++;
				if(direction.equals("")){
					count += diagonalRight(i+1, n-1, player, "leftDown");
					count += diagonalRight(i-1, n+1, player, "rightUp");
				}else if(direction.equals("leftDown")){
					count += diagonalRight(i+1, n-1, player, "leftDown");
				}else if(direction.equals("rightUp")){
					count += diagonalRight(i-1, n+1, player,  "rightUp");
				}
				
			}
		}
		return count;
	}

	/**
	 * Prints the gameboard, including placement of red and blue chips, in cmd
	 */
	public void print()
	{
		System.out.print("x  1 x x  2 x x  3 x x  4 x x  5 x x  6 x ");
		for(int i = 0; i < 6; i++){
			System.out.println("");
			for(int j = 0; j < 6; j++){
				if(board[i][j] == null){
					System.out.print("x    x ");
				}else if(board[i][j].getColor().equals("blue")){
					System.out.print(" blue  ");
				}else{
					System.out.print(" red   ");
				}
			}
		}
	}
	
	/**
	 * Returns a player that's occupying a certain position i,n
	 * @param i the column number
	 * @param j the row number
	 * @return player that's on i,n
	 */
	public Player get(int i, int j)
	{
		return board[i][j];
	}
	
	/**
	 * Checks to see if a player won 
	 * @return true if there is a winner, false if there is no winner
	 */
	public boolean go()
	{
		return go;
	}

	
	public static void main(String[] args)
	{
		PBoard board = new PBoard();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while(n != 99){
			board.addToColumn(n);
			board.print();
			if(board.go())  
			{
				n = 99;
				System.out.println("\n\nGame Over");
			}else{
				n = sc.nextInt();
			}
		}
		
		
		
	}
}
