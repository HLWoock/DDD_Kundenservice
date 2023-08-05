package de.woock.domain;

import static de.woock.domain.Prio.HOCH;
import static de.woock.domain.Status.AUFGENOMMEN;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.woock.Kundenservice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings({ "serial" })
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Anfrage extends    Vorgang 
                     implements Serializable {

	@NotBlank(message = "Anfrage kann nicht leer sein!")
	private String frage;
	
	private String antwort;
    
	@NotBlank(message = "Es muss ein Datum gesetzt sein!")
	private String von;
	
	@NotNull
	private Prio   prio;
	
	@NotNull
	@Enumerated(EnumType.STRING) 
	private Status status;
	
	public Anfrage(String text) {
		this.frage = text;
	}
	

	public Vorgang stellen(String anfrage) {
		this.frage = anfrage;
		this.von     = String.format("%1$te %1$tb %1$tY  %1$tH:%1$tM", new Date());
		this.status  = AUFGENOMMEN;
		this.prio    = HOCH;
		return Kundenservice.vorgaengeOrdner.abheften(this);	
	}

	public Vorgang heuteGestellt() {
		this.von     = String.format("%1$te %1$tb %1$tY  %1$tH:%1$tM", new Date());
		this.status  = AUFGENOMMEN;
		return Kundenservice.vorgaengeOrdner.abheften(this);	
	}
	
	public Vorgang aktualisiert() {
		return Kundenservice.vorgaengeOrdner.abheften(this);	
	}
	
	public Anfrage weiterleitenAn(Abteilungen abteilung) {
		Kundenservice.vorgaengeBoard.neuenVorgangAnheften(this, abteilung);
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
