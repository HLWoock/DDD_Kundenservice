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

	//@NotBlank(message = "Anfrage kann nicht leer sein")
	private String frage;
	
	//@Size(min = 3, max = 50)     
	private String antwort;
    
	private String von;
	
	private Prio   prio;
	
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
		this.prio    = HOCH;
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
