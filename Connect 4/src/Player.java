
public class Player {

	private String color;
	private boolean victory;
	
	/**
	 * Constructs a new instance of Player initializing color and victory = false
	 * @param color the color of the player as a string
	 */
	public Player(String color)
	{
		this.color = color;
		victory = false;
	}
	/**
	 * Returns the color of a this player
	 * @return color as a string
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Sets a new color for this player
	 * @param color the color, as a string, you want to change to
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * If this player won, boolean victory will be changed to true
	 * @return true if this player is a winner, false if this player is not the winner
	 */
	public boolean isVictory() {
		return victory;
	}
	/**
	 * Changes the boolean victory of this player 
	 * @param victory boolean true if this player is the winner
	 */
	public void setVictory(boolean victory) {
		this.victory = victory;
	}
	
	
}
