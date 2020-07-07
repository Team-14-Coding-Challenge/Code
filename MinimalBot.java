package de.vitbund.vitmaze.players;

import java.util.Scanner;

/**
 * Klasse eines minimalen Bots für das VITMaze
 * @author Patrick.Stalljohann
 * @version 1.0
 *
 */
public class MinimalBot {

	/**
	 * Hauptmethode zum Ausführen des Bots
	 * @param args
	 */
	public static void main(String[] args) {
		// Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und Rundendaten liefert
		Scanner input = new Scanner(System.in);


		// INIT - Auslesen der Initialdaten
		// 1. Zeile: Maze Infos
		int sizeX = input.nextInt(); // X-Groesse des Spielfeldes (Breite)
		int sizeY = input.nextInt(); // Y-Groesse des Spielfeldes (Hoehe)
		int level = input.nextInt(); // Level des Matches
		input.nextLine(); // Beenden der ersten Zeile
		// 2. Zeile: Player Infos
		int playerId = input.nextInt(); // id dieses Players / Bots
		int startX = input.nextInt(); // X-Koordinate der Startposition dieses Player
		int startY = input.nextInt(); // Y-Koordinate der Startposition dieses Players
		input.nextLine(); // Beenden der zweiten Zeile

		
		// TURN (Wiederholung je Runde notwendig)
		while(input.hasNext()) {
			// Rundeninformationen auslesen
			String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();
	
			
			//Erzeugen der Spielfeldkarte
			char[][] spielfeld = new char [sizeX][sizeY]; 
			//Erzeugen der Objektkarte
			char[][] sachbearbeiterfeld = new char [sizeX][sizeY];
			
			//Startpunkt in das leere Array eintragen
			spielfeld[startX][startY] = 'O';
			
			//posHoehe und posBreite überschreiben bei jedem weiteren Zug
			int posHoehe = startY;
			int posBreite = startX;

			
			// Umgebung abfragen und eintragen
			
			
			//Richtung Norden
			//Freies Feld
			if(northCellStatus.equals("FLOOR")) {
				spielfeld[posHoehe - 1][posBreite] = 'O';
			}
			
			else if(northCellStatus.contains("FINISH")) {
				//Prüfung,ob unser Sachbearbeiter mit unser ID
				if (northCellStatus.equals("FINISH " + playerId)) {
					sachbearbeiterfeld[posHoehe - 1][posBreite] = 'S';
				}
				//sonst, anderen Sachbearbeiter im Array vermerken
				else {
					sachbearbeiterfeld[posHoehe - 1][posBreite] = 'F';
				}
			}
			//Wand eintragen
			else {
				spielfeld[posHoehe - 1][posBreite] = 'X';
			}
			
			
			
			
			//Richtung Osten
			if(eastCellStatus.equals("FLOOR")) {
				spielfeld[posHoehe][posBreite + 1] = 'O';
			}
			
			else if(eastCellStatus.contains("FINISH")) {
				//Prüfung,ob unser Sachbearbeiter mit unser ID
				if (eastCellStatus.equals("FINISH " + playerId)) {
					sachbearbeiterfeld[posHoehe][posBreite + 1] = 'S';
				}
				//sonst, anderen Sachbearbeiter im Array vermerken
				else {
					sachbearbeiterfeld[posHoehe][posBreite + 1] = 'F';
				}
			}
			
			else {
				spielfeld[posHoehe][posBreite + 1] = 'X';
			}
			
			
			
			
			//Richtung Süden
			if(southCellStatus.equals("FLOOR")) {
				spielfeld[posHoehe + 1][posBreite] = 'O';
			}
			
			else if(southCellStatus.contains("FINISH")) {
				//Prüfung,ob unser Sachbearbeiter mit unser ID
				if (southCellStatus.equals("FINISH " + playerId)) {
					sachbearbeiterfeld[posHoehe + 1][posBreite] = 'S';
				}
				//sonst, anderen Sachbearbeiter im Array vermerken
				else {
					sachbearbeiterfeld[posHoehe + 1][posBreite] = 'F';
				}
			}
			
			else {
				spielfeld[posHoehe + 1][posBreite] = 'X';
			}
			
			
			
			//Richtung Westen
			if(westCellStatus.equals("FLOOR")) {
				spielfeld[posHoehe][posBreite - 1] = 'O';
			}
			
			else if(westCellStatus.contains("FINISH")) {
				//Prüfung,ob unser Sachbearbeiter mit unser ID
				if (westCellStatus.equals("FINISH " + playerId)) {
					sachbearbeiterfeld[posHoehe][posBreite - 1] = 'S';
				}
				//sonst, anderen Sachbearbeiter im Array vermerken
				else {
					sachbearbeiterfeld[posHoehe][posBreite - 1] = 'F';
				}
			}
			
			else {
				spielfeld[posHoehe][posBreite - 1] = 'X';
			}
			
			
			// Bewegen des Bots
			
			boolean sachbearbeiterGefunden = false;
			String letzteAusgabe = "";

			//while (sachbearbeiterGefunden == false) {	
				
				
				// Debug Information ausgeben

				//Lvl 1 startX: 7 startY: 1
				System.err.println("Aktuelles Level: " + level);
				System.err.println("Ergebnis Vorrunde: " + lastActionsResult);
				System.err.println("Aktuelles Feld: " + currentCellStatus);
				System.err.println("Feldstatus Norden: " + northCellStatus);
				System.err.println("Feldstatus Osten: " + eastCellStatus);
				System.err.println("Feldstatus Süden: " + southCellStatus);
				System.err.println("Feldstatus Westen: " + westCellStatus);
				
				
				if (currentCellStatus.equals("FINISH " + playerId + " 0")) {
					letzteAusgabe = "finish";
					sachbearbeiterGefunden = true;
				}
				
				else if(westCellStatus.equals("FLOOR") | westCellStatus.equals("FINISH " + playerId + " 0")) {
					letzteAusgabe = "go west";
					//anpassen an aktuelle Position
					posBreite = posBreite - 1;
				}
				
				else if(eastCellStatus.equals("FLOOR") | eastCellStatus.equals("FINISH " + playerId + " 0")) {
					letzteAusgabe = "go east";
					posBreite = posBreite + 1;
				}
				
				else if(northCellStatus.equals("FLOOR") | northCellStatus.equals("FINISH " + playerId + " 0")) {
					letzteAusgabe = "go north";
					posHoehe = posHoehe - 1;
				}
				
				else if(southCellStatus.equals("FLOOR") | southCellStatus.equals("FINISH " + playerId + " 0")) {
					letzteAusgabe = "go south";
					posHoehe = posHoehe + 1;
				}
				
				//Debug Informationen ausgeben
				System.err.println("Aktuelle Koordinaten: " + posHoehe + " | " + posBreite);
				System.err.println("----------------------------------------");

				
				// Rundenaktion ausgeben 
				System.out.println(letzteAusgabe);
			}
			
		
			
		//}
		
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
