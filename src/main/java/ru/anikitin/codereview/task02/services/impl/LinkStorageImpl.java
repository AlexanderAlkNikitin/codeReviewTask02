package ru.anikitin.codereview.task02.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.anikitin.codereview.task02.domain.Link;
import ru.anikitin.codereview.task02.domain.LinkStat;
import ru.anikitin.codereview.task02.repository.LinkRepository;
import ru.anikitin.codereview.task02.repository.LinkStatRepository;
import ru.anikitin.codereview.task02.services.LinkStorage;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkStorageImpl implements LinkStorage {

    private final LinkRepository linkRepository;
    private final LinkStatRepository linkStatRepository;

    @Override
    public Link save(String originalLink, String shortLink) throws ShorterDbException {
        LinkStat linkStat = LinkStat.builder().requestCount(-1).rank(-1).build();
        Link link = Link.builder()
                .shortLink(shortLink)
                .linkStat(linkStat)
                .originalLink(originalLink)
                .build();
        linkStat.setLink(link);

        log.info("Saving link {}", link);
        try {
            return linkRepository.save(link);
        } catch (Exception e) {
            throw new ShorterDbException("DB error", e);
        }
    }

    @Override
    public Link find(String hash) {
        return linkRepository.findByShortLink(hash);
    }

    @Override
    public void updateStat(String hash) {
        log.debug("Try update stat for {}", hash);
        Link link = linkRepository.findByShortLink(hash);
        if (link != null && link.getLinkStat() != null) {
            linkStatRepository.findById(link.getId())
                    .ifPresent(linkStat -> saveIncremented(link, linkStat));
        }

    }

    @Override
    public void stat(String hash) {
        log.info("Counting stat for {}", hash);
        Link link = linkRepository.findByShortLink(hash);
        link.getLinkStat().setRank(linkStatRepository.rank(link.getId()));
        linkRepository.save(link);
    }

    @Override
    public List<Link> findAll() {
        return linkRepository.findAll();
    }


    private void saveIncremented(Link link, LinkStat linkStat) {
        linkStat.incrementCount();
        linkRepository.save(link);
    }
}
