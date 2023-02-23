package pisti;

import java.util.ArrayList;
/**
 * Oyuncunun ayýrdýðý kartlarý tutar ve puan hesaplamasýyla ilgili gerekli iþlemleri gerçekleþtirir.
 * @author EfeB
 *
 */
public class Inventory {
	private ArrayList<Card> inventory ;
	private int pisti;
	private int score; 
	
	/**
	 * constructor.
	 */
	public Inventory() {
		
		inventory= new ArrayList<Card>();
		score=0;
		pisti=0;
	}
	/**
	 * envanterde tutulan kartlarý arraylist olarak döndürür.
	 * @return ArrayList Card deðerleri
	 */
	public ArrayList<Card> getInventory(){
		return this.inventory;
	}
	/**
	 * parametre olarak girilen destedeki tüm kartlarý, metodu çaðýran desteye taþýr
	 * @param table katlarýn alýnacaðý deste 
	 */
	public void takeAllFromTable( Deck table ) {
		
		int tableSize= table.size();	
		
		for(int i= 0; i< tableSize ; i++) {
		inventory.add(table.showCard(0));
		table.removeCard(0);
		}
	}

	/**
	 * Oyunda piþti gerçekleþirse piþti sayacýný bir artýrýr.
	 */
	public void pisti() {
		pisti++;
	}
	
	/**
	 * mevcut skor deðerini dönrürür
	 * @return int skor deðeri
	 */
	public int getScore() {
		return this.score;
	}
	/**
	 * Oyuncunun skor deðerini sýfýrlar
	 */
	public void clearScore() {
		score=0;
	}
	/**
	 * Oyun kurallarýna göre puanlama yapar ve skor deðerini deðiþtirir.
	 * Kurallar;
	 * 1. Eðer elde edilen kartlar 26 dan fazlaysa --> +3 \n
		 * 2. Her piþti --> +10 \n
		 * 2. Her duble piþti --> +20 \n
		 * 3. DIAMOND TEN --> +3 \n
		 * 4. CLUB TWO --> +2 \n
		 * 5. Her JACK --> +1 \n
		 * 6. Her ACE --> +1 \n
		 * 
		 * Kart sayýmýnda piþti yapýlan kartlarýn kendisi de dahil edilir.
	 */
	public void scoreCalculate() {
		
		if (inventory.size()==0){ /* eðer envanterde hiç kart yoksa*/
			score=0;
		}
		else {
			
		if (inventory.size()>26)
			score+=3;
		
		for (int i= 0; i<pisti ; i++) {
			score+=10;
		}

		for ( int i= 0; i< inventory.size() ; i++) {
			
			if (inventory.get(i).valueCompare(Value.ON) && 
					inventory.get(i).typeCompare(Type.KARO)) 

				score+=3;
			
			if (inventory.get(i).valueCompare(Value.IKI) &&
					inventory.get(i).typeCompare(Type.SINEK)	) 

				score+=2;
			
			if (inventory.get(i).valueCompare(Value.VALE) )

				score++;

			if (inventory.get(i).valueCompare(Value.AS) )

				score++;
		}
		}
	}
		
	/**
	 * Kimin puanýnýn daha yüksek olduðunu hesaplar.
	 * @param rival karþý oyuncunun Inventory nesnesi
	 * @return oyuncu kazanýrsa 0, rakip kazanýrsa -1, berabere ise 1 deðeri döndürür.
	 */
	public int whoIsWinner(Inventory rival) {
		
		System.out.println("Oyuncu 1 Skoru: " + this.getScore());
		System.out.println("Oyuncu 2 Skoru: " + rival.getScore());
		
		if (this.score > rival.score)
			return 0;
		if (this.score == rival.score)
			return 1;
		else
			return -1;
	}
	
	@Override
	/**
	 * Ýnventory içindeki kartlarý metin olarak döndürür.
	 */
	public String toString() {
		String string = new String();
		
		for (int i=0 ; i< inventory.size() ; i++) {
			string += inventory.get(i);
		}
		
		return string;
	}
}
