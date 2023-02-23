package pisti;

import java.util.ArrayList;
/**
 * Oyuncunun ay�rd��� kartlar� tutar ve puan hesaplamas�yla ilgili gerekli i�lemleri ger�ekle�tirir.
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
	 * envanterde tutulan kartlar� arraylist olarak d�nd�r�r.
	 * @return ArrayList Card de�erleri
	 */
	public ArrayList<Card> getInventory(){
		return this.inventory;
	}
	/**
	 * parametre olarak girilen destedeki t�m kartlar�, metodu �a��ran desteye ta��r
	 * @param table katlar�n al�naca�� deste 
	 */
	public void takeAllFromTable( Deck table ) {
		
		int tableSize= table.size();	
		
		for(int i= 0; i< tableSize ; i++) {
		inventory.add(table.showCard(0));
		table.removeCard(0);
		}
	}

	/**
	 * Oyunda pi�ti ger�ekle�irse pi�ti sayac�n� bir art�r�r.
	 */
	public void pisti() {
		pisti++;
	}
	
	/**
	 * mevcut skor de�erini d�nr�r�r
	 * @return int skor de�eri
	 */
	public int getScore() {
		return this.score;
	}
	/**
	 * Oyuncunun skor de�erini s�f�rlar
	 */
	public void clearScore() {
		score=0;
	}
	/**
	 * Oyun kurallar�na g�re puanlama yapar ve skor de�erini de�i�tirir.
	 * Kurallar;
	 * 1. E�er elde edilen kartlar 26 dan fazlaysa --> +3 \n
		 * 2. Her pi�ti --> +10 \n
		 * 2. Her duble pi�ti --> +20 \n
		 * 3. DIAMOND TEN --> +3 \n
		 * 4. CLUB TWO --> +2 \n
		 * 5. Her JACK --> +1 \n
		 * 6. Her ACE --> +1 \n
		 * 
		 * Kart say�m�nda pi�ti yap�lan kartlar�n kendisi de dahil edilir.
	 */
	public void scoreCalculate() {
		
		if (inventory.size()==0){ /* e�er envanterde hi� kart yoksa*/
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
	 * Kimin puan�n�n daha y�ksek oldu�unu hesaplar.
	 * @param rival kar�� oyuncunun Inventory nesnesi
	 * @return oyuncu kazan�rsa 0, rakip kazan�rsa -1, berabere ise 1 de�eri d�nd�r�r.
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
	 * �nventory i�indeki kartlar� metin olarak d�nd�r�r.
	 */
	public String toString() {
		String string = new String();
		
		for (int i=0 ; i< inventory.size() ; i++) {
			string += inventory.get(i);
		}
		
		return string;
	}
}
