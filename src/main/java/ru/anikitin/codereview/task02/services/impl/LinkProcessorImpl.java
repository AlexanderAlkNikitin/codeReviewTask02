package ru.anikitin.codereview.task02.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anikitin.codereview.task02.controller.Request;
import ru.anikitin.codereview.task02.controller.Response;
import ru.anikitin.codereview.task02.domain.Link;
import ru.anikitin.codereview.task02.services.LinkProcessor;
import ru.anikitin.codereview.task02.services.LinkShorter;
import ru.anikitin.codereview.task02.services.LinkStorage;
import ru.anikitin.codereview.task02.services.MessageBuilder;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkProcessorImpl implements LinkProcessor {

    public static final String INVALID = "INVALID";
    public static final String DB_ERROR = "DB_ERROR";
    private final LinkShorter linkShorter;
    private final LinkStorage linkStorage;
    private final MessageBuilder messageBuilder;

    @Override
    @Transactional
    public Response process(Request request) {
        if (validate(request)) {
            try {
                String originalLink = request.getOriginal();
                String shortLink = linkShorter.toShortLink(originalLink);

                if (getFromCash(shortLink).isEmpty()
                        && linkStorage.find(shortLink) == null) {
                    linkStorage.save(originalLink, shortLink);
                }
                return Response.builder().link(shortLink).build();
            } catch (ShorterDbException e) {
                log.error("Error ", e.getCause());
                return Response.builder()
                        .error(messageBuilder.build(DB_ERROR, e.getCause().getClass().getSimpleName()))
                        .build();
            }
        }
        return Response.builder()
                .error(messageBuilder.build(INVALID, request))
                .build();
    }

    @Override
    @Transactional
    public String getOriginalLink(String hash) {
        Optional<String> original = getFromCash(hash);
        String originalLink = original.orElseGet(() -> linkStorage.find(hash).getOriginalLink());
        try {
            linkStorage.updateStat(hash);
        } catch (Exception e) {
            log.info("Something was wrong", e);
        }
        return originalLink;
    }

    @Override
    @Transactional
    public Link stat(String hash) {
        linkStorage.stat(hash);
        return linkStorage.find(hash);
    }

    @Override
    @Transactional
    public List<Link> stats() {
        return linkStorage.findAll();

    }

    private Optional<String> getFromCash(String hash) {
        log.info("Implement me!");
        // какая то реализация кэша

        return Optional.empty();
    }


    private boolean validate(Request request) {
        return request != null && request.getOriginal() != null;
    }
}
