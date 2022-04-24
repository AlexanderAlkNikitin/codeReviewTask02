package ru.anikitin.codereview.task02.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.anikitin.codereview.task02.domain.Link;
import ru.anikitin.codereview.task02.domain.LinkStat;


@Repository
public interface LinkStatRepository extends CrudRepository<LinkStat, Long> {

    @Query(nativeQuery = true, value = "SELECT rank() OVER (PARTITION BY id ORDER BY request_count DESC) FROM link_stat where id = :id")
    Integer rank(Long id);

}
