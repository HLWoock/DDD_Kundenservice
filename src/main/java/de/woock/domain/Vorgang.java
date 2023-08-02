package de.woock.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import de.woock.infra.entity.Kopfdaten;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings({ "serial" })
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public abstract class Vorgang extends    Kopfdaten 
                              implements Serializable { 
	
	public abstract Vorgang weiterleitenAn(Abteilungen abteilung);
	public abstract void beantworten(String antwort);
}
