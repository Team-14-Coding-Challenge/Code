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
	
			// Debug Information ausgeben (optional möglich)
			System.err.println("Ergebnis Vorrunde: " + lastActionsResult);
			
			//KartenFeld karte = new KartenFeld(sizeX, sizeY);
			
			//Erzeugen eines leeren Arrays mit den Maßen des Labyrinths
			char[][] spielfeld = new char [sizeX][sizeY]; 
			
			
			char[][] sachbearbeiterfeld = new char [sizeX][sizeY];
			
			//Startpunkt in das leere Array eintragen
			spielfeld[startX][startY] = 'O';
			
			//posHoehe und posBreite überschreiben bei jedem weiteren Zug
			int posHoehe = startX;
			int posBreite = startY;
			
			
			
			
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
			
			
			
			
			
			
			
			
			boolean o = false;
			//Schleife für die Bewegung des Bots in freie Richtung
			String i = "";
			//while (! currentCellStatus.equals("FINISH " + playerId + " 0")) {
			while (o == false) {	
			if (currentCellStatus.equals("FINISH " + playerId + " 0")) {
					i = "finish";
				}
				
				else if(westCellStatus.equals("FLOOR")) {
					i = "go west";
					//anpassen an aktuelle Position
					posBreite = posBreite - 1;
				}
				
				else if(eastCellStatus.equals("FLOOR")) {
					i = "go east";
					posBreite = posBreite + 1;
				}
				
				else if(northCellStatus.equals("FLOOR")) {
					i = "go north";
					posHoehe = posHoehe - 1;
				}
				
				else if(southCellStatus.equals("FLOOR")) {
					i = "go south";
					posHoehe = posHoehe + 1;
				}
				
				// Rundenaktion ausgeben 
				System.out.println(i);
			}
			
			
			
			
		}
		
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
