package de.woock.infra.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("serial")
@AllArgsConstructor
@Data
@MappedSuperclass
public abstract class Kopfdaten implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private int version;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	private Date created;

	public Kopfdaten() {
		super();
		created = new Date();
	}
}
