package de.woock.domain.ereignisse;

import java.io.Serializable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AnfrageGestellt implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Long   id;
	private final String frage;
	private String antwort;
}