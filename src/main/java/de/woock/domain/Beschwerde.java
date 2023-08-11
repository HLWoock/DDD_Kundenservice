package de.woock.domain;

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

@SuppressWarnings({ "serial" })
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Beschwerde extends Vorgang 
                        implements Serializable{
	
    private String beschwerde;
    private String antwort;
    private Date   von;
	private Prio   prio;
	
    @Enumerated(EnumType.STRING) 
    private Status status;

	public Beschwerde(String beschwerde) throws LeeresFeldException {
		if (beschwerde == null || beschwerde.isBlank()) {
			throw new LeeresFeldException("frage");
		}

		this.beschwerde = beschwerde;
	}
	@Override
	public Beschwerde weiterleitenAn(Abteilungen abteilung) {
		Kundenservice.vorgaengeBoard.neueBeschwerdeAnheften(this, abteilung);
		return this;
	}
	
	public Vorgang einreichen(String beschwerde) {
		this.beschwerde = beschwerde;
		this.von        = new Date();
		this.status     = AUFGENOMMEN;
		this.prio       = Prio.HOCH;
		return Kundenservice.vorgaengeOrdner.abheften(this);	
	}

	@Override
	public void beantworten(String antwort) {
		this.antwort = antwort;
		Kundenservice.vorgaengeOrdner.updaten(this);
	}
	
	public static List<Beschwerde> liste() {
		return Kundenservice.vorgaengeOrdner.alleBeschweden();
	}

}
