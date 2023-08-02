package de.woock.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import de.woock.domain.Vorgang;

@NoRepositoryBean
public interface BaseRepository<V extends Vorgang> extends JpaRepository<V, Long> {

}
