package pisti;
/**
 * Destenin yarat�lmas� i�in gerekli kart nesnesini ve kartlar�n kar��la�t�r�lmas� i�in gerekli olan
 * metodlar� bar�nd�r�r.
 * @author EfeB
 *
 */
public class Card {
	
	/*Type ve Value de�erleri birer enum d�r. Kart de�erleri oyun i�inde de�i�meyece�i i�in 
	 * kodlamada rahatl�k sa�lar.*/
	
	private Value value;
	private Type type;
	/**
	 * constructor.
	 * @param type kart tipi
	 * @param value kart de�eri
	 */
	public Card(Type type, Value value) {
		this.value = value;
		this.type = type;
	}
	/**
	 * Kart de�erini d�nd�r�r.
	 * @return kart de�eri
	 */
	public Value getCardValue() {
		return this.value;
	}
	/**
	 * Kart tipini d�nd�r�r.
	 * @return kart tipi
	 */
	public Type getCardType() {
		return this.type;
	}
	
	
	/**
	 * iki kart de�erini kar��la�t�r.
	 * 
	 * @param aCard kar��la�t�r�lmas� istenen kart
	 * @return kar��la�t�r�lan kartlar�n de�er ve tipi ayn�ysa true d�nd�r�r. farkl�ysa veya kart null de�erse
	 * false d�nd�r�r.
	 */
	public boolean compareTo(Card aCard) {
		try {
		if( this.value==aCard.value  && this.type==aCard.type) 
			return true;
		else
			return false;

	}catch(Exception e) {
		return false;}
	}
	/**
	 * Kart�n de�erini verilen de�er ile kar��la�t�r�r.
	 * @param value kar��la�t�r�lmak istenen de�er
	 * @return boolean e�er de�erler ayn�ysa true, farkl�ysa false d�nd�r�r.
	 */
	public boolean valueCompare(Value value) {
		try {
		if (this.value==value)
			return true;
		else
			return false;
		
		}catch(Exception e) {
			return false;}
	}
	/**
	 * Kart�n tipimi verilen tip ile kar��la�t�r�r.
	 * @param type kar��la�t�r�lmak istenen tip
	 * @return tipler ayn�ysa true, farkl�ysa false d�nd�r�r.
	 */
	public boolean typeCompare(Type type) {
		try {
		if(this.type==type)
			return true;
		else
			return false;
		
		}catch(Exception e) {
			return false;}
	}
	
	@Override
	/**
	 * Kart de�erinin metin olarak de�erini d�nd�r�r.
	 */
	public String toString() {
		return " " + this.type + " - " + this.value;
				
	}
}
