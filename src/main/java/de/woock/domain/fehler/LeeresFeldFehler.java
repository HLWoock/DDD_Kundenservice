package de.woock.domain.fehler;

public class LeeresFeldFehler extends Exception {

	private static final long serialVersionUID = 1L;
	private final String feld;
	
	public LeeresFeldFehler(String feld) {
		super(String.format("%s darf nicht leer sein", feld), null, true, false);
		this.feld = feld;
	}
	
	public String feld() {
		return feld;
	}
}