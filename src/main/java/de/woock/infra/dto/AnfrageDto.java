package de.woock.infra.dto;

import de.woock.domain.Prio;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnfrageDto {
	
	private Long id;
	private int version;
	
	private String frage;
	private String antwort;
	private String kunde;
	private String von;
	private Prio   prio;
	private Prio[] prios;
	
	public AnfrageDto(Prio[] prios) {
		this.prios = prios;
	}
}
