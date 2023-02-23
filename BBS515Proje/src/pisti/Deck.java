package pisti;

import java.util.ArrayList;
import java.util.Random;
/**
 * Deste elemanlarýnýn ne tür metodlara sahip olacaðýný tanýmlar.
 * GroupOfCards soyut sýnýfýndan türetilmiþtir.
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
	 * Destedeki elemanlarý arraylist olarak getirir.
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
 * bir kart destesindeki tüm elemanlarý oluþturur.
 * Eðer parametre true ise demo modu için önceden belirlenmiþ kartlar desteye eklenir.
 * @param demoMode eðer true ise demo modu, false ise orijinal mod çalýþtýrýlýr.
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
	 * destedeki kartlarý karýþtýr. 
	 */
	public void shuffle() {

		Random random = new Random();
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		int listSize = deck.size();
		int index=0;
		
		/**Kartlarýlarý unified dist. þekilde karýþtýrmanýn yolu: ( ((max-min)+1)+min )
		 *  
		 * Anlamý; random metodu sýfýr ile bir arasýnda deðer verir. Bu aralýðý geniþletmek için arzu
		 * ettiðimiz sayý aralýðý ile çarparýz. aralýkta sýnýr deðerlerin de dahil olmasýný saðlamak için +1 
		 * ile toplarýz. Arzu edilen aralýðý belirlediktan sonra bu aralýðýn hangi sayýdan baþladýðýný belirtmek
		 * için de aralýðýn minimum deðeriyle toplarýz.
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
			/*metodumuz mutlaka bir Card deðeri döndürmesi gerektiði için, hatayla karþýlaþtýðýnda
			 * boþ bir Card deðeri döndürür.*/
			return new Card(null,null);
		}
	} 

	public void removeCard(int index) {
		try {
			
		this.deck.remove(index);
		
	} catch (Exception e) {}
	}
	/**
	 * Kart nesnesi döndüren metodlar hatayla karþýlaþtýklarýnda boþ kart nesnesi oluþtururlar.Bu boþ kartlarýn 
	 * deste ve oyuncularýn ellerinde birikmemesi için kullanýlýr.
	 */
	public void removeEmptyCards() {
		int size= this.size();
		for(int i=0 ; i<size ; i++) {
			this.deck.remove(new Card(null,null));
		}
	}
	
	/**Destede konumu belli bir kartýn diðer desteye aktarýlmasýný saðlar
	 * 
	 * @param comingFrom kart alýnacak deste
	 * @param index alýnacak kartýn konumu
	 */
	//@Overload
	public void drawCard( Deck comingFrom, int index) {
		try {
			
				this.deck.add(comingFrom.showCard(index));
				comingFrom.removeCard(index);
			
		}catch(Exception e) {
			System.out.println("destede daðýtýlacak kart kalmadý");
			
			/*showCard metodunda hata alýnýrsa  listelerde boþ Card nesneleri oluþur.bunun programa etki
			 * etmesini engellemek için bu deðerler silinir.*/
			
			comingFrom.removeEmptyCards();
		}
	}

	/**
	 * Destedeki ilk kartýn diðer desteye aktarýlmasýný saðlar
	 * @param comingFrom alýnacak kartýn destesi
	 */
	public void drawCard( Deck comingFrom) {
		try {
			
				this.deck.add(comingFrom.showCard(0));
				comingFrom.removeCard(0);
			
	}catch(Exception e) {
		System.out.println("destede daðýtýlacak kart kalmadý");
		comingFrom.removeEmptyCards();
	}
	}
	/**
	 * Ýki farklý destede bulunan kartlarýn deðerlerini karþýlaþtýrýr. Eðer masada tek kart varsa
	 * istisna yakalanýr ve flse deðeri döndürülür.
	 * 
	 * @return Eðer kartlar eþleþirse true, öbür durumlarda false
	 */
	public boolean compareTableCards() {
		
		/**/
		try {
			return this.deck.get( this.size()-1 ).valueCompare( this.deck.get( this.size()-2 ).getCardValue() );
		
		} catch (Exception e) {
			return false;}
	}
	
	/**
	 * Bir destedeki tüm kartlarý baþka bir desteye aktarýr.
	 * @param comingFrom kartlarýn alýnacaðý destenin kart listesi
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
	 * desteyi metin formatýnda döndürür.
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
