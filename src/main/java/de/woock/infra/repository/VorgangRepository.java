package de.woock.infra.repository;

import javax.transaction.Transactional;

import de.woock.domain.Vorgang;


@Transactional
public interface VorgangRepository extends BaseRepository<Vorgang> {
}
