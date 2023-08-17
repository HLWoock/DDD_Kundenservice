package de.woock.domain.ausnahmen;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LeeresFeldException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String feld;
	
	public String feld() {
		return feld;
	}
}