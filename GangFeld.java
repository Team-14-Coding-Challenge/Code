package de.vitbund.vitmaze.players;

public class GangFeld extends KartenFeld {
	
	private boolean besucht = false;

	public boolean isBesucht() {
		return besucht;
	}

	public void setBesucht(boolean besucht) {
		this.besucht = besucht;
	}

}
