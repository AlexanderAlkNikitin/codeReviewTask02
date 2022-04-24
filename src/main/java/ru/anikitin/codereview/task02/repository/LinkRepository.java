package ru.anikitin.codereview.task02.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.anikitin.codereview.task02.domain.Link;

import java.util.List;


@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
    Link findByShortLink(String hash);

    List<Link> findAll();
}
