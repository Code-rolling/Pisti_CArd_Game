package pisti;

import java.util.ArrayList;
import java.util.Random;
/**
 * Deste elemanlar�n�n ne t�r metodlara sahip olaca��n� tan�mlar.
 * GroupOfCards soyut s�n�f�ndan t�retilmi�tir.
 * @author EfeB
 *
 */
public class Deck extends GroupOfCards {

	private ArrayList<Card> deck;

/**
 * constuctor
 */
	public Deck() {

		deck= new ArrayList<Card>();
	}
	// DECK METODLARI
	/**
	 * Destedeki elemanlar� arraylist olarak getirir.
	 * @return ArrayList Cards
	 */
	public ArrayList<Card> getDeck(){
		return this.deck;
	}
	@Override
	public int size() {
		return deck.size();
	}
	
/**
 * bir kart destesindeki t�m elemanlar� olu�turur.
 * E�er parametre true ise demo modu i�in �nceden belirlenmi� kartlar desteye eklenir.
 * @param demoMode e�er true ise demo modu, false ise orijinal mod �al��t�r�l�r.
 */
	public void createFullDeck(boolean demoMode) {
		
		if (demoMode) {
			deck.add(new Card(Type.SINEK, Value.AS));
			deck.add(new Card(Type.SINEK, Value.VALE));
			deck.add(new Card(Type.SINEK, Value.BES));
			deck.add(new Card(Type.KARO, Value.BES));
			deck.add(new Card(Type.KARO, Value.VALE));
			deck.add(new Card(Type.MACA, Value.VALE));
			deck.add(new Card(Type.MACA, Value.AS));
			deck.add(new Card(Type.KUPA, Value.AS));
			deck.add(new Card(Type.KUPA, Value.ALTI));
			deck.add(new Card(Type.KARO, Value.IKI));
			deck.add(new Card(Type.MACA, Value.PAPAZ));
			deck.add(new Card(Type.SINEK, Value.KIZ));
			deck.add(new Card(Type.SINEK, Value.DOKUZ));
		}
		
		else {
			
			for (Type newType : Type.values()) {
				for (Value newValue : Value.values()) {
					deck.add(new Card(newType, newValue));
				}
			}
		}
		
	}
	/**
	 * destedeki kartlar� kar��t�r. 
	 */
	public void shuffle() {

		Random random = new Random();
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		int listSize = deck.size();
		int index=0;
		
		/**Kartlar�lar� unified dist. �ekilde kar��t�rman�n yolu: ( ((max-min)+1)+min )
		 *  
		 * Anlam�; random metodu s�f�r ile bir aras�nda de�er verir. Bu aral��� geni�letmek i�in arzu
		 * etti�imiz say� aral��� ile �arpar�z. aral�kta s�n�r de�erlerin de dahil olmas�n� sa�lamak i�in +1 
		 * ile toplar�z. Arzu edilen aral��� belirlediktan sonra bu aral���n hangi say�dan ba�lad���n� belirtmek
		 * i�in de aral���n minimum de�eriyle toplar�z.
		 */
		for (int i=0 ; i< listSize ; i++) {
			index = random.nextInt(((deck.size()-1) - 0) + 1 + 0);
			tmpDeck.add(deck.get(index));
			deck.remove(index);
		}
		deck = tmpDeck;
		

	}

	//CARD METODLARI 
	
	public Card showCard(int index)   {
		
		try {
			
		return this.deck.get(index);
		
		} catch (Exception e) {
			/*metodumuz mutlaka bir Card de�eri d�nd�rmesi gerekti�i i�in, hatayla kar��la�t���nda
			 * bo� bir Card de�eri d�nd�r�r.*/
			return new Card(null,null);
		}
	} 

	public void removeCard(int index) {
		try {
			
		this.deck.remove(index);
		
	} catch (Exception e) {}
	}
	/**
	 * Kart nesnesi d�nd�ren metodlar hatayla kar��la�t�klar�nda bo� kart nesnesi olu�tururlar.Bu bo� kartlar�n 
	 * deste ve oyuncular�n ellerinde birikmemesi i�in kullan�l�r.
	 */
	public void removeEmptyCards() {
		int size= this.size();
		for(int i=0 ; i<size ; i++) {
			this.deck.remove(new Card(null,null));
		}
	}
	
	/**Destede konumu belli bir kart�n di�er desteye aktar�lmas�n� sa�lar
	 * 
	 * @param comingFrom kart al�nacak deste
	 * @param index al�nacak kart�n konumu
	 */
	//@Overload
	public void drawCard( Deck comingFrom, int index) {
		try {
			
				this.deck.add(comingFrom.showCard(index));
				comingFrom.removeCard(index);
			
		}catch(Exception e) {
			System.out.println("destede da��t�lacak kart kalmad�");
			
			/*showCard metodunda hata al�n�rsa  listelerde bo� Card nesneleri olu�ur.bunun programa etki
			 * etmesini engellemek i�in bu de�erler silinir.*/
			
			comingFrom.removeEmptyCards();
		}
	}

	/**
	 * Destedeki ilk kart�n di�er desteye aktar�lmas�n� sa�lar
	 * @param comingFrom al�nacak kart�n destesi
	 */
	public void drawCard( Deck comingFrom) {
		try {
			
				this.deck.add(comingFrom.showCard(0));
				comingFrom.removeCard(0);
			
	}catch(Exception e) {
		System.out.println("destede da��t�lacak kart kalmad�");
		comingFrom.removeEmptyCards();
	}
	}
	/**
	 * �ki farkl� destede bulunan kartlar�n de�erlerini kar��la�t�r�r. E�er masada tek kart varsa
	 * istisna yakalan�r ve flse de�eri d�nd�r�l�r.
	 * 
	 * @return E�er kartlar e�le�irse true, �b�r durumlarda false
	 */
	public boolean compareTableCards() {
		
		/**/
		try {
			return this.deck.get( this.size()-1 ).valueCompare( this.deck.get( this.size()-2 ).getCardValue() );
		
		} catch (Exception e) {
			return false;}
	}
	
	/**
	 * Bir destedeki t�m kartlar� ba�ka bir desteye aktar�r.
	 * @param comingFrom kartlar�n al�naca�� destenin kart listesi
	 */
	public void moveAllToDeck(ArrayList<Card> comingFrom) {
		
		int comingFromDeckSize = comingFrom.size();
		
		try{

			for (int i=0 ; i< comingFromDeckSize ; i++) {
				this.deck.add(comingFrom.get(i));	
			}

			for (int i=0 ; i< comingFromDeckSize ; i++) {
				comingFrom.remove(i);
			}
		}catch (Exception e) {}
		
	}

	/**
	 * desteyi metin format�nda d�nd�r�r.
	 * 
	 */
	public String toString() {
		
		int size=deck.size();
		String string ="\n";
		
		for (int i=0 ;i<size ; i++ ) {
			
			string+=deck.get(i);
			
			if(i+1!=size)
			string+=",";
		}
	return string;
	}

}
