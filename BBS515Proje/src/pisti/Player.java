package pisti;
/**
 * Oyuncu için gerekli nitelik özellikleri ve metodlarý bulundurur. Deck sýnýfýndan inherit edilmiþtir. 
 * @author EfeB
 *
 */
public class Player extends Deck {

	private static int IDCount=0;
	private int playerID;
	private int score;
	private Inventory playerInventory;
	
	
	/**
	 * yeni oyuncu yaratýr.
	 */
	public Player() {
		super();
		IDCount++;
		score=0;
		playerInventory = new Inventory();
		playerID= IDCount;
	}
	/**
	 * oyuncu kimliðini döndürür
	 * @return oyuncu no'su
	 */
	public int getPlayerID() {
		return this.playerID;
	}
	/**
	 * oyuncu skorunu döndürür.
	 * @return  oyuncu skoru
	 */
	public int getScore() {
		return this.score;
	}
	/**
	 * 
	 * Oyuncunun sahip olduðu envanter nesnesini döndürür
	 * @return envanter 
	 */
	public Inventory playerInventory() {
		return this.playerInventory;
	}
	/**
	 * skoru bir artýrarak günceller
	 */
	public void refreshScore() {
		score++;
	}
	
}
