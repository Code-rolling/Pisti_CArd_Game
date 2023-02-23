package pisti;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * �ki ki�i oynamak �zere yaz�lm�� pi�ti oyunu.
 * 
 * @author N19235573 Efe Bekta�
 * @since 2021-01-04
 *
 */
public class Pisti {
	

	public static void main(String[] args) {

		/*Kar��lama mesaj�*/

		System.out.println("Merhaba! Pi�ti oyununa ho�geldiniz!");


		System.out.println("            __                                               \r\n"
				+ "        _..-''--'----_.                                        \r\n"
				+ "      ,''.-''| .---/ _`-._                                     \r\n"
				+ "    ,' \\ \\  ;| | ,/ / `-._`-.                                  \r\n"
				+ "  ,' ,',\\ \\( | |// /,-._  / /                                  \r\n"
				+ "  ;.`. `,\\ \\`| |/ / |   )/ /                                   \r\n"
				+ " / /`_`.\\_\\ \\| /_.-.'-''/ /                                    \r\n"
				+ "/ /_|_:.`. \\ |;'`..')  / /                                     \r\n"
				+ "`-._`-._`.`.;`.\\  ,'  / /                                      \r\n"
				+ "    `-._`.`/    ,'-._/ /                                       \r\n"
				+ "      : `-/     \\`-.._/                                        \r\n"
				+ "      |  :      ;._ (                                          \r\n"
				+ "      :  |      \\  ` \\                                         \r\n"
				+ "       \\         \\   |                                         \r\n"
				+ "        :        :   ;                                         \r\n"
				+ "        |           /                                          \r\n"
				+ "        ;         ,'                                           \r\n"
				+ "       /         /                                             \r\n"
				+ "      /         /                                              \r\n"
				+ "               /");



		System.out.println("Oyun iki ki�iliktir.");




		/*Oyunu kur*/
		Scanner keyboard = new Scanner(System.in);
		int input=0;
		boolean isModeDemo=false;
		
		System.out.println("Hangi modda oynamak istersiniz?:  [1]DEMO    [2]OR�J�NAL");
		input = keyboard.nextInt();
		
		if (input==1) {
			isModeDemo=true;
			System.out.println("Oyun demo modunda �al��t�r�l�yor");
		}
		else {
			System.out.println("Oyun orijinal modunda �al��t�r�l�yor");
		}
		
	
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck(isModeDemo);
		playingDeck.shuffle();

		Player player1= new Player();
		Player player2= new Player();
		Deck table= new Deck();

		
		
		if (isModeDemo) {
			System.out.println("Oyun 13 kartla oynan�r");
			
			for (int i= 0; i<2 ; i++) {
				player1.drawCard(playingDeck);
				player2.drawCard(playingDeck);
				table.drawCard(playingDeck);
				}
				table.drawCard(playingDeck);
				table.drawCard(playingDeck);
			
		}
		
		else {
			System.out.println("Oyun 52 kartla oynan�r");
			
			for (int i= 0; i<4 ; i++) {
				player1.drawCard(playingDeck);
				player2.drawCard(playingDeck);
				table.drawCard(playingDeck);
				}
		}
		
		System.out.println("Kartlar kar�ld� ve da��t�ld�...");

		/*Masada en �stte olan kart a��l�r*/	
		
		System.out.println("Masadaki kartlar:" + "[hidden] [hidden] [hidden] " + table.showCard(3));


		/****Oyuncular oyuna ba�layabilir****/

		/*�lk olarak oyuncu1 oyuna ba�lar. her turda tur say�s� artt�r�l�r. Tekli say�larda oyuncu1
		 * oynarken �iftli say�larda oyuncu2 oynar.*/

		/*bo� bir player nesnesi yarat�l�r. Bu nesne oyun i�erisinde pointer olarak g�rev g�r�r.*/
		
		/*Oyun bitiminde e�er masadaki kart� kimse alamazsa en son masay� alan oyuncu bu kartlar� al�r.
		 * Bunun kayd�n� tutmak i�in de whoTookTheCards array listi olu�turulur.*/
		
		/*Destede kart olup olmad���n� hat�rlamak i�in boolean de�er tutar�z. 
		 * Ayn� zamanda bu de�er kart da��tmak i�in s�rekli d�ng�ye girmemizi
		 * engeller.
		 */
		
		int turn=1;
		Player player =new Player();
		ArrayList<Integer> whoTookTheCards= new ArrayList<Integer>();
		ArrayList<Integer> totalScore= new ArrayList<Integer>();
		boolean isDeckEmpty=false;

		while(true) {

			if (turn%2!=0)
				player= player1;
			
			else if (turn%2==0)
				player= player2;

			/*Tur ba�lar*/

			System.out.format("\n\n" + "--Tur:"+ turn +"--"+ "\n\n" + 
								"OYUNCU %d \nElindeki kartlar:\n" , player.getPlayerID());


			/*Oyuncunun kartlar� yaz�l�r.  */
			
			for (int i=1 ; i<=player.size() ; i++) {
				
				System.out.println( i + "-" + player.showCard(i-1) + "\n");
			}
			
			System.out.println("Hangi kart� oynamak istiyorsun?:");

			/*�stenilen kart konsola girilir*/
			 
			/* E�er uyumsuz girdi olursa burada yakalan�r ve tekrar denemek i�in loop �al��t�r�l�r*/
			
			while(true) {
				try {
					input= keyboard.nextInt();
				
				if ( input <=0 || input> player.size()) {
					if (player.size()==1)
						System.out.println("Oynayabilece�iniz yaln�zca bir tane kart�n�z var");
					else
						System.out.println("L�tfen 1 ile " + player.size() + " aras�nda bir say� giriniz.");
					}
				else
					break;
				
				} catch ( Exception e) {
					System.out.println("L�tfen bir say� giriniz.");
					
					/* Buraya keyboard.next deriz. E�er demezsek keyboard.nextInt konsolda
					 * bulunan de�eri s�rekli okumaya devam edecektir.
					 * Ancak girilen token t�ketilirse bir sonraki tokeni okumaya ge�ebilir.
					 * */
					keyboard.next();
				}
			}
				

			System.out.format("OYUNCU %d �u kart� oynad� :"+ player.showCard(input-1), player.getPlayerID());
			/*showCard e�er hatayla kar��la��rsa null Card d�nd�r�r. Bunun oyuna etki etmesini 
			 * engellemek i�in null Card de�erlerini silmeliyiz*/
			
			table.drawCard(player, input-1);
			
			turn++;

			/*Masadan kart alabilmek i�in iki kural�m�z vard�r;
			 * 1. At�lan kartla masadaki kart�n de�erleri ayn� olmal�d�r.
			 * 2. JACK (Vale) kart� at�l�rsa	*/

			if (table.compareTableCards() || 
					(table.size()>=2  &  table.showCard(table.size()-1).valueCompare(Value.VALE) )) {

				whoTookTheCards.add(player.getPlayerID());

				/* 
				 * Pi�tinin ger�ekle�ebilmesi i�in, masada sadece tek bir kart olmal�d�r.
				 * Bu durumda "a" if stamement i ger�ekle�ir.
				 * 
				 * E�er masada duran tek kart vale ise ve valeyle al�n�rsa,
				 * buna duble pi�ti denir.
				 * Bu durumda hem "a" hemde "b" if stamement i �al���r.
				 */

				/*a*/if (table.size()==2) {
					player.playerInventory().pisti();
					
					/*b*/if(table.showCard(table.size()-1).valueCompare(Value.VALE)) {
						player.playerInventory().pisti();
					}
				}

				/*E�er kazan�rsa, oyuncu masadaki t�m kartlara bakma �ans�na sahip olur.*/
				
				System.out.println("\nMasadaki kartlar� ald�n. Bunlar :" + table.toString() );

				player.playerInventory().takeAllFromTable(table);

			}
			
			/*
			 * Destedeki kartlar bitse bile oyuncular ellerindeki kartlarla oynayabilir.
			 * Oyunun bitti�ini iki taraf�n kart� bitti�inde anlar�z.
			 * */
			
			
			/*kart da��tma*/
			int size;
			if (isModeDemo) {
				size=2;
			}
			else {
				size=4;
			}

			if ( !isDeckEmpty && player.size()==0 ){
				/*E�er destede da��t�lacak 4 adet kart kalmazsa,
				 * ne kadar kart varsa o kadar� da��t�l�r.*/

				if (playingDeck.size()<size) {
					size= playingDeck.size();
				}

				if (playingDeck.size()==0) {
					System.out.println("\nDestedeki kartlar bitmi� durumda.");
					isDeckEmpty=true; 
				}
				else {
					for (int i= 0; i<size ; i++) {
						player.drawCard(playingDeck);
					}
				}
			}


			
			

			/*oyunu bitirme vakti*/
			/*destede kart bitmi�se ve oyunculardan en az birinde kartlar t�kenmi�se, oyun bitirilir*/

			if ( (player2.size()==0 | player1.size()==0) & isDeckEmpty  ) {
				
				/*Masada kalan kartlar varsa, en son masadan kart alan oyuncu 
				 * kartlar� toplar.
				 * */
				
				if (table.size()!=0 ) {
					
					try {
					
					if (whoTookTheCards.get( whoTookTheCards.size() - 1 )==1)
						player1.playerInventory().takeAllFromTable(table);
					
					else
						player2.playerInventory().takeAllFromTable(table);
					
					
					} catch( Exception e) { 
						
						System.out.println("�kinizde kart alamad�n�z. T�m kartlar "
								+ "masada kald�.");
						
	
					}
					
					/*Ayn� zamanda oyuncunun elinde kart kalm��sa, inventory'sine
					 * aktar�l�r.*/
					
					if (player1.size()!=0)
					player1.playerInventory().takeAllFromTable(player1);
					
					if(player2.size()!=0)
					player2.playerInventory().takeAllFromTable(player2);
					
						
				}
				
				
				System.out.println("\nOyun bitmi�tir. Skorlar hesaplan�yor \n\n");

				player1.playerInventory().scoreCalculate();
				player2.playerInventory().scoreCalculate();
				int theWinner;

				theWinner= player1.playerInventory().whoIsWinner(player2.playerInventory());
				
				/*Kimin oyunu ald��� oyuncu nesnesinin i�ine kaydedilir.*/
				/*total score listesinin her tek eleman� oyuncu1 ,her �ift eleman� oyuncu2
				 * i�in skoru tutar
				 * arrayin s�f�r�nc� de�erine oyuncu2 nin de�eri girilir. b�ylece 0 % 2 = 0 olur*/
				switch(theWinner) {
				case  0 : System.out.println("OYUNCU 1 OYUNU KAZANDI!"); 
							player1.refreshScore();
							totalScore.add(player2.getScore());
							totalScore.add(player1.getScore());
							break;
							
				case -1 : System.out.println("OYUNCU 2 OYUNU KAZANDI!");
							player2.refreshScore();
							totalScore.add(player2.getScore());
							totalScore.add(player1.getScore());
							break;
							
				case  1 : System.out.println("OYUN BERABERE!"); 
							player2.refreshScore();
							player1.refreshScore();
							totalScore.add(player2.getScore());
							totalScore.add(player1.getScore());
							break;
				}
				
				
				System.out.print("Oyun tablosu; \n|OYUNCU1|  |OYUNCU2|");
				
				for (int i=0; i<totalScore.size()/2 ; i++) {
					int a; /*oyuncu2 i�in �ift say�*/ 
					int b; /*oyuncu1 i�in tek say�*/
				
					try {
						a= totalScore.get(i*2);
						b= totalScore.get(i*2+1);
						System.out.printf("%n %d %10d %n ", b, a);
					}catch(Exception e) {
						System.out.println("kontrol et");
					}
		
				}
				
				/*Tekrar oynamak istiyor muyuz?*/
				
				
				System.out.println("TEKRAR OYNAMAK �STER M�S�N?:  [1] EVET    [2] HAYIR");
				input = keyboard.nextInt();
				
				if (input==2) /*oyundan ��kar�z*/
					break;	
				
				else { /*tekrar oyanamak istersek*/
					
					turn=1;
				
					/* B�t�n kartlar� desteye toplama zaman�*/
					playingDeck.moveAllToDeck(player1.playerInventory().getInventory());
					playingDeck.moveAllToDeck(player2.playerInventory().getInventory());
					playingDeck.moveAllToDeck(table.getDeck());
					
					/*envanter puanlar� silinir.*/
					player1.playerInventory().clearScore();
					player2.playerInventory().clearScore();
				
					/*kartlar kar�l�r*/
					
					playingDeck.shuffle();
					
					/*tekrar kart da��t*/
					
					if(isModeDemo) {
						for (int i= 0; i<2 ; i++) {
							player1.drawCard(playingDeck);
							player2.drawCard(playingDeck);
							table.drawCard(playingDeck);
							}
						table.drawCard(playingDeck);
						table.drawCard(playingDeck);
					}
					
					else {
						for (int i= 0; i<4 ; i++) {
							player1.drawCard(playingDeck);
							player2.drawCard(playingDeck);
							table.drawCard(playingDeck);
							}
					}
					
					isDeckEmpty=false;
					whoTookTheCards.clear();
					
					System.out.println("Masadaki kartlar:" + "[hidden] [hidden] [hidden] " + table.showCard(3));
					
				/*tekrar oynama else parantezi*/
				}
				
			/*oyunu bitirme if statement parantezi*/
			}
			
			
		/*while loop parantezi*/
		}
		
		/*kapan��*/
		System.out.println("Oyunumu oynad���n�z i�in te�ekk�r ederim...");
		keyboard.close();

	/*main parantezi*/
	}

/*class parantezi*/
}

