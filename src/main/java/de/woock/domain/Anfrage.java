package de.woock.domain;

import static de.woock.domain.Prio.HOCH;
import static de.woock.domain.Status.AUFGENOMMEN;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.woock.Kundenservice;
import de.woock.domain.ausnahmen.LeeresFeldException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Anfrage extends    Vorgang 
                     implements Serializable {

	private static final long serialVersionUID = 1L;
	private String frage;
	private String antwort;
	private String von;
	private Prio   prio;
	
	@Enumerated(EnumType.STRING) 
	private Status status;
	
	public Anfrage(String frage) throws LeeresFeldException {
		if (frage == null || frage.isBlank()) {
			throw new LeeresFeldException("frage");
		}

		this.frage = frage;
	}

	public Vorgang stellen(String frage) {
		this.frage   = frage;
		this.von     = String.format("%1$te %1$tb %1$tY  %1$tH:%1$tM", new Date());
		this.status  = AUFGENOMMEN;
		this.prio    = HOCH;
		return Kundenservice.vorgaengeOrdner.abheften(this);	
	}

	public Vorgang aktualisiert() {
		return Kundenservice.vorgaengeOrdner.abheften(this);	
	}
	
	public Anfrage weiterleitenAn(Abteilungen abteilung) {
		Kundenservice.vorgaengeBoard.neueAnfrageAnheften(this, abteilung);
		return this;
	}

	public void beantworten(String antwort) {
		this.antwort = antwort;
		Kundenservice.vorgaengeOrdner.updaten(this);
	}
	
	public static List<Anfrage> liste() {
		return Kundenservice.vorgaengeOrdner.alleAbfragen();
	}
}
