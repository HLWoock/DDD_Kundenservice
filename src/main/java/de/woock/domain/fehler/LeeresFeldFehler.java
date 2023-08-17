package de.woock.domain.fehler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LeeresFeldFehler extends Exception {

	private static final long serialVersionUID = 1L;
	private final String feld;
	
	public String feld() {
		return feld;
	}
}