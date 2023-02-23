package pisti;

import java.util.ArrayList;
/**
 * bir grup kart�n sahip oldu�u �zellikleri g�sterir
 * @author EfeB
 *
 */
public abstract class GroupOfCards {
	
	private ArrayList<Card> group;
	/**
	 * grup kart� constructor'u.
	 */
	public GroupOfCards() {
		
		group= new ArrayList<Card>();
	}
	/**
	 * grup i�inde index s�ras�ndaki kart� d�nd�r�r.
	 * @param index kart�n listedeki konumu
	 * @return kart nesnesi
	 */
	public Card showCard(int index) {
		
		return group.get(index);
	}
	/**
	 * grup i�inde index s�ras�ndaki kart� siler.
	 * @param index kart�n listedeki konumu
	 */
	public void removeCard(int index) {
		
		group.remove(index);
		
	}
	/**
	 * bir grupta ka� adet kart oldu�unu d�nd�r�r.
	 * @return kart adedi
	 */
	public abstract int size();

}
	
