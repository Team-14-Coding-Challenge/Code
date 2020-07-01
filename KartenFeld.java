package de.vitbund.vitmaze.players;

public class KartenFeld {
	private int hoehe;
	private int breite;
	//private char spielfeld[][];
	
	public KartenFeld(int hoehe, int breite) {
		this.hoehe = hoehe;
		this.breite = breite;
	}
	
	public KartenFeld() {
		
	};
	
	public int getHoehe() {
		return hoehe;
	}
	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}
	public int getBreite() {
		return breite;
	}
	public void setBreite(int breite) {
		this.breite = breite;
	}

}
