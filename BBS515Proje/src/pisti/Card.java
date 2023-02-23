package pisti;
/**
 * Destenin yaratýlmasý için gerekli kart nesnesini ve kartlarýn karþýlaþtýrýlmasý için gerekli olan
 * metodlarý barýndýrýr.
 * @author EfeB
 *
 */
public class Card {
	
	/*Type ve Value deðerleri birer enum dýr. Kart deðerleri oyun içinde deðiþmeyeceði için 
	 * kodlamada rahatlýk saðlar.*/
	
	private Value value;
	private Type type;
	/**
	 * constructor.
	 * @param type kart tipi
	 * @param value kart deðeri
	 */
	public Card(Type type, Value value) {
		this.value = value;
		this.type = type;
	}
	/**
	 * Kart deðerini döndürür.
	 * @return kart deðeri
	 */
	public Value getCardValue() {
		return this.value;
	}
	/**
	 * Kart tipini döndürür.
	 * @return kart tipi
	 */
	public Type getCardType() {
		return this.type;
	}
	
	
	/**
	 * iki kart deðerini karþýlaþtýr.
	 * 
	 * @param aCard karþýlaþtýrýlmasý istenen kart
	 * @return karþýlaþtýrýlan kartlarýn deðer ve tipi aynýysa true döndürür. farklýysa veya kart null deðerse
	 * false döndürür.
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
	 * Kartýn deðerini verilen deðer ile karþýlaþtýrýr.
	 * @param value karþýlaþtýrýlmak istenen deðer
	 * @return boolean eðer deðerler aynýysa true, farklýysa false döndürür.
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
	 * Kartýn tipimi verilen tip ile karþýlaþtýrýr.
	 * @param type karþýlaþtýrýlmak istenen tip
	 * @return tipler aynýysa true, farklýysa false döndürür.
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
	 * Kart deðerinin metin olarak deðerini döndürür.
	 */
	public String toString() {
		return " " + this.type + " - " + this.value;
				
	}
}
