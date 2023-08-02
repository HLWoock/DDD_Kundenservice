package de.woock.domain;

import static de.woock.domain.Status.AUFGENOMMEN;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

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

								 @Transient
								 private Speichern speichern;
	                             private String anfrage;
	                             private String antwort;
	                             private Date   von;
	@Enumerated(EnumType.STRING) private Status status;
	
	public Anfrage(String text, Speichern speichern) {
		this.anfrage = text;
	}
	

	public Vorgang stellen(String anfrage) {
		this.anfrage = anfrage;
		this.von     = new Date();
		this.status  = AUFGENOMMEN;
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
