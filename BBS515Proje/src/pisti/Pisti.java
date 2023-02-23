package pisti;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ýki kiþi oynamak üzere yazýlmýþ piþti oyunu.
 * 
 * @author N19235573 Efe Bektaþ
 * @since 2021-01-04
 *
 */
public class Pisti {
	

	public static void main(String[] args) {

		/*Karþýlama mesajý*/

		System.out.println("Merhaba! Piþti oyununa hoþgeldiniz!");


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



		System.out.println("Oyun iki kiþiliktir.");




		/*Oyunu kur*/
		Scanner keyboard = new Scanner(System.in);
		int input=0;
		boolean isModeDemo=false;
		
		System.out.println("Hangi modda oynamak istersiniz?:  [1]DEMO    [2]ORÝJÝNAL");
		input = keyboard.nextInt();
		
		if (input==1) {
			isModeDemo=true;
			System.out.println("Oyun demo modunda çalýþtýrýlýyor");
		}
		else {
			System.out.println("Oyun orijinal modunda çalýþtýrýlýyor");
		}
		
	
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck(isModeDemo);
		playingDeck.shuffle();

		Player player1= new Player();
		Player player2= new Player();
		Deck table= new Deck();

		
		
		if (isModeDemo) {
			System.out.println("Oyun 13 kartla oynanýr");
			
			for (int i= 0; i<2 ; i++) {
				player1.drawCard(playingDeck);
				player2.drawCard(playingDeck);
				table.drawCard(playingDeck);
				}
				table.drawCard(playingDeck);
				table.drawCard(playingDeck);
			
		}
		
		else {
			System.out.println("Oyun 52 kartla oynanýr");
			
			for (int i= 0; i<4 ; i++) {
				player1.drawCard(playingDeck);
				player2.drawCard(playingDeck);
				table.drawCard(playingDeck);
				}
		}
		
		System.out.println("Kartlar karýldý ve daðýtýldý...");

		/*Masada en üstte olan kart açýlýr*/	
		
		System.out.println("Masadaki kartlar:" + "[hidden] [hidden] [hidden] " + table.showCard(3));


		/****Oyuncular oyuna baþlayabilir****/

		/*Ýlk olarak oyuncu1 oyuna baþlar. her turda tur sayýsý arttýrýlýr. Tekli sayýlarda oyuncu1
		 * oynarken çiftli sayýlarda oyuncu2 oynar.*/

		/*boþ bir player nesnesi yaratýlýr. Bu nesne oyun içerisinde pointer olarak görev görür.*/
		
		/*Oyun bitiminde eðer masadaki kartý kimse alamazsa en son masayý alan oyuncu bu kartlarý alýr.
		 * Bunun kaydýný tutmak için de whoTookTheCards array listi oluþturulur.*/
		
		/*Destede kart olup olmadýðýný hatýrlamak için boolean deðer tutarýz. 
		 * Ayný zamanda bu deðer kart daðýtmak için sürekli döngüye girmemizi
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

			/*Tur baþlar*/

			System.out.format("\n\n" + "--Tur:"+ turn +"--"+ "\n\n" + 
								"OYUNCU %d \nElindeki kartlar:\n" , player.getPlayerID());


			/*Oyuncunun kartlarý yazýlýr.  */
			
			for (int i=1 ; i<=player.size() ; i++) {
				
				System.out.println( i + "-" + player.showCard(i-1) + "\n");
			}
			
			System.out.println("Hangi kartý oynamak istiyorsun?:");

			/*Ýstenilen kart konsola girilir*/
			 
			/* Eðer uyumsuz girdi olursa burada yakalanýr ve tekrar denemek için loop çalýþtýrýlýr*/
			
			while(true) {
				try {
					input= keyboard.nextInt();
				
				if ( input <=0 || input> player.size()) {
					if (player.size()==1)
						System.out.println("Oynayabileceðiniz yalnýzca bir tane kartýnýz var");
					else
						System.out.println("Lütfen 1 ile " + player.size() + " arasýnda bir sayý giriniz.");
					}
				else
					break;
				
				} catch ( Exception e) {
					System.out.println("Lütfen bir sayý giriniz.");
					
					/* Buraya keyboard.next deriz. Eðer demezsek keyboard.nextInt konsolda
					 * bulunan deðeri sürekli okumaya devam edecektir.
					 * Ancak girilen token tüketilirse bir sonraki tokeni okumaya geçebilir.
					 * */
					keyboard.next();
				}
			}
				

			System.out.format("OYUNCU %d þu kartý oynadý :"+ player.showCard(input-1), player.getPlayerID());
			/*showCard eðer hatayla karþýlaþýrsa null Card döndürür. Bunun oyuna etki etmesini 
			 * engellemek için null Card deðerlerini silmeliyiz*/
			
			table.drawCard(player, input-1);
			
			turn++;

			/*Masadan kart alabilmek için iki kuralýmýz vardýr;
			 * 1. Atýlan kartla masadaki kartýn deðerleri ayný olmalýdýr.
			 * 2. JACK (Vale) kartý atýlýrsa	*/

			if (table.compareTableCards() || 
					(table.size()>=2  &  table.showCard(table.size()-1).valueCompare(Value.VALE) )) {

				whoTookTheCards.add(player.getPlayerID());

				/* 
				 * Piþtinin gerçekleþebilmesi için, masada sadece tek bir kart olmalýdýr.
				 * Bu durumda "a" if stamement i gerçekleþir.
				 * 
				 * Eðer masada duran tek kart vale ise ve valeyle alýnýrsa,
				 * buna duble piþti denir.
				 * Bu durumda hem "a" hemde "b" if stamement i çalýþýr.
				 */

				/*a*/if (table.size()==2) {
					player.playerInventory().pisti();
					
					/*b*/if(table.showCard(table.size()-1).valueCompare(Value.VALE)) {
						player.playerInventory().pisti();
					}
				}

				/*Eðer kazanýrsa, oyuncu masadaki tüm kartlara bakma þansýna sahip olur.*/
				
				System.out.println("\nMasadaki kartlarý aldýn. Bunlar :" + table.toString() );

				player.playerInventory().takeAllFromTable(table);

			}
			
			/*
			 * Destedeki kartlar bitse bile oyuncular ellerindeki kartlarla oynayabilir.
			 * Oyunun bittiðini iki tarafýn kartý bittiðinde anlarýz.
			 * */
			
			
			/*kart daðýtma*/
			int size;
			if (isModeDemo) {
				size=2;
			}
			else {
				size=4;
			}

			if ( !isDeckEmpty && player.size()==0 ){
				/*Eðer destede daðýtýlacak 4 adet kart kalmazsa,
				 * ne kadar kart varsa o kadarý daðýtýlýr.*/

				if (playingDeck.size()<size) {
					size= playingDeck.size();
				}

				if (playingDeck.size()==0) {
					System.out.println("\nDestedeki kartlar bitmiþ durumda.");
					isDeckEmpty=true; 
				}
				else {
					for (int i= 0; i<size ; i++) {
						player.drawCard(playingDeck);
					}
				}
			}


			
			

			/*oyunu bitirme vakti*/
			/*destede kart bitmiþse ve oyunculardan en az birinde kartlar tükenmiþse, oyun bitirilir*/

			if ( (player2.size()==0 | player1.size()==0) & isDeckEmpty  ) {
				
				/*Masada kalan kartlar varsa, en son masadan kart alan oyuncu 
				 * kartlarý toplar.
				 * */
				
				if (table.size()!=0 ) {
					
					try {
					
					if (whoTookTheCards.get( whoTookTheCards.size() - 1 )==1)
						player1.playerInventory().takeAllFromTable(table);
					
					else
						player2.playerInventory().takeAllFromTable(table);
					
					
					} catch( Exception e) { 
						
						System.out.println("Ýkinizde kart alamadýnýz. Tüm kartlar "
								+ "masada kaldý.");
						
	
					}
					
					/*Ayný zamanda oyuncunun elinde kart kalmýþsa, inventory'sine
					 * aktarýlýr.*/
					
					if (player1.size()!=0)
					player1.playerInventory().takeAllFromTable(player1);
					
					if(player2.size()!=0)
					player2.playerInventory().takeAllFromTable(player2);
					
						
				}
				
				
				System.out.println("\nOyun bitmiþtir. Skorlar hesaplanýyor \n\n");

				player1.playerInventory().scoreCalculate();
				player2.playerInventory().scoreCalculate();
				int theWinner;

				theWinner= player1.playerInventory().whoIsWinner(player2.playerInventory());
				
				/*Kimin oyunu aldýðý oyuncu nesnesinin içine kaydedilir.*/
				/*total score listesinin her tek elemaný oyuncu1 ,her çift elemaný oyuncu2
				 * için skoru tutar
				 * arrayin sýfýrýncý deðerine oyuncu2 nin deðeri girilir. böylece 0 % 2 = 0 olur*/
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
					int a; /*oyuncu2 için çift sayý*/ 
					int b; /*oyuncu1 için tek sayý*/
				
					try {
						a= totalScore.get(i*2);
						b= totalScore.get(i*2+1);
						System.out.printf("%n %d %10d %n ", b, a);
					}catch(Exception e) {
						System.out.println("kontrol et");
					}
		
				}
				
				/*Tekrar oynamak istiyor muyuz?*/
				
				
				System.out.println("TEKRAR OYNAMAK ÝSTER MÝSÝN?:  [1] EVET    [2] HAYIR");
				input = keyboard.nextInt();
				
				if (input==2) /*oyundan çýkarýz*/
					break;	
				
				else { /*tekrar oyanamak istersek*/
					
					turn=1;
				
					/* Bütün kartlarý desteye toplama zamaný*/
					playingDeck.moveAllToDeck(player1.playerInventory().getInventory());
					playingDeck.moveAllToDeck(player2.playerInventory().getInventory());
					playingDeck.moveAllToDeck(table.getDeck());
					
					/*envanter puanlarý silinir.*/
					player1.playerInventory().clearScore();
					player2.playerInventory().clearScore();
				
					/*kartlar karýlýr*/
					
					playingDeck.shuffle();
					
					/*tekrar kart daðýt*/
					
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
		
		/*kapanýþ*/
		System.out.println("Oyunumu oynadýðýnýz için teþekkür ederim...");
		keyboard.close();

	/*main parantezi*/
	}

/*class parantezi*/
}

