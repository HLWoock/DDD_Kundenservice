package de.woock.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.woock.infra.entity.Kopfdaten;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings({ "serial" })
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vorgang extends    Kopfdaten 
                              implements Serializable { 
	
	public abstract Vorgang weiterleitenAn(Abteilung abteilung);
	public abstract void beantworten(String antwort);
}
