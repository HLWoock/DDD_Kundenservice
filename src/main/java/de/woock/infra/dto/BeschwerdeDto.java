package de.woock.infra.dto;

import de.woock.domain.Prio;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BeschwerdeDto {

	private Long id;
	private int version;
	
	private String beschwerde;
	private String antwort;
	private String kunde;
	private String von;
	private Prio   prio;
	
	public Prio[] getPrios() {
		return Prio.values();
	}
}
