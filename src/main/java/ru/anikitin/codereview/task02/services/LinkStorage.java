package ru.anikitin.codereview.task02.services;

import ru.anikitin.codereview.task02.domain.Link;
import ru.anikitin.codereview.task02.services.impl.ShorterDbException;

import java.util.Collection;
import java.util.List;

public interface LinkStorage {
    Link save(String originalLink, String shortLink) throws ShorterDbException;

    Link find(String hash);

    void updateStat(String hash);

    void stat(String hash);

    List<Link> findAll();

}
