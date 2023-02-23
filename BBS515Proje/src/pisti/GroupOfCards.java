package pisti;

import java.util.ArrayList;
/**
 * bir grup kartın sahip olduğu özellikleri gösterir
 * @author EfeB
 *
 */
public abstract class GroupOfCards {
	
	private ArrayList<Card> group;
	/**
	 * grup kartı constructor'u.
	 */
	public GroupOfCards() {
		
		group= new ArrayList<Card>();
	}
	/**
	 * grup içinde index sırasındaki kartı döndürür.
	 * @param index kartın listedeki konumu
	 * @return kart nesnesi
	 */
	public Card showCard(int index) {
		
		return group.get(index);
	}
	/**
	 * grup içinde index sırasındaki kartı siler.
	 * @param index kartın listedeki konumu
	 */
	public void removeCard(int index) {
		
		group.remove(index);
		
	}
	/**
	 * bir grupta kaç adet kart olduğunu döndürür.
	 * @return kart adedi
	 */
	public abstract int size();

}
	
