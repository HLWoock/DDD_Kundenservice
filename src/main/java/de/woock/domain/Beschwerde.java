package de.woock.domain;

import static de.woock.domain.Status.AUFGENOMMEN;

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
public class Beschwerde extends Vorgang {
	
    private String beschwerde;
    private String antwort;
    private Date   von;
@Enumerated(EnumType.STRING) private Status status;

	@Override
	public Beschwerde weiterleitenAn(Abteilungen abteilung) {
		Kundenservice.vorgaengeBoard.neuenVorgangAnheften(this, abteilung);
		return this;
	}
	
	public Vorgang einreichen(String beschwerde) {
		this.beschwerde = beschwerde;
		this.von        = new Date();
		this.status     = AUFGENOMMEN;
		return Kundenservice.vorgaengeOrdner.abheften(this);	
	}

	@Override
	public void beantworten(String antwort) {
		this.antwort = antwort;
		Kundenservice.vorgaengeOrdner.updaten(this);
	}
	
	public static List<Vorgang> liste() {
		return Kundenservice.vorgaengeOrdner.alleVorgaenge();
	}

}
