package pisti;
/**
 * Oyuncu i�in gerekli nitelik �zellikleri ve metodlar� bulundurur. Deck s�n�f�ndan inherit edilmi�tir. 
 * @author EfeB
 *
 */
public class Player extends Deck {

	private static int IDCount=0;
	private int playerID;
	private int score;
	private Inventory playerInventory;
	
	
	/**
	 * yeni oyuncu yarat�r.
	 */
	public Player() {
		super();
		IDCount++;
		score=0;
		playerInventory = new Inventory();
		playerID= IDCount;
	}
	/**
	 * oyuncu kimli�ini d�nd�r�r
	 * @return oyuncu no'su
	 */
	public int getPlayerID() {
		return this.playerID;
	}
	/**
	 * oyuncu skorunu d�nd�r�r.
	 * @return  oyuncu skoru
	 */
	public int getScore() {
		return this.score;
	}
	/**
	 * 
	 * Oyuncunun sahip oldu�u envanter nesnesini d�nd�r�r
	 * @return envanter 
	 */
	public Inventory playerInventory() {
		return this.playerInventory;
	}
	/**
	 * skoru bir art�rarak g�nceller
	 */
	public void refreshScore() {
		score++;
	}
	
}
